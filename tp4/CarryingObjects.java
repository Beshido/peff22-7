import java.awt.*;
import java.util.HashMap;

public class CarryingObjects{
    public Point currentLocation;
    public HashMap<Integer, Integer> listOfObject;
    public double getX(){return currentLocation.getX();}
    public double getY(){return currentLocation.getY();}

    public CarryingObjects(int x, int y){
        currentLocation = new Point(x,y);
        listOfObject = new HashMap<>();
    }
    public CarryingObjects(Point p){
        this((int)p.getX(), (int)p.getY());
    }

    public String toString(){
      return "("+(int)currentLocation.getX()+" "+(int)currentLocation.getY()+") "+listOfObject;
    }

    public void initializedObjects(int[] tab){
        for(int i = 0; i< tab.length; i++){
            listOfObject.put(i,tab[i]);
        }
    }
}
