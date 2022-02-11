import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import static java.lang.Integer.parseInt;

public class TP3_bis {

  public static void main(String[] args) {
    // System.out.println(2==communLetterCpt("ab","aab"));
    // System.out.println(2==communLetterCpt("aab","ab"));
    // System.out.println(1==communLetterCpt("ba","aab"));
    // System.out.println(mergeString("ab", "aab").equals("aab"));
    // System.out.println(mergeString("ba", "aab").equals("aaba"));
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
  private static int communLetterCpt1(String s1, String s2){
    int k=0;
    int len2 = s2.length();
    int len = s1.length();
    if(len>len2){len=len2;}
    int communChar=0;
    while(k<len){
      k++;
      // System.out.println(k+" compare "+s1.substring(0,k)+" "+s2.substring(len2-k,len2));
      if(s1.substring(0,k).equals(s2.substring(len2-k,len2))){
        communChar=k;
      }
    }
    return communChar;
  }
  private static int communLetterCpt(String s1, String s2){
    if(s1.contains(s2)){return s2.length();}
    else if(s2.contains(s1)){return s1.length();}
    int x = communLetterCpt1(s1,s2);
    int x2 = communLetterCpt1(s2,s1);
    if(x2>x){x=x2;}
    return x;
  }
  private static String mergeString(String s1, String s2, int communChar){
    if(s1.contains(s2)){return s1;}
    else if(s2.contains(s1)){return s2;}
    String s4=s1.substring(0,communChar);
    if(s2.contains(s4)){
      return s2+s1.substring(communChar);
    }else{
      return s1+s2.substring(communChar);
    }
  }




  public static void fuze2CloserString(Set<String> set){
    String s1=null;
    String s2=null;
    int communLetter=-1;
    //find best merge to do
    for (String s : set) {
      if(s1==null){
        s1=s;
      }else if(s2==null){
        s2=s;
        communLetter = communLetterCpt(s1, s2);
      }else{
        if(communLetterCpt(s1,s)>communLetter){
          // if(communLetterCpt(s2,s)>communLetterCpt(s1,s)){
          //   communLetter=communLetterCpt(s2,s);
          //   s1=s;
          // }else{
            communLetter=communLetterCpt(s1,s);
            s2=s;
          // }
        }else if(communLetterCpt(s2,s)>communLetter){
            communLetter=communLetterCpt(s2,s);
            s1=s;
        }
      }
    }
    //do the merge
    // System.out.println(communLetter+" between "+s1+" "+s2);
    String s3 = mergeString(s1, s2, communLetter);
    set.remove(s1);
    set.remove(s2);
    set.add(s3);
  }
  public static int getCommunLetterCpt(String s1, String s2){
    int len1 = s1.length();
    int len2 = s2.length();
    int len = len1;
    if(len>len2){len=len2;}
    int x=0;
    String s1Rev = reverse(s1);
    String s2Rev = reverse(s2);
    while(x<len && s1.charAt(x)==s2Rev.charAt(x)){
      x++;
    }
    int y=0;
    while(y<len && s2.charAt(y)==s1Rev.charAt(y)){
      y++;
    }
    // System.out.println(x+" for "+s1+" "+s2Rev);
    // System.out.println(y+" for "+s2+" "+s1Rev);
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
      }else if (mot2.contains(mot1)) {
          return mot2;
      }
      // else if (mot1.charAt(0)!=mot2.charAt(mot2.length()-1) && mot2.charAt(0)!=mot1.charAt(mot1.length()-1)) {
      //   return mot1+mot2;
      // }
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
