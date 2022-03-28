import java.util.List;

public class exo3{
    public static void main(String[] args) {
        List<String> list = parser.readFile(args[0]);
        int ecart = Integer.parseInt(list.get(0));
        int bougieFille = Integer.parseInt(list.get(1));
        int bougieGarcon = Integer.parseInt(list.get(2));
        int res = calculFinal(ecart, bougieFille, bougieGarcon);
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
}