import java.util.List;

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
}
