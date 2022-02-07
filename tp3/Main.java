import java.util.List;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Main {
  public static void main(String[] args) {
    List<String> list = FilesOp.readFile(args[0]);
    //System.out.println(list);
    int nbMots = parseInt(list.get(0));
    String chaineLongue = list.get(nbMots+1);
    System.out.println(chaineLongue);

  }

  public String[] motsARechercher (String[] texte){
     int n = parseInt(texte[0]);
     String[] res = new String [n];
     for(int i = 0; i < n; i++ ){
       res[i] = texte[i+1];
     }
     return res;
  }
  public List<List<Integer>> reserch(String chaineLongue, String mots[]){
    List<List<Integer>> listOfList = new ArrayList<List<Integer>>();
    for (String mot : mots) {
      listOfList.add(makeIntList(chaineLongue, mot));
    }
    return listOfList;
  }
    public ArrayList<Integer> makeIntList(String texteSeul, String mot){
        ArrayList<Integer> res = new ArrayList();
        int a = texteSeul.indexOf(mot);
        while(a < texteSeul.length() && a != -1){
            res.add(a);
            a = texteSeul.indexOf(mot, a+ mot.length());
        }
        return res;
    }

}
