import java.awt.*;
import java.util.HashMap;

public class Warehouse extends CarryingObjects{
        Point currentLocation;
        HashMap<Integer, Integer> listOfObject;

    public Warehouse(int x, int y){
        super(x,y);
        Point p = new Point (x,y);
        currentLocation = p;
        listOfObject = null;
    }
    
}
