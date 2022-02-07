import java.util.List;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Main {
  public static void main(String[] args) {
    List<String> list = FilesOp.readFile(args[0]);
    // System.out.println(list);
    int nbMots = parseInt(list.get(0));
    String chaineLongue = list.get(nbMots+1);
    // System.out.println(chaineLongue);
    System.out.println(reserch(chaineLongue, motsARechercher(list)));

  }

  public static String[] motsARechercher (List<String> texte){
     int n = parseInt(texte.get(0));
     String[] res = new String [n];
     // for(int i = 0; i < n; i++ ){
     int i=0;
     for (String s : texte) {
       if(i>0 && i<=n){
         res[i-1] = s;
       }
       i++;
     }
     return res;
  }
<<<<<<< Updated upstream
  public static List<List<Integer>> reserch(String chaineLongue, String mots[]){
    List<List<Integer>> listOfList = new ArrayList<List<Integer>>();
    for (String mot : mots) {
      listOfList.add(makeIntList(chaineLongue, mot));
    }
    return listOfList;
  }
    public static ArrayList<Integer> makeIntList(String texteSeul, String mot){
        ArrayList<Integer> res = new ArrayList();
        System.out.println(mot);
        if(mot==null){return res;}
        int a = texteSeul.indexOf(mot);
        while(a < texteSeul.length() && a != -1){
            res.add(a);
            a = texteSeul.indexOf(mot, a+ mot.length());
        }
        return res;
    }



  public int[] positionChaine(List<List<Integer>>list){
    int debut= list.get(0).get(0);
    int fin= list.get(0).get(0);

    for(int i=0; i<list.size(); i++){
      for(int j=0; j<list.get(i).get(j);j++){
        if ((fin - debut) < (fin - j)){
          debut = list.get(i).get(j);
        }
        if((fin - debut) < (j - debut)){
          fin = list.get(i).get(j);
        }  
      }
    }
    int [] tab = {debut, fin};
    return tab;  
    

  }

}
