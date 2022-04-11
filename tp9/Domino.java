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
    public int [] getSide(){
        int t [] = new int[4];
        t[0]=haut;
        t[1]=bas;
        t[2]=gauche;
        t[3]=droite;
        return t;
    }
    public String toString(){
        return id+" col: "+col+" "+haut+" "+droite+" "+bas+" "+gauche;
    }
}
