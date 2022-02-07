import java.util.List;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Main2 {
  public static void main(String[] args) {
    List<String> list = FilesOp2.readFile(args[0]);
    // System.out.println(list);
    int nbMots = parseInt(list.get(0));
    System.out.println(list);
    String res ="";
    concatene(char[] chars, size, new char[size], 0 ,res);

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

    public static void concatene(char[] chars, int size, char[] build, int pos, String res){
        if (pos == size) {
            String word = new String (build);
            res = res+word;
            return;
        }

        for (char aChar : chars) {
            build[pos] = aChar;
            concatene(chars, size, build, pos + 1,res);
        }
    }

    public char[] makeAlphabet (String[] WordsList){
      List<char> listAlphabet = new ArrayList<char>();
      
      for(String word : WordsList){
        for(int i = 0; i<word.length;i++){
          if(!alphabet.contains(word.charAt(i))){
            alphabet.add(word.charAt(i));
          }

      char[] tabAlphabet = new char[listAlphabet.size()];
      for(int i = 0; i<tabAlphabet.length;i++){
        tabAlphabet[i] = listAlphabet.get(i);
      }

          
      }
    }
    return tabAlphabet;
  }
}
