import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
    public static Map<Integer, Integer> colorInfo(List<Domino> dominos){
        Map<Integer, Integer> colors = new HashMap<Integer, Integer>();
        for (Domino d : dominos) {
            if(colors.containsKey(d.couleur)){
                colors.put(d.couleur, colors.get(d.couleur)+1);
            }else{
                colors.put(d.couleur, 1);
            }
        }
        return colors;
    }
    public static Map<Integer, Integer> numberInfo(List<Domino> dominos){
        Map<Integer, Integer> numbers = new HashMap<Integer, Integer>();
        for (Domino d : dominos) {
            for (int side : d.getSide()) {
                if(numbers.containsKey(side)){
                    numbers.put(side, numbers.get(side)+1);
                }else{
                    numbers.put(side, 1);
                }
            }
        }
        return numbers;
    }
}
