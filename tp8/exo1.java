import java.util.List;

public class exo1{
    public static void main(String[] args) {
        List<String> list = parser.readFile(args[0]);
        int largeur = Integer.parseInt(list.get(0));
        int nbPieces = Integer.parseInt(list.get(1));
        int[][] pieces = new int[nbPieces+2][2];
        for(int i = 2; i < nbPieces+2; i++){
            String[] tab = list.get(i).split(" ");
            pieces[i][0] = Integer.parseInt(tab[0]);
            pieces[i][1] = Integer.parseInt(tab[1]);
        }
    }
}