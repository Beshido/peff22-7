import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Drone extends CarryingObjects {
  public double timeLeft;
  public int maxWeigth;
  public List<Point> targetLocation;
  public Drone(int x, int y, int maxW, int timeLeft){
    super(x,y);
    maxWeigth=maxW;
    this.timeLeft=timeLeft;
    targetLocation = new ArrayList<Point>();
  }
  public void move(){
    //TODO update time
    double dist = Math.sqrt(
    Math.pow(targetLocation.get(0).getX()-currentLocation.getX(),2) +
    Math.pow(targetLocation.get(0).getY()-currentLocation.getY(),2));
    timeLeft-=dist;
    if(timeLeft>=0){
      currentLocation = targetLocation.get(0);
      targetLocation.remove(0);
    }
  }
}
