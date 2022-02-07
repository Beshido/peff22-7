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
    // System.out.println(reserch(chaineLongue, motsARechercher(list)));
    String resString = "";
    int t [] = positionChaine(reserch(chaineLongue, motsARechercher(list)));
    // System.out.println(t[0]);
    // System.out.println(t[1]);
    int k=0;
    for (String s : motsARechercher(list)) {
      if(chaineLongue.substring(t[1]-1).indexOf(s)==1){
        k=s.length();
      }
    }
    // System.out.println(k);
    t[1]+=k;
    resString = chaineLongue.substring(t[0],t[1]);
    // System.out.println(resString);
    resString = resString.length()+"\n"+t[0]+"\n"+resString+"\n";
    FilesOp.writeFile(resString,"out");

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
        ArrayList<Integer> res = new ArrayList<Integer>();
        // System.out.println(mot);
        if(mot==null){return res;}
        int a = texteSeul.indexOf(mot);
        while(a < texteSeul.length() && a != -1){
            res.add(a);
            a = texteSeul.indexOf(mot, a+ mot.length());
        }
        return res;
    }



  public static int[] positionChaine(List<List<Integer>>list){
    int nbrMot = list.size();
    int listSize = Integer.MAX_VALUE;
    int indiceDeb=0;
    // System.out.println(list);
    // int debut= list.get(0).get(0);
    // int fin= list.get(1).get(0);

    int tTemp [] = new int [nbrMot];
    for (int i=0; i<nbrMot; i++) {
      tTemp[i]=list.get(i).get(0);
      list.get(i).remove(0);
    }
    while(true){
      int min = min(tTemp);
      int listSizeTemp = max(tTemp) - min;
      if(listSizeTemp<listSize){
        indiceDeb=min;
        listSize=listSizeTemp;
      }
      for (int i=0; i<nbrMot; i++) {
        if(tTemp[i]==min){
          try {
            tTemp[i]=list.get(i).get(0);
            list.get(i).remove(0);
          }catch (Exception e) {
            // return listSize;
            // int listDernierMot=0;
            int [] tab = {indiceDeb, indiceDeb+listSize};
            // return {indiceDeb, indiceDeb+listSize};
            return tab;
          }
        }
      }
    }
  }
  public static int max(int t[]){
    int m=t[0];
    for (int i : t) {
      if(i>m){
        m=i;
      }
    }
    return m;
  }
  public static int min(int t[]){
    int m=t[0];
    for (int i : t) {
      if(i<m){
        m=i;
      }
    }
    return m;
  }

}
