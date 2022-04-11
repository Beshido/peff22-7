import java.util.*;

public class fonctionAurelie {
    public static boolean colorInLine (Domino[] dominos, int color){
        for(int i = 0; i< dominos.length; i++) {
            if (dominos[i] != null) {
                if (dominos[i].couleur == color) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] oneDomManyCol (List<Domino> dominos, int l){
        Domino[][] res = new Domino[l][dominos.size()];
        int i = l-1;
        int j = 0;
        while((!dominos.isEmpty()) && i>0 && (j < dominos.size()-1)){
            for(Domino domino : dominos){
                if(!colorInLine(res[i],domino.couleur)){
                    res[i][j] = domino;
                    dominos.remove(domino);
                    break;
                }
            }
            i--;
            j++;
        }
        int[][] res2 = new int[l][dominos.size()];
        for(int k = 0; k< res.length; k++){
            for(int f = 0; f < res[k].length; f++){
                if(res[k][f] != null){
                    res2[k][f]=res[k][f].id;
                }
            }
        }
        return res2;
    }


    public static void main(String[] args) {

    }
}
