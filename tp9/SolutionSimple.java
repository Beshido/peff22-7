import java.util.LinkedList;
import java.util.List;

public class SolutionSimple {

    public int[][] solution (LinkedList <Domino> liste) {
        int[][] resultat = new int[maxL][liste.size()];
        Domino domino = liste.get(0);
        int incrementer = 0;
        for(int i =1;i<liste.size();i++){
            if (liste.get(i).Haut() == domino.Bas()){
                resultat [incrementer][incrementer] = domino.id;
                resultat [incrementer][incrementer+1] = liste.get(i).id;
                incrementer=incrementer+2;
                resultat[incrementer-1][0] = -1;
                liste.remove(i);
                liste.remove(0);

        }
        if (liste.get(i).Bas() == domino.haut()){
            resultat [incrementer][incrementer] = liste.get(i).id;
            resultat [incrementer][incrementer+1] = domino.id;
            incrementer=incrementer+2;
            resultat[incrementer-1][0] = -1;
            liste.remove(i);
            liste.remove(0);

     }if(incrementer == maxL){
         break;
     }
}

        return resultat;
        }
}
