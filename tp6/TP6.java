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
        System.out.println(listeFichiers);
        List<String> list = parsertp6.readFile(args[0]);

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
        System.out.println("s : " +nbsommets+" Joueurs : "+ nbjoueurs+" Equipes : "+ nbequipes+ " Arcs : "+ nbarcs );
        for(int i = 1; i < list.size();i++){
            String vals[] = list.get(i).split(" ");

            int nomPoint = Integer.parseInt(vals[0]);
            int nomLie = Integer.parseInt(vals[1]);
            int poids = Integer.parseInt(vals[2]);

            sommets[nomPoint].fillHashMap(nomLie, poids);
            System.out.println(sommets[nomPoint].voisins);

        }

    }

    public int [] dijkrsta(Sommet source, Sommet[] sommets,int nbSommets){
        FileDePriorite listePrio = new FileDePriorite();
        int distance [] = new int [nbSommets+1];
        for(int i = 1; i< distance.length ; i++ ){
            if(i == source.id){
                distance[i] = 0;  
            }
            distance[i] = Integer.MAX_VALUE;
        }
        listePrio.insertion(source, 0);
        
        while (!listePrio.estVide()) {
            //extraction du minimum dans la file de priorite
            Sommet u = listePrio.extraireMin();
            for(Sommet voisin : u.voisins){
                if(distance[voisin.id]> distance[voisin.id]+ distance[u]){
                    
                    distance[voisin.id] = distance[voisin.id]+ distance[u];
                    listePrio.insertion(voisin, distance[voisin.id]);
                }
            }
        }
        return distance ;
    }
}
