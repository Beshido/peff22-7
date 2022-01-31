import java.util.List;

public class Ville {
  private String nom;
  private int credit [];
  private List<Ville> nextVille;
  public Ville(String in){
    String t [] = in.split(" ");
    nom = t[0];
    credit = new int [3];
    for (int i=0; i<3; i++) {
      credit[i]=Integer.valueOf(t[i+1]);
    }
  }
  public String toString(){
    String r =  nom;
    for (int i : credit) {
      r+=" "+i;
    }
    return r;
  }
}
