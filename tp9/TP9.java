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
        // System.out.println(dominoes);
        System.out.println(colorInfo(dominoes));
        System.out.println(numberInfo(dominoes));
        int[][] resultat = solutionAlban(dominoes, maxL);
        // print resultat
        for(int i = 0; i < maxL; i++){
            for(int j = 0; j < nbDominos; j++){
                if(resultat[i][j] != -10){
                    System.out.print(resultat[i][j] + " ");
                }
            }
            System.out.println();
        }

        
    }

    public static int[][] solutionAlban (List <Domino> liste, int maxL) {
        int[][] resultat = new int[maxL][liste.size()];
        // fill resultat with val -10
        for(int i = 0; i < maxL; i++){
            for(int j = 0; j < liste.size(); j++){
                resultat[i][j] = -10;
            }
        }
        Domino domino = liste.get(0);
        int incrementer = 0;
        for(int i =1;i<liste.size();i++){
            if (liste.get(i).haut == domino.bas){
                resultat [incrementer][incrementer] = liste.get(i).id;
                resultat [incrementer][incrementer+1] = domino.id;
                incrementer=incrementer+2;
                resultat[incrementer-1][0] = -1;
                liste.remove(i);
                liste.remove(0);
                domino = liste.get(0);

            }
            else if (liste.get(i).bas == domino.haut){
                resultat [incrementer][incrementer] = domino.id;
                resultat [incrementer][incrementer+1] = liste.get(i).id;
                incrementer=incrementer+2;
                resultat[incrementer-1][0] = -1;
                liste.remove(i);
                liste.remove(0);
                domino = liste.get(0);

            }
            if(incrementer == maxL){
                break;
            }
        }

        return resultat;
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
    public static int [][] toDominoId(Domino [][] td){
        int len1=td.length;
        int len2=td[0].length;
        int [][] ti = new int[len1][len2];
        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                ti[i][j]=td[i][j].id;
            }
        }
        return ti;
    }
}
