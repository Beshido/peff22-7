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
    public static int n;
    public static int iterationTab[];
    public static void main(String[] args) {
        List<String> list = parsertp7.readFile(args[0]);
        nbNotes = Integer.parseInt((list.get(0).split(" "))[0]);
        int longSeq = Integer.parseInt((list.get(0).split(" "))[1]);
        System.out.println(nbNotes + " " +longSeq);
        int mEnt[] = new int[nbNotes+1];
        for(int i = 1; i < nbNotes;i++){
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
        double fTab[] = new double[nbNotes+1];
        for(int i = 1; i<nbNotes;i++){
            fTab[i] = mEnt[i]/sommeT;
        }
        iterationTab = new int[nbNotes+1];

        n=0;

        while(isAllOk(iterationTab, fTab)){
            if(updateIteration(iterationTab, fTab) == -1){
                break;
            }
            n++;
        }
        System.out.println(n);
    }

    public static int ajouteNb (int[] iterationTab, double[] fTab){
        int nb = -1;
        boolean isChange = false;
        double min = calculateValueInInterval(fTab[1], n , iterationTab[1]);
        for(int i = 2; i<fTab.length - 1; i++) {
             double tmp = calculateValueInInterval(fTab[i], n , iterationTab[i]);
             if(tmp < min){
                 if(isOk(fTab[i], n , iterationTab[i])) {
                     min = tmp;
                     isChange = true;
                     nb = i;
                 }
             }
        }
        if(!isChange){
            if(isOk(fTab[1], n , iterationTab[1])){
                nb = 1;
            }
        }
        return nb;
    }

    public static int updateIteration (int[] iterationTab, double[] fTab){
        int n = ajouteNb(iterationTab, fTab);
        if( n != -1) {
            iterationTab[n] = +1;
        }
            return n;
    }

    public static double calculateValueInInterval(double fi, int n, double si){
        double val = n*fi;
        return si - val -1;
    }
    public static boolean isOk(double fi, int n, double si){
        double valInInterval=calculateValueInInterval(fi, n, si);
        if (!(valInInterval>0 && valInInterval<2)) {System.out.println(valInInterval+" < "+si+" < "+(valInInterval+2)+" Not ok");}
        return (valInInterval>0 && valInInterval<2);
    }
    public static boolean isAllOk(int [] iterationTab, double [] fTab){
        for (int i=1; i<nbNotes; i++) {
            if(!isOk(fTab[i],n,iterationTab[i])){return false;}
        }
        return true;
    }

}
