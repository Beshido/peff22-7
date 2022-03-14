import java.util.HashSet;
import java.util.Set;

public class Equipe {

    public static Set<Equipe> EQUIPES;
    private Set<Integer> equipeMember;
    private int costToGoToEveryMember;

    private Equipe(){
        equipeMember= new HashSet<Integer>();
        costToGoToEveryMember=0;
    }
    private int getCostToAddInTeam(int tToARbitre[], int tToPlayer[], int playerId){
        int cost=costToGoToEveryMember;
        cost+=equipeMember.size()*tToARbitre[playerId];
        return cost;
    }
    private void addPlayer(int playerId, int tToPlayer[]){
        equipeMember.add(playerId);
        costToGoToEveryMember+=tToPlayer[playerId];
    }

    //static
    public static void addAllPlayer(int tToARbitre[], int tToPlayer[], int nbjoueurs){
        EQUIPES = new HashSet<Equipe>();
        for (int i=1; i<nbjoueurs+1; i++) {
            addPlayerToBestEquipe(tToARbitre, tToPlayer, i);
        }
    }

    private static void addPlayerToBestEquipe(int tToARbitre[], int tToPlayer[], int playerId){
        Equipe eq = null;
        int score = Integer.MAX_VALUE;
        for (Equipe equipe : EQUIPES) {
            int scoreTemp = equipe.getCostToAddInTeam(tToARbitre, tToPlayer, playerId);
            if(scoreTemp<score){
                score=scoreTemp;
                eq = equipe;
            }
        }
        eq.addPlayer(playerId, tToPlayer);
    }
}
