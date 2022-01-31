import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class Ville {
  public String nom;
  public int credit [];
  public List<Ville> nextVille;
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
  public int getDistanceBus(int id){
    return credit[id];
  }
}
