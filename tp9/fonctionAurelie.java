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
        int[][] res2 = TP9.toDominoId(res);
        return res2;
    }


    public static void main(String[] args) {

    }
}
