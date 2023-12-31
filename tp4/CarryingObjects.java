import java.awt.*;
import java.util.HashMap;
import java.util.Map.Entry;


public class CarryingObjects{
    private static int cptId=0;
    protected int id;
    public Point currentLocation;
    public HashMap<Integer, Integer> listOfObject; //<Id des object, nombres d'objet>
    public double getX(){return currentLocation.getX();}
    public double getY(){return currentLocation.getY();}

    public CarryingObjects(int x, int y){
        currentLocation = new Point(x,y);
        listOfObject = new HashMap<>();
        id=cptId++;
    }
    public CarryingObjects(Point p){
        this((int)p.getX(), (int)p.getY());
    }

    public String toString(){
      return id+": ("+(int)currentLocation.getX()+" "+(int)currentLocation.getY()+") "+listOfObject;
    }

    public void initializedObjects(int[] tab){
        if(this instanceof Drone){return;}
        for(int i = 0; i< tab.length; i++){
            listOfObject.put(i,tab[i]);
        }
    }
    public void initializedObjectsHashmap(HashMap<Integer, Integer> h){
        listOfObject = h;
    }
    public void addObject(int objectId){
      if(this instanceof Drone){
        Drone dr = (Drone)this;
        // if(dr.getWeigth()+TP4.objectsWeights[objectId]>=Drone.maxWeigth/2){
        //   return;
        // }
        if(objectId==741){
          System.out.println(this);
          System.out.println(TP4.objectsWeights[objectId]);
        }
      }
      if(listOfObject.containsKey(objectId)){
        listOfObject.put(objectId, listOfObject.get(objectId)+1);
      }else{
        listOfObject.put(objectId, 1);
      }
    }
    public void addObject(int objectId, int quantity){
      for (int i=0; i<quantity; i++) {
        addObject(objectId);
      }
    }

    public boolean transfereTo(CarryingObjects co, int objectId){
      if(co instanceof Drone){
        Drone dr = (Drone)co;
        if(dr.getWeigth()+TP4.objectsWeights[objectId]>Drone.maxWeigth){
          return false;
        }
      }
      // try {
      //   System.out.print("Transfere: "+toString()+" to "+co.toString()+" of "+objectId);
      // }catch (Exception e) {
      //   System.out.print("Transfere: "+toString()+" to "+"null"+" of "+objectId);
      // }
      if(listOfObject.containsKey(objectId) && listOfObject.get(objectId)>0){
        listOfObject.put(objectId, listOfObject.get(objectId)-1);
        if(listOfObject.get(objectId)<=0){
          listOfObject.remove(objectId);
        }
        if(co!=null){
          co.addObject(objectId);
        }
        // System.out.println(" DONE.");
        return true;
      }else{
        // System.out.println(" FAIL.");
        return false;
      }
    }
    public boolean haveAtLease1Item(CarryingObjects co){
      for (int objectId : co.listOfObject.keySet()) {
        if(this.listOfObject.containsKey(objectId) && this.listOfObject.get(objectId)>0){
          return true;
        }
      }
      return false;
    }
    //checks that this have all the objects of co
    public boolean haveAllItem(CarryingObjects co){
      // System.out.println(this +" have all item of "+co+" ???");
      //   for(Entry<Integer,Integer> missionEntry : co.listOfObject.entrySet()){
      //       if(!listOfObject.containsKey(missionEntry.getKey()) ||
      //           listOfObject.get(missionEntry.getValue()) == 0){
      //               return false;
      //       }
      //   }
      //   return true;
      for (int objectId : co.listOfObject.keySet()) {
        int wantedQuantity = co.listOfObject.get(objectId);
        if(!this.listOfObject.containsKey(objectId) || wantedQuantity > this.listOfObject.get(objectId)){
          return false;
        }
      }
      return true;
   }
}
