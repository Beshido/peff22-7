public class Domino {
    public int haut;
    public int bas;
    public int gauche;
    public int droite;
    public int couleur;
    public int id;
    
    public Domino( int couleur,int haut, int droite, int bas, int gauche,int id) {
        this.haut = haut;
        this.bas = bas;
        this.gauche = gauche;
        this.droite = droite;
        this.couleur = couleur;
        this.id = id;
    }
}
