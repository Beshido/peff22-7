import java.awt.*;
import java.util.HashMap;

public class CarryingObjects{
        Point currentLocation;
        HashMap<Integer, Integer> listOfObject;

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
