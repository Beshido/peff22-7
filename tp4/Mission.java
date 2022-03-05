import java.awt.Point;
import java.util.Map.Entry;

public class Mission extends CarryingObjects {
    private static int cptId=0;
    // private int id;
    public int getId(){return id;}

    public Mission(int x, int y){
        super(x,y);
        id=cptId++;
    }
    public Mission(Point p){
        super(p);
        id=cptId++;
    }

    //checks that the warehouse has the objects of the mission
    public boolean objectsInWarehouse(int warehouseId, Warehouse[] tab ){
        for(Entry<Integer,Integer> missionEntry : listOfObject.entrySet()){
            if(!tab[warehouseId].listOfObject.containsKey(missionEntry.getKey()) ||
                tab[warehouseId].listOfObject.get(missionEntry.getValue()) == 0){
                    return false;
            }
        }
        return true;
   }
   public boolean isDone(){
     return listOfObject.size()==0;
   }

}
