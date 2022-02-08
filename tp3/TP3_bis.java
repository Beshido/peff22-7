import java.util.List;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TP3_bis {
    static String res = "";

  public static void main(String[] args) {
    List<String> list = FilesOp2.readFile(args[0]);
    // System.out.println(list);
    int nbMots = parseInt(list.get(0));
    String resString = "";

    char[] chars = makeAlphabet(motsARechercher(list));
    int size = sizeIs(motsARechercher(list));
    for ( int  i = 1; i < size; i++) {
        concatene(chars, i, new char[i], 0);
    }
      int t [] = TP3.positionChaine(reserch(res, motsARechercher(list)));
      int k=0;
      for (String s : motsARechercher(list)) {
          if(res.substring(t[1]-1).indexOf(s)==1){
              k=s.length();
          }
      }
      // System.out.println(k);
      t[1]+=k;
      resString = res.substring(t[0],t[1]);
    System.out.println(resString);
      FilesOp2.writeFile(resString,t[0],"out2");
    res = "";

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

    public static void concatene(char[] chars, int size, char[] build, int pos){
        if (pos == size) {
            String word = new String (build);
            res = res + word;
            return;
        }

        for (char aChar : chars) {
            build[pos] = aChar;
            concatene(chars, size, build, pos + 1);
        }
    }

    public static char[] makeAlphabet (String[] WordsList){
      List<Character> listAlphabet = new ArrayList<Character>();
      
      for(String word : WordsList) {
          for (int i = 0; i < word.length(); i++) {
              if (!listAlphabet.contains(word.charAt(i))) {
                  listAlphabet.add(word.charAt(i));
              }
          }
      }

      char[] tabAlphabet = new char[listAlphabet.size()];
      for(int i = 0; i<tabAlphabet.length;i++){
        tabAlphabet[i] = listAlphabet.get(i);
      }
    return tabAlphabet;
  }

  public static int sizeIs(String[] WordsList){
      int i = 0;
      for(String word : WordsList){
          i = i + word.length();
      }
      return i;
  }
}
