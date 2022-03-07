import java.awt.*;
import java.util.HashMap;

public class Warehouse extends CarryingObjects {
    public boolean isOver(){
      for(Mission mission: TP4.getMissions()){
        if(haveAtLease1Item(mission)){
          return false;
        }
      }
      for (Drone d : TP4.listDrone) {
        for(Mission mission: d.currentsMissions){
          if(haveAtLease1Item(mission)){
            return false;
          }
        }
      }
      return true;
    }

    public Warehouse(int x, int y){
        super(x,y);
    }
    @Override
    public String toString(){
      return "W"+super.toString();
    }

}
