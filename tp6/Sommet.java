import java.util.HashMap;

public class Sommet {
    public boolean estJoueur;
    public int id;
    public HashMap<Integer, Integer> voisin = new HashMap<>();

    public Sommet(boolean b, int id){
        estJoueur = b;
        this.id = id;
    }

    public void fillHashMap (int sommet, int poids){
        voisin.put(sommet,poids);
    }
}