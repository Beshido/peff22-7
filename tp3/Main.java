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
       if(i>0 && i<n){
         res[i-1] = s;
       }
       i++;
     }
     return res;
  }
  public static List<List<Integer>> reserch(String chaineLongue, String mots[]){
    List<List<Integer>> listOfList = new ArrayList<List<Integer>>();
    for (String mot : mots) {
      listOfList.add(makeIntList(chaineLongue, mot));
    }
    return listOfList;
  }
  public static List<Integer> makeIntList(String s, String s2){return new ArrayList<Integer>();}
}
