import java.util.HashSet;
import java.util.Set;

public class Equipe {

    public static Set<Equipe> EQUIPES;
    public static int tToPlayer[];
    public static int tToArbitre[];
    private Set<Integer> equipeMember;
    private int costToGoToEveryMember;

    private Equipe(){
        equipeMember= new HashSet<Integer>();
        costToGoToEveryMember=0;
    }
    private int getCostToAddInTeam(int playerId){
        // System.out.println("cost="+costToGoToEveryMember+"+"+(equipeMember.size()*tToArbitre[playerId]));//@a
        int cost=costToGoToEveryMember;
        cost+=equipeMember.size()*tToArbitre[playerId];
        return cost;
    }
    private void addPlayer(int playerId){
        equipeMember.add(playerId);
        costToGoToEveryMember+=tToPlayer[playerId];
    }
    @Override
    public String toString(){
        return equipeMember.toString();
    }

    //static -------------------------------
    //Main function to split player in nbjoueurs different team.
    public static void addAllPlayer(int tToArbitre[], int tToPlayer[], int nbjoueurs, int nbequipes){
        Equipe.tToArbitre=tToArbitre;
        Equipe.tToPlayer=tToPlayer;
        EQUIPES = new HashSet<Equipe>();
        for (int i=0; i<nbequipes; i++) {
            EQUIPES.add(new Equipe());
        }
        for (int i=1; i<nbjoueurs+1; i++) {
            addPlayerToBestEquipe(i);
        }
        // System.out.println(EQUIPES);
    }

    private static void addPlayerToBestEquipe(int playerId){
        Equipe eq = null;
        int score = Integer.MAX_VALUE;
        // System.out.println("Chosing between "+EQUIPES.size()+" Equipes");
        for (Equipe equipe : EQUIPES) {
            int scoreTemp = equipe.getCostToAddInTeam(playerId);
            if(scoreTemp<score){
                score=scoreTemp;
                eq = equipe;
            }
        }
        // System.out.println("Choose "+eq+" with score "+score);
        if(eq!=null){
            eq.addPlayer(playerId);
        }else{
            System.out.println("Error");
        }
    }
    /**
    *@return The cost for 1 team
    */
    public int getEquipeCost(){
        int cost = 0;
        for (Integer playerId : equipeMember) {
            cost+=tToArbitre[playerId]*(equipeMember.size()-1); //GO to the ARBITRE n-1 times
            cost+=costToGoToEveryMember-tToPlayer[playerId]; //Go to every other player.
        }
        return cost;
    }
    /**
    *@return The full cost for all the equipes.
    */
    public static int getFullEquipeCost(){
        int cost=0;
        for (Equipe eq : EQUIPES) {
            cost+=eq.getEquipeCost();
        }
        return cost;
    }
}
