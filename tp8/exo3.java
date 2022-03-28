import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class exo3{
    public static void main(String[] args) throws IOException {
        for(int fnum = 0; fnum < args.length;fnum++){

            List<String> list = parser.readFile(args[fnum]);
            String filename = args[0];
            filename = filename.substring(0, filename.lastIndexOf('.'));
            int ecart = Integer.parseInt(list.get(0));
            int bougieFille = Integer.parseInt(list.get(1));
            int bougieGarcon = Integer.parseInt(list.get(2));
            Integer res = calculFinal(ecart, bougieFille, bougieGarcon);
            outWrite(filename,res.toString());
        }
    }

    public static int realBougie(int age, int j){
        int i = 0;
        while (j <= age){
            i = i+j;
            j++;
        }
        return i;
    }

    public static int calculAgeMax(int bougieFille){
        int ageMax = 4;
        int i = 4;
        while( i < bougieFille){
                i = i +ageMax;
                ageMax++;
        }
        return ageMax-1;
    }


    public static int calculFinal(int ecart, int bougieFille, int bougieGarcon) {
        int ageMaxFille = calculAgeMax(bougieFille);
        int ageGarcon = ageMaxFille - ecart;
        int realBougieFille = realBougie(ageMaxFille, 4);
        int realBougieGarcon = realBougie(ageGarcon, 3);
        int res = bougieFille - realBougieFille;
        if ((bougieFille == realBougieFille + res) && (bougieGarcon == realBougieGarcon - res)) {
            return res;
        } else {
            do {
                res = 0;
                ageMaxFille = ageMaxFille - 1;
                ageGarcon = ageMaxFille - ecart;
                realBougieFille = realBougie(ageMaxFille, 4);
                realBougieGarcon = realBougie(ageGarcon, 3);
                res = bougieFille - realBougieFille;
            } while ((bougieFille != realBougieFille + res) || (bougieGarcon != realBougieGarcon - res));
        }
        return res;
    }

    public static void outWrite(String str1, String str2) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(str1+".out"));
        writer.write(str2);
        writer.close();
    }
}