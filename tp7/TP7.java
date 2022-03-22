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
    public static int longueurSequenceInit;
    public static int n;
    public static int iterationTab[];
    public static void main(String[] args) {
        Color.iniColor();
        List<String> list = parsertp7.readFile(args[0]);
        nbNotes = Integer.parseInt((list.get(0).split(" "))[0]);
        longueurSequenceInit = Integer.parseInt((list.get(0).split(" "))[1]);
        System.out.println(nbNotes + " " +longueurSequenceInit);
        int mEnt[] = new int[nbNotes+1];
        for(int i = 1; i < nbNotes;i++){
            mEnt[i] = Integer.parseInt((list.get(1).split(" "))[i]);
            //System.out.println(mEnt[i]);
        }
        int seqInit[] = new int[longueurSequenceInit];
        for(int i = 0; i < longueurSequenceInit;i++){
            seqInit[i] = Integer.parseInt((list.get(2).split(" "))[i]);
            //System.out.println(seqInit[i]);
        }
        int sommeT = 0;
        for(int i = 0;i<nbNotes; i++){
            sommeT+=mEnt[i];
        }
        double fTab[] = new double[nbNotes+1];
        for(int i = 1; i<nbNotes;i++){
            fTab[i] = (double)(mEnt[i])/(double)(sommeT); // double !!! sinon ça donne 0 tout le temps par la division entière.
        }
        iterationTab = new int[nbNotes+1];
        for(int i : seqInit){
            iterationTab[i] += 1;
        }

        n=0;

        // for (int i : iterationTab) {
        //     System.out.print(i+" ");
        // }System.out.println();
        // updateIteration(iterationTab, fTab);
        // for (int i : iterationTab) {
        //     System.out.print(i+" ");
        // }System.out.println();
        isAllOk(iterationTab, fTab);
        do{
            if(updateIteration(iterationTab, fTab) == -1){
                System.out.println("out from while because of updateIteration");
                break;
            }
            System.out.println(n+":  ");
            // for (int i : iterationTab) {
            //     System.out.print(i+" ");
            // }System.out.println();
            n++;
            if(!isAllOk(iterationTab, fTab)){
            // isAllOk(iterationTab, fTab);
            // if(n>4){
                System.out.println("out from while because of isAllOk()==false");
                break;
            }
        } while(n<1000);
        if(n>=1000){
            System.out.println("infini");
        }else{
            System.out.println(n); //TODO write sol in a file insted of print it.
        }
    }
    public static int getN(){return n+longueurSequenceInit;}

    public static int ajouteNb (int[] iterationTab, double[] fTab){
        int nb = -1;
        boolean isChange = false;
        double min = calculateValueInInterval(fTab[1], getN(), iterationTab[1]);
        for(int i = 2; i<fTab.length - 1; i++) {
             double tmp = calculateValueInInterval(fTab[i], getN(), iterationTab[i]);
             // System.out.println(tmp);
             if(tmp < min){
                 // if(isOk(fTab[i], getN() , iterationTab[i])) {
                     min = tmp;
                     isChange = true;
                     nb = i;
                 // }
             }
        }
        if(!isChange){
            // if(isOk(fTab[1], getN() , iterationTab[1])){
                nb = 1;
            // }
        }
        System.out.println("to add "+nb+ " because "+min);
        return nb;
    }

    public static int updateIteration (int[] iterationTab, double[] fTab){
        int n = ajouteNb(iterationTab, fTab);
        if( n != -1) {
            // iterationTab[n] = +1; // non !
            iterationTab[n]+=1;
        }
        return n;
    }

    public static double calculateValueInInterval(double fi, int n, double si){
        double val = n*fi;
        return si-val+1;
    }
    public static boolean isOk(double fi, int n, double si){
        double valInInterval=calculateValueInInterval(fi, n, si);
        // if (!(valInInterval>0 && valInInterval<2)) {System.out.println(valInInterval+" < "+si+" < "+(valInInterval+2)+" Not ok");}
        // System.out.println((n*fi-1)+" < "+si+" < "+(n*fi+1)+"  "+(valInInterval>0 && valInInterval<2));
        return (valInInterval>0 && valInInterval<2); // Ce qui serait correcte de faire compte tenu de l'énnoncé.
        // return (valInInterval>=0 && valInInterval<=2);
    }
    public static boolean isAllOk(int [] iterationTab, double [] fTab){
        for (int i=1; i<nbNotes; i++) {
            String col = Color.GREEN;
            if(!isOk(fTab[i],getN(),iterationTab[i])){col=Color.RED;}
            System.out.println(col+"i:"+i+"  n: "+n+"  "+(getN()*fTab[i]-1)+" < "+iterationTab[i]+" < "+(getN()*fTab[i]+1)+Color.NEUTRAL);
        }
        for (int i=1; i<nbNotes; i++) {
            if(!isOk(fTab[i],getN(),iterationTab[i])){
                return false;
            }
        }
        return true;
    }

}
