public class exo4{

    public static int[] EquartSorted(int[] Entree, int[]Sortie){
        int[]tab= new int [Entree.length];
        for(int i=0;i<Entree.length;i++){
            tab[i]=(Sortie[i]-Entree[i]);
        }
        java.util.Arrays.sort(tab);
        return tab;

    }
    public static int calculEquart(int[]tabEquart){
        int max = 0;
        int count = 0;
        for (int i = 0; i < tabEquart.length; i++) {
            int num = tabEquart[i];
        if (num == max) {
            count++;
        } else if (num > max) {
        max = num;
        count = 1;
        }
    }return max;
}

    public static void main(String[] args) {

        
    }
}