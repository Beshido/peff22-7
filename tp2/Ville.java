import java.util.List;
import java.util.HashMap;

public class Ville {
  private String nom;
  private int credit [];
  public Map<String, Ville> nextVille;
  public Ville(String in){
    String t [] = in.split(" ");
    nom = t[0];
    credit = new int [3];
    for (int i=0; i<3; i++) {
      credit[i]=Integer.valueOf(t[i+1]);
    }
    nextVille = new HashMap<String, Ville>();
  }
  public String toString(){
    String r =  nom;
    for (int i : credit) {
      r+=" "+i;
    }
    return r;
  }
}
