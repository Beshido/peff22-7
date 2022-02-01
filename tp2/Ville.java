import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Ville {
  private String nom;
  private int credit [];
  public Map<Ville, Integer> nextVille;
  public Ville(String in){
    String t [] = in.split(" ");
    nom = t[0];
    credit = new int [3];
    for (int i=0; i<3; i++) {
      credit[i]=Integer.valueOf(t[i+1]);
    }
    nextVille = new HashMap<Ville, Integer>();
  }
  public String toString(){
    String r =  nom;
    for (int i : credit) {
      r+=" "+i;
    }
    return r;
  }
  public int getDistanceBus(int id){
    return credit[id];
  }
  public Ville findBestCity(){
    for (Ville v : nextVille.keySet()) {
      return v;
    }
    return null;
  }
}
