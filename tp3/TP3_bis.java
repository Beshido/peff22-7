import java.util.List;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TP3_bis {

  public static void main(String[] args) {
    List<String> list = FilesOp2.readFile(args[0]);
      String res = result(motsARechercher(list));
      FilesOp2.writeFile(res,res.length(),"out2");
  }


  public static String[] motsARechercher (List<String> texte){
     int n = parseInt(texte.get(0));
     String[] res = new String [n];
     int i=0;
     for (String s : texte) {
       if(i>0 && i<=n){
         res[i-1] = s;
       }
       i++;
     }
     return res;
  }

    public static String reverse(String input){
        char[] in = input.toCharArray();
        int begin=0;
        int end=in.length-1;
        char temp;
        while(end>begin){
            temp = in[begin];
            in[begin]=in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }

  public static String concatene ( String mot1, String mot2) {
      if (mot1.contains(mot2)) {
          return mot1;
      }
      String fin_mot2 = "";
      for (int i = mot2.length()-1; i > 0; i--) {
          fin_mot2 += mot2.charAt(i);
          String tmp = mot2.substring(0,i);
          if (mot1.contains(tmp)) {
              System.out.println(reverse(fin_mot2));
              return mot1+reverse(fin_mot2);
          }
      }
      System.out.println("pas de concatenation possible");
        return mot1+mot2;
  }

  public static String result (String[] mots){
      for(int i = 0; i<mots.length - 1; i++){
          String motA = mots[i];
          String motB = mots[i+1];
          String resultA = concatene(motA,motB);
          String resultB = concatene(motB,motA);
          if((resultA.length()) < (resultB.length())){
              mots[i+1] = resultA;
          }else{
              mots[i+1] = resultB;
          }
      }
      return mots[mots.length -1];
  }
}
