import java.awt.Point;

public class Mission extends CarryingObjects {

    public Mission(int x, int y){
        super(x,y);
    }
    public Mission(Point p){
        super(p);
    }
  
    public boolean objectsInWarehouse(int warehouseId,Warehouse[] tab ){
        for(Map.Entry missionEntry : listOfObject.entrySet()){
            if(!tab[warhouseId].containsKey(missionEntry.getKey()) || 
                tab[warhouseId].get(missionEntry.getKey()) == 0){
                    return false;
            }
        }
        return true;
   }

}
