import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Warehouse extends Carryingobjet{
    public int x;
    public int y;


    public Warehouse(int x, int y, Map <Integer, Integer> stock){
        Point location = new Point (this.x, this.y);
        this.stock = stock;
    }
    
}
