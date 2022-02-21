import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Drone extends CarryingObjects {
    public int timeLeft;
    public int maxWeigth;
    public List<Point> targetLocation;
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
    public void load(Warhouse warhouse){

    }
    public void deliver(Mission m){
        // TODO removeItemOf(m);
        if(targetLocation.length()==0){
            targetLocation.add(getCloserWarhouse());
        }
    }

}
