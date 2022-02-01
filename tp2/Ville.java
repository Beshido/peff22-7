import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Ville {
  private String nom;
  private int credit[];
  public Map<Ville, Integer> nextVille;

  public Ville(String in) {
    String t[] = in.split(" ");
    nom = t[0];
    credit = new int[3];
    for (int i = 0; i < 3; i++) {
      credit[i] = Integer.valueOf(t[i + 1]);
    }
    nextVille = new HashMap<Ville, Integer>();
  }

  public String toString() {
    String r = nom;
    for (int i : credit) {
      r += " " + i;
    }
    return r;
  }

  public int getDistanceBus(int id) {
    return credit[id];
  }

  public void setVilleVoisine(HashMap<String, Ville> villes, String s) {
    String ligne[] = s.split(" ");

    this.nextVille.put(villes.get(ligne[1]), Integer.parseInt(ligne[2]));
  }

  public Ville findBestCity() {
    int distanceMin = Integer.MAX_VALUE;
    Ville res = new Ville("");
    for (Ville v : this.nextVille.keySet()) {
      int dist = this.nextVille.get(v);
      if (distanceMin > dist) {
        distanceMin = dist;
        res = v;
      }
    }
    return res;
  }
}
