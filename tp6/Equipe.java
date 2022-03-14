import java.util.HashSet;
import java.util.Set;

public class Equipe {

    public static Set<Equipe> EQUIPES;
    public static int tToPlayer[];
    public static int tToARbitre[];
    private Set<Integer> equipeMember;
    private int costToGoToEveryMember;

    private Equipe(){
        equipeMember= new HashSet<Integer>();
        costToGoToEveryMember=0;
    }
    private int getCostToAddInTeam(int playerId){
        int cost=costToGoToEveryMember;
        cost+=equipeMember.size()*tToARbitre[playerId];
        return cost;
    }
    private void addPlayer(int playerId){
        equipeMember.add(playerId);
        costToGoToEveryMember+=tToPlayer[playerId];
    }

    //static -------------------------------
    //Main function to split player in nbjoueurs different team.
    public static void addAllPlayer(int tToARbitre[], int tToPlayer[], int nbjoueurs){
        Equipe.tToARbitre=tToARbitre;
        Equipe.tToPlayer=tToPlayer;
        EQUIPES = new HashSet<Equipe>();
        for (int i=1; i<nbjoueurs+1; i++) {
            addPlayerToBestEquipe(i);
        }
    }

    private static void addPlayerToBestEquipe(int playerId){
        Equipe eq = null;
        int score = Integer.MAX_VALUE;
        for (Equipe equipe : EQUIPES) {
            int scoreTemp = equipe.getCostToAddInTeam(playerId);
            if(scoreTemp<score){
                score=scoreTemp;
                eq = equipe;
            }
        }
        eq.addPlayer(playerId);
    }
    //get score to calculate score.
    public int getEquipeCost(){
        //TODO doit prendre en compte le cout de tout les membres vers tout les membres au sein de l'équipe.
        int cost = costToGoToEveryMember;
        for (Integer playerId : equipeMember) {
            cost+=tToARbitre[playerId]*equipeMember.size();
        }
        return cost;
    }
    public static int getFullEquipeCost(){
        int cost=0;
        for (Equipe eq : EQUIPES) {
            cost+=eq.getEquipeCost();
        }
        return cost;
    }
}
