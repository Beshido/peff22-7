import java.awt.Point;
import java.util.Map.Entry;

public class Mission extends CarryingObjects {

    public Mission(int x, int y){
        super(x,y);
    }
    public Mission(Point p){
        super(p);
    }

    //checks that the warehouse has the objects of the mission
    public boolean objectsInWarehouse(int warehouseId, Warehouse[] tab ){
        for(Entry<Integer,Integer> missionEntry : listOfObject.entrySet()){
            if(!tab[warehouseId].listOfObject.containsKey(missionEntry.getKey()) ||
                tab[warehouseId].listOfObject.get(missionEntry.getKey()) == 0){
                    return false;
            }
        }
        return true;
   }

}
