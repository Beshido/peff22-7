import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                //System.out.println(file.getName());
            };
        }
    }
    public static void main(String[] args) throws IOException {
        String currentPath = new java.io.File(".").getCanonicalPath();
        getAllFiles(currentPath+"/in/");
        java.util.Collections.sort(listeFichiers);
        // System.out.println(listeFichiers);
        for(int iB =0; iB<1;iB++){
        // for(int iB =0; iB<listeFichiers.size();iB++){
            List<String> list = parsertp6.readFile(listeFichiers.get(iB));
            //System.out.println(list);
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
            // sommets[nbjoueurs+1].estJoueur = true;
            Sommet.ARBITRE = sommets[nbjoueurs+1];
            // System.out.println("s : " +nbsommets+" Joueurs : "+ nbjoueurs+" Equipes : "+ nbequipes+ " Arcs : "+ nbarcs );
            for(int i = 1; i < list.size();i++){
                String vals[] = list.get(i).split(" ");

                int nomPoint = Integer.parseInt(vals[0]);
                int nomLie = Integer.parseInt(vals[1]);
                int poids = Integer.parseInt(vals[2]);

                sommets[nomPoint].fillHashMap(nomLie, poids);
            }
            for (int i=1; i<sommets.length; i++) {
                System.out.println(i+" -> "+sommets[i].voisins);
            }
            int tToArbitre[] = dijkrsta(Sommet.ARBITRE, sommets, nbsommets);
            int tToPlayer[] = dijkrsta(Sommet.ARBITRE, sommets, nbsommets); //TODO path but in reverse arrow.
            // int tToArbitre[] = {0,1,2,3,4};
            //int tToPlayer[] = {0,4,2,3,9};
            for (int i=0; i<tToArbitre.length; i++) {
                System.out.println(tToArbitre[i]+" "+tToPlayer[i]);
            }
            Equipe.addAllPlayer(tToArbitre, tToPlayer, nbjoueurs, nbequipes);
        }
    }

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
            //extraction du minimum dans la file de priorite
            Sommet u = listePrio.extraireMin();
            for(Integer voisin : u.voisins.keySet()){
                if(distance[voisin]>u.voisins.get(voisin)+ distance[u.id]){
                    distance[voisin] = u.voisins.get(voisin)+ distance[u.id];//distance du sommet courant + val de l'arc
                    listePrio.insertion(sommets[voisin], distance[voisin]);
                }
            }
        }
        return distance;
    }
}
