import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Drone extends CarryingObjects {
    public int timeLeft;
    public int maxWeigth;
    public List<Point> targetLocation; //TODO plutot qu'une liste de point ou aller, il faudrait prendre les missions pour savoir a qui distribuer quoi.
    public Drone(int x, int y, int maxW, int timeLeft){
        super(x,y);
        maxWeigth=maxW;
        this.timeLeft=timeLeft;
        targetLocation = new ArrayList<Point>();
    }
    // Function to move
    public void move(){
        timeLeft-=getMoveTime();
        if(timeLeft>=0){
            currentLocation = targetLocation.get(0);
            targetLocation.remove(0);
        }
    }
    public int getMoveTime(){
        double dist = Math.sqrt(
                Math.pow(targetLocation.get(0).getX()-currentLocation.getX(),2) +
                Math.pow(targetLocation.get(0).getY()-currentLocation.getY(),2));
        return (int)(dist+1);
    }
    public int getMissionTime(){
        return getMoveTime()+2; //+ load & deliver
    }

    //renvoie le numero de l'entrepot le plus proche
    public int nearestWarehouse(Warehouse[] tab){
        int nearest =  -1;
        int distance = Integer.MAX_VALUE ;
        for(int i = 0; i < tab.length; i++ ){
            int tmp = (int) Math.sqrt(
                Math.pow(tab[i].currentLocation.getX()-currentLocation.getX(),2) +
                Math.pow(tab[i].currentLocation.getY()-currentLocation.getY(),2));
            if(tmp < distance ){
                distance = tmp ;
                nearest = i;
            }

        }

        return nearest;
    }
    public void load(Warehouse warehouse){
        //TODO trouver la meilleurs mission en fonction des stocks de l'entrepot.
        //TODO charger les éléments en fonction de la mission choisi
    }
    public void deliver(Mission m){
        // TODO removeItemOf(m);
        if(targetLocation.size()==0){
            //TODO faire la fct getCloserWarhouse()
            // targetLocation.add(getCloserWarhouse());
        }
    }

}
