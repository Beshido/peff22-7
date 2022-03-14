import java.util.HashMap;

public class Sommet {
    public static Sommet ARBITRE;
    public boolean estJoueur;
    public int id;
    public HashMap<Integer, Integer> voisins;

    public Sommet(boolean b, int id){
        estJoueur = b;
        this.id = id;
        voisins = new HashMap<>();
    }

    public void fillHashMap (int sommet, int poids){
        voisins.put(sommet,poids);
    }
}
