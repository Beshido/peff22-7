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
    public static void main(String[] args) {
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
            System.out.println(sommets[nomPoint].voisin);

        }

    }
}
