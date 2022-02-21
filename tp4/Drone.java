import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Drone extends CarryingObject {
  public int maxWeigth;
  public List<Point> targetLocation;
  public Drone(int x, int y, int maxW){
    super(x,y, new ArrayList());
    maxWeigth=maxW;
    List<Point> = new ArrayList();
  }
}
