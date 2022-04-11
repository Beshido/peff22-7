import java.util.LinkedList;

public class fonctionAurelie {
    public static boolean ColorInLine (Domino[] dominos, int color){
        for(int i = 0; i< dominos.length; i++){
            if(dominos[i].couleur == color){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {

    }
}
