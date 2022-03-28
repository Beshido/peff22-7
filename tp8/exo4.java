import java.util.List;

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
        List<String> list = parser.readFile(args[0]);
        int N = Integer.parseInt(list.get(0));
        int M = Integer.parseInt(list.get(1));
        String Ntemps = list.get(2);
        String Mtemps = list.get(3);
        int[] NtempsTab = new int[N];
        int[] MtempsTab = new int[M];


        for(int i = 0;i<N;i++){
            NtempsTab[i] = Integer.parseInt(Ntemps.split(" ")[i]);
        }
        for(int i = 0;i<M;i++){
            MtempsTab[i] = Integer.parseInt(Mtemps.split(" ")[i]);
        }

        
    }
}