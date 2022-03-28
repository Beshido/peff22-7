import java.util.ArrayList;
import java.util.List;

public class exo4{

    public static List<Integer> EquartSorted(int[] Entree, int[]Sortie){
        List<Integer> a = new ArrayList<Integer>();
        for(int i=0;i<Entree.length;i++){
            if (Sortie[i]>Entree[i]){
            a.add(Sortie[i]-Entree[i]);
        }
    }
        a.sort((Integer x, Integer y) -> y-x);
        return a;

    }
    public static int calculEquart(List<Integer>tabEquart){
        int max = 0;
        int count = 0;
        int val =tabEquart.get(0);
        int trucARetenir=0;
        
        for(int i=0;i<tabEquart.size();i++){
            if(val!=tabEquart.get(i)){
            val = tabEquart.get(i);
            count = 1;
            }
            if(max< count){
            max=count;
            trucARetenir=tabEquart.get(i);
            }
            else count++;   
            
        }
    return trucARetenir;
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
        
        System.out.println(calculEquart(EquartSorted(NtempsTab, MtempsTab)));  
    }
}