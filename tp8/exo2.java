import java.util.List;

public class exo2{
    private static int n;
    private static int a[];
    private static int b[];
    private static long res[];
    private static int alen;
    private static int blen;
    public static void main(String[] args) {
        List<String> list = parser.readFile(args[0]);
        n = Integer.parseInt(list.get(0));
        String as [] = list.get(1).split(" ");
        String bs [] = list.get(2).split(" ");
        // int a = Integer.parseInt(list.get(1));
        // int bougieGarcon = Integer.parseInt(list.get(2));
        a=new int[as.length];
        b=new int[bs.length];
        int k=0;
        for (String s : as) {
            a[k]=Integer.parseInt(s);
            k++;
        }
        k=0;
        for (String s : bs) {
            b[k]=Integer.parseInt(s);
            k++;
        }
        alen=a.length;
        blen=b.length;
        res = new long[3];
        res[0]=0;
        res[1]=0;
        res[2]=0;
        calculateSurface();
        String sol="";
        for (long i : res) {
            sol+=i+" ";
        }
        sol=sol.substring(0,sol.length()-1)+'\n';
        System.out.print(sol);
        parser.writeFile(sol, args[0].replaceFirst("[.][^.]+$", ".out"));
    }
    public static void calculateSurface(){
        for (int j=0; j<blen; j++) {
            for (int i=0; i<alen; i++) {
                res[(i+j+2)%3]+=a[i]*b[j];
            }
        }
    }
}
