import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Main {
  public static void main(String[] args) {
    List<String> list = FilesOp.readFile(args[0]);
    System.out.println(list);

  }

  public String[] motARechercher (String[] texte){
     int n = parseInt(texte[0]);
     String[] res = new String [n];
     for(int i = 0; i < n; i++ ){
       res[i] = texte[i+1];
     }
     return res;
  }

  public ArrayList<Integer> makeIntList(String texteSeul, String mot){
      ArrayList<Integer> res = new ArrayList();
      int a = texteSeul.indexOf(mot);
      while(a < texteSeul.length()){
            res.add(a);
            a = texteSeul.indexOf(mot, a+ mot.length());
      }
      return res;
  }
}
