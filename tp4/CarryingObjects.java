import java.awt.*;
import java.util.HashMap;

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
        for(int i = 0; i< tab.length; i++){
            listOfObject.put(i,tab[i]);
        }
    }
    public void addObject(int objectId){
      if(listOfObject.containsKey(objectId)){
        listOfObject.put(objectId, listOfObject.get(objectId)+1);
      }else{
        listOfObject.put(objectId, 1);
      }
    }

    public boolean transfereTo(CarryingObjects co, int objectId){
      try {
        System.out.print("Transfere: "+toString()+" to "+co.toString()+" of "+objectId);
      }catch (Exception e) {
        System.out.print("Transfere: "+toString()+" to "+"null"+" of "+objectId);
      }
      if(listOfObject.containsKey(objectId) && listOfObject.get(objectId)>0){
        listOfObject.put(objectId, listOfObject.get(objectId)-1);
        if(co!=null){
          co.addObject(objectId);
        }
        System.out.println(" DONE.");
        return true;
      }else{
        System.out.println(" FAIL.");
        return false;
      }
    }
}
