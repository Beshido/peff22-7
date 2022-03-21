import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
public class TP7{
    public static int nbNotes;
    public static void main(String[] args) {
        List<String> list = parsertp7.readFile(args[0]);
        nbNotes = Integer.parseInt((list.get(0).split(" "))[0]);
        int longSeq = Integer.parseInt((list.get(0).split(" "))[1]);
        System.out.println(nbNotes + " " +longSeq);
        int mEnt[] = new int[nbNotes];
        for(int i = 0; i < nbNotes;i++){
            mEnt[i] = Integer.parseInt((list.get(1).split(" "))[i]);
            //System.out.println(mEnt[i]);
        }
        int seqInit[] = new int[longSeq];
        for(int i = 0; i < longSeq;i++){
            seqInit[i] = Integer.parseInt((list.get(2).split(" "))[i]);
            //System.out.println(seqInit[i]);
        }
        int sommeT = 0;
        for(int i = 0;i<nbNotes; i++){
            sommeT+=mEnt[i];
        }
        double fTab[] = new double[nbNotes];
        for(int i = 0; i<nbNotes;i++){
            fTab[i] = mEnt[i]/sommeT;
        }
        int iterationTab[] = new int[nbNotes];


    }

    public static double calculateValueInInterval(double fi, double n, double si){
        double val = n*fi;
        return si - val -1;
    }
    public static boolean isOk(double fi, double n, double si){
        double valInInterval=calculateValueInInterval(fi, n, si);
        return (valInInterval>0 && valInInterval<2);
    }
    public static boolean isAllOk(double [] fTab, double [] iterationTab){
        int n=0;
        for (int i=1; i<nbNotes; i++) {
            if(!isOk(fTab[i],n,iterationTab[i])){return false;}
        }
        return true;
    }

}
