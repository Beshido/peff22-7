import java.util.LinkedList;
import java.util.List;

public class TP9{
    public static void main(String[] args) {
        List<String> list = parser.readFile(args[0]);
        List<Domino> dominoes = new LinkedList<>();
        String tab[] = list.get(0).split(" ");
        int nbCouleurs = Integer.parseInt(tab[0]);
        int valeurMax = Integer.parseInt(tab[1]);
        int maxL =  Integer.parseInt(tab[2]);
        int nbDominos = Integer.parseInt(tab[3]);
        for(int i = 0; i < nbDominos; i++){
            tab = list.get(i+1).split(" ");
            Domino d = new Domino(Integer.parseInt(tab[0]), Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]), i);
            dominoes.add(d);
        }
        

    }
}