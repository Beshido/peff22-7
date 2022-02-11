import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import static java.lang.Integer.parseInt;

public class TP3_bis {

  public static void main(String[] args) {
    List<String> list = FilesOp2.readFile(args[0]);
    Set<String> set = new HashSet<String>();
    list.remove(0);
    for (String s : list) {
      set.add(s);
    }
    while(set.size()>1){
      // System.out.println(set);
      fuze2CloserString(set);
    }
    // System.out.println(set);
    // for (String s : motsARechercher(list)) {
    //   System.out.print(s+", ");
    // }
    // System.out.println();
    // String res = result(motsARechercher(list));
    // System.out.println(res);
    String res = null;
    for (String s : set) {
      res=s;
    }
    FilesOp2.writeFile(res,res.length(),"out2");
  }
  public static void fuze2CloserString(Set<String> set){
    String s1=null;
    String s2=null;
    int actualLen=Integer.MAX_VALUE;
    //find best merge to do
    for (String s : set) {
      if(s1==null){
        s1=s;
      }else if(s2==null){
        s2=s;
        actualLen = getCommunLetterCpt(s1, s2);
      }else{
        if(getCommunLetterCpt(s1,s)<actualLen){
          s1=s;
          actualLen=getCommunLetterCpt(s1,s);
        }else if(getCommunLetterCpt(s2,s)<actualLen){
          s2=s;
          actualLen=getCommunLetterCpt(s2,s);
        }
      }
    }
    //do the merge
    String s3 = shorterMerge(s1, s2);
    set.remove(s1);
    set.remove(s2);
    set.add(s3);
  }
  public static int getCommunLetterCpt(String s1, String s2){
    int len1 = s1.length();
    int len2 = s2.length();
    int x=0;
    while(s1.charAt(x)==s2.charAt(len2-1-x)){
      x++;
    }
    int y=0;
    while(s1.charAt(len1-1-y)==s2.charAt(y)){
      y++;
    }
    if(x>y){return x;}
    else{return y;}
  }

  public static String shorterMerge(String s1, String s2){
    String s3 = concatene(s1,s2);
    String s4 = concatene(s2,s1);
    if(s4.length()<s3.length()){s3=s4;}
    return s3;
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

  // public static String concatene ( String mot1, String mot2) {
  //     //return mot if it's an easy cas.
  //     if (mot1.contains(mot2)) {
  //         return mot1;
  //     }else if (mot2.contains(mot1)) {
  //         return mot2;
  //     }else if (mot1.charAt(0)!=mot2.charAt(mot2.length()-1) && mot2.charAt(0)!=mot1.charAt(mot1.length()-1)) {
  //       return mot1+mot2;
  //     }
  //     String fin_mot2 = "";
  //     for (int i = mot2.length()-1; i > 0; i--) {
  //         fin_mot2 += mot2.charAt(i);
  //         String tmp = mot2.substring(0,i-1);
  //         if (mot1.contains(tmp)) {
  //             return mot1+reverse(fin_mot2);
  //         }
  //     }
  //       return mot1+mot2;
  // }
  public static String concatene ( String mot1, String mot2) {
      if (mot1.contains(mot2)) {
          return mot1;
      }
      String fin_mot2 = "";
      for (int i = mot2.length()-1; i > 0; i--) {
          fin_mot2 += mot2.charAt(i);
          String tmp = mot2.substring(0,i);
          if (mot1.contains(tmp)) {
              return mot1+reverse(fin_mot2);
          }
      }
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
