import java.util.List;

public class exo3{
    public static void main(String[] args) {
        List<String> list = parser.readFile(args[0]);
        int ecart = Integer.parseInt(list.get(0));
        int bougieFille = Integer.parseInt(list.get(1));
        int bougieGarcon = Integer.parseInt(list.get(2));
        int res = calculFinal(ecart, bougieFille, bougieGarcon);
        System.out.println(res);
    }

    public static int realBougie(int age){
        int i = 0;
        int j = 4;
        while (j < age){
            i = i+j;
            j++;
        }
        return i;
    }

    public static int calculAgeMax(int bougieFille){
        int ageMax = 4;
        int i = 0;
        while( i < bougieFille){
                i=+ageMax;
                ageMax++;
        }
        return ageMax;
    }


    public static int calculFinal(int ecart, int bougieFille, int bougieGarcon) {
        int res = 0;
        int ageMaxFille = calculAgeMax(bougieFille);
        int ageGarcon = ageMaxFille - ecart;
        int realBougieFille = realBougie(bougieFille);
        int realBougieGarcon = realBougie(bougieGarcon + ageGarcon);
        int bougieEcart = realBougieFille - realBougieGarcon;
        res = bougieEcart / 2;
        if ((bougieFille == realBougieFille + res) && (bougieGarcon == realBougieGarcon - res)) {
            return res;
        } else {
            do {
                res = 0;
                ageMaxFille = ageMaxFille - 1;
                ageGarcon = ageMaxFille - ecart;
                realBougieFille = realBougie(bougieFille);
                realBougieGarcon = realBougie(bougieGarcon + ageGarcon);
                bougieEcart = realBougieFille - realBougieGarcon;
                res = bougieEcart / 2;
            } while ((bougieFille != realBougieFille + res) && (bougieGarcon != realBougieGarcon - res));
        }
        return res;
    }
}