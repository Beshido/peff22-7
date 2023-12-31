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
        System.out.println("maxL "+maxL+" nbDominos "+nbDominos);
        for(int i = 0; i < nbDominos; i++){
            tab = list.get(i+1).split(" ");
            Domino d = new Domino(Integer.parseInt(tab[0]), Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]), i);
            dominoes.add(d);
        }
        // System.out.println(dominoes);
        System.out.println(colorInfo(dominoes));
        System.out.println(numberInfo(dominoes));
        int[][] resultat = permutation(dominoes, maxL);
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
                resultat [incrementer][incrementer+1] = domino.id;
                resultat [incrementer][incrementer] = liste.get(i).id;
                incrementer=incrementer+2;
                liste.remove(i);
                liste.remove(0);
                domino = liste.get(0);

            }
            else if (liste.get(i).bas == domino.haut){
                resultat [incrementer][incrementer+1] = liste.get(i).id;
                resultat [incrementer][incrementer] = domino.id;
                incrementer=incrementer+2;
                liste.remove(i);
                liste.remove(0);
                domino = liste.get(0);

            }
            if(incrementer == maxL){
                break;
            }
            if(incrementer>0 && (i<liste.size()-1 || incrementer == maxL)){
                resultat[incrementer-1][0] = -1;
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
                if(td[i][j]!=null){
                    ti[i][j]=td[i][j].id;
                }else{
                    ti[i][j]=-10;
                }
            }
        }
        return ti;
    }

    public static boolean colorInLine (Domino[] dominos, int color){
        for(int i = 0; i< dominos.length; i++) {
            if (dominos[i] != null) {
                if (dominos[i].couleur == color) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int[][] oneDomManyCol (List<Domino> dominos, int maxL){
        Domino[][] res = new Domino[maxL][dominos.size()];
        int i = res.length-1;
        int j = 0;
        while((!dominos.isEmpty()) && i>=0){
            boolean haveBeenRemove=false;
            for(Domino domino : dominos){
                if(!colorInLine(res[i],domino.couleur)){
                    res[i][j] = domino;
                    dominos.remove(domino);
                    haveBeenRemove=true;
                    break;
                }
            }
            if(j == res[i].length-1) {
                i--;
                System.out.println("next lines");
                j=0;
            }else {
                j++;
            }
        }
        i = res.length-1;
        j = 0;
        while((!dominos.isEmpty()) && i>=0){
            if(res[i][j]==null){
              boolean haveBeenRemove=false;
              for(Domino domino : dominos){
                  res[i][j] = domino;
                  dominos.remove(domino);
                  haveBeenRemove=true;
                  break;
              }
            }
            if(j == res[i].length-1) {
                i--;
                System.out.println("next lines");
                j=0;
            }else {
                j++;
            }
        }
        int[][] res2 = TP9.toDominoId(res);
        return res2;
    }


    public static int[][] permutation(List<Domino> dominos,int l){
        Domino [][] grille = new Domino [l][dominos.size()];
        int i = l-1;
        int j = 0;
        //tant que l'on peut rajouter des dominos et que l'on a pas atteint le haut de la grille 
        while(!dominos.isEmpty() && i>=0){
            //si on est sur la ligne du bas on met le premier dominos de la liste 
            if(i == l-1){
                grille[i][j] = dominos.remove(0);
                  
            }else{
                // sinon on prend un domino qui peut etre mis au dessus de celui de la ligne du dessous
                for(Domino domino : dominos){
                    if(grille[i+1][j]!= null){

                    
                        if(grille[i+1][j].haut == domino.bas){
                            grille[i][j] = domino;
                            dominos.remove(domino);
                            break;
                            
                         }
                    }
                    
                }
            }
            
            //si le domino courant n'est pas null on pioche un domino dans la liste et on voit si on peut le mettre dans 
            //la case à coté
             if(grille[i][j] !=null){
                for(int k = 0; k < dominos.size();k++){
                
                    if(grille[i][j].droite == dominos.get(k).gauche){
                
                        if(i == l-1||(i != l-1 && grille[i+1][j].haut == dominos.get(k).bas)){
                                grille[i][j+1] = dominos.get(k);
                                dominos.remove(dominos.get(k));
                                j++;
                                
                            }
                            

                        }

                     //lorsque on atteint le bout de la ligne on passe à la ligne du dessus    
                    if(j == grille[i].length-1){
                        i = i-1;
                        j=0;
                        break;
                    }
                }   
             }

        }
        int  [][] res = TP9.toDominoId(grille);
        return res;
    }

}
