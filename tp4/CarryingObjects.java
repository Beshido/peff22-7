import java.awt.*;
import java.util.HashMap;

public class CarryingObjects{
    public Point currentLocation;
    public HashMap<Integer, Integer> listOfObject;
    public double getX(){return currentLocation.getX();}
    public double getY(){return currentLocation.getY();}

    public CarryingObjects(int x, int y){
        Point p = new Point(x,y);
        currentLocation = p;
        listOfObject = new HashMap<>();
    }

    public void initializedObjects(int[] tab){
        for(int i = 0; i< tab.length; i++){
            listOfObject.put(i,tab[i]);
        }
    }
}
