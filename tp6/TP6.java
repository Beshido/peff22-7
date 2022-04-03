import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TP6{
    public static ArrayList<String> listeFichiers = new ArrayList<String>();
    public static void getAllFiles(String sDir){
        File[] faFiles = new File(sDir).listFiles();
        for(File file : faFiles){
            if(file.isDirectory()){
                getAllFiles(file.getAbsolutePath());
            }
            if(file.getName().length()>1){
                listeFichiers.add(file.getAbsolutePath());
            };
        }
    }
    public static void main(String[] args) throws IOException {
        String currentPath = new java.io.File(".").getCanonicalPath();
        getAllFiles(currentPath+"/in/");
        java.util.Collections.sort(listeFichiers);
        String sol="";
        // get information from files 
        for(int iB =0; iB<listeFichiers.size();iB++){
            List<String> list = parsertp6.readFile(listeFichiers.get(iB));
            String a[] = list.get(0).split(" ");
            int nbsommets = Integer.parseInt(a[0]);
            int nbjoueurs = Integer.parseInt(a[1]);
            Sommet sommets[] = new Sommet[nbsommets+1];
            int nbequipes = Integer.parseInt(a[2]);
            int nbarcs = Integer.parseInt(a[3]);
            for(int i = 1; i<=nbsommets;i++){
                if(i>=nbjoueurs){
                    sommets[i] = new Sommet(false, i);
                }
                else{
                    sommets[i] = new Sommet(true, i);
                }
            }
            Sommet.ARBITRE = sommets[nbjoueurs+1];
            
            for(int i = 1; i < list.size();i++){
                String vals[] = list.get(i).split(" ");

                int nomPoint = Integer.parseInt(vals[0]);
                int nomLie = Integer.parseInt(vals[1]);
                int poids = Integer.parseInt(vals[2]);

                sommets[nomPoint].fillHashMap(nomLie, poids);
            }
        
            //END PARSER -------------------------------------------------------
            //At this step every sommet is link to this neibor by "public HashMap<Integer, Integer> voisins".

            // Calculate cost to go from player to ARBITRE & from ARBITRE to player.
            int tToArbitre[] = dijkrstaBis(Sommet.ARBITRE, sommets, nbsommets);
            int tToPlayer[] = dijkrsta(Sommet.ARBITRE, sommets, nbsommets);
           
            // Split player in Equipe
            Equipe.addAllPlayer(tToArbitre, tToPlayer, nbjoueurs, nbequipes);
            // Calculate cost for comunicate between every Equipe.
            sol+=Equipe.getFullEquipeCost()+"\n";
        }
        //Write final solution
        parsertp6.writeFile(sol, "out");
    }
    /** Calculate time needed to go from everywere to source. */
    public static int [] dijkrsta(Sommet source, Sommet[] sommets, int nbSommets){
        FileDePriorite listePrio = new FileDePriorite();
        int distance [] = new int [nbSommets+1];
        for(int i = 1; i< distance.length ; i++ ){
            if(i == source.id){
                distance[i] = 0;
            }else{
                distance[i] = Integer.MAX_VALUE;
            }
        }
        listePrio.insertion(source, 0);

        while (!listePrio.estVide()) {
            //extraction of the minimum in the priority queue
            Sommet u = listePrio.extraireMin();
            for(Integer voisin : u.voisins.keySet()){
                if(distance[voisin]>u.voisins.get(voisin)+ distance[u.id]){
                    distance[voisin] = u.voisins.get(voisin)+ distance[u.id];//distance from current vertex + arc value
                    listePrio.insertion(sommets[voisin], distance[voisin]);
                }
            }
        }
        return distance;
    }

        /** Calculate time needed to go everywere from source. */
     public static int [] dijkrstaBis(Sommet source, Sommet[] sommets, int nbSommets){

        int distance [] = new int [nbSommets+1];
        for(int i = 1; i< distance.length ; i++ ){
            distance[i] = Integer.MAX_VALUE;
        }
        //for each vertex we determine the distance to the referee
        for(int i = 1; i<sommets.length; i++){
            FileDePriorite listePrio = new FileDePriorite();
            distance[sommets[i].id] = 0;
            listePrio.insertion(sommets[i], 0);

            while (!listePrio.estVide()) {
            //extraction of the minimum in the priority queue
                Sommet u = listePrio.extraireMin();

                //  if the vertex we extract is the referee,
                //  we update the distance of the current vertex
                //  and set the distance of the referee to max for the next vertex
                if(u.id == source.id ){
                    distance[sommets[i].id] = distance[u.id];
                    distance[u.id] = Integer.MAX_VALUE;
                    break;
                }
                for(Integer voisin : u.voisins.keySet()){
                    if(distance[voisin]>u.voisins.get(voisin)+ distance[u.id]){
                        distance[voisin] = u.voisins.get(voisin)+ distance[u.id];
                        listePrio.insertion(sommets[voisin], distance[voisin]);
                    }
                }
            }
        }
        //we reset the referee's distance to 0 before returning the distances
       distance[source.id] = 0;

        return distance;
    }
}
