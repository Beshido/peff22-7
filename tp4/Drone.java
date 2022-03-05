import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Math;

public class Drone extends CarryingObjects {
    private static int cptId=0;
    // private int id;
    public int timeLeft;
    public static int maxWeigth;
    private List<Mission> currentsMissions;
    private boolean over;
    public Drone(int x, int y, int timeLeft){
        super(x,y);
        this.timeLeft=timeLeft;
        currentsMissions = new ArrayList<Mission>();
        over=false;
        id=cptId++;
    }

    //Time fct -----------------------------------------------------------------
    private int getMoveTime(){
        double dist = Math.sqrt(
                Math.pow(currentsMissions.get(0).getX()-currentLocation.getX(),2) +
                Math.pow(currentsMissions.get(0).getY()-currentLocation.getY(),2));
        return (int)(dist+1);
    }

    private int getMoveTime(Mission mission){
        double dist = Math.sqrt(
                Math.pow(mission.currentLocation.getX()-currentLocation.getX(),2) +
                Math.pow(mission.currentLocation.getY()-currentLocation.getY(),2));
        return (int)(dist+1);
    }
    private static int getLoadTime(){return 1;}
    private static int getDeliverTime(){return 1;}
    private int getMissionTime(){
      return getMoveTime()+getLoadTime()+getDeliverTime(); //+ load & deliver
    }
    public boolean isOver(){return over;}

    public String toString(){
      return "D"+super.toString()+" timeLeft:"+timeLeft+" currentsMissions:"+currentsMissions+" over:"+over;
    }

    //Tools to choose what to do -----------------------------------------------
    //returns the number of the nearest warehouse
    private int nearestWarehouse(Warehouse[] tab){
        int nearest =  -1;
        int distance = Integer.MAX_VALUE ;
        for(int i = 0; i < tab.length; i++ ){
            int tmp = (int) Math.sqrt(
                Math.pow(tab[i].currentLocation.getX()-currentLocation.getX(),2) +
                Math.pow(tab[i].currentLocation.getY()-currentLocation.getY(),2));
            if(tmp < distance){ //  && tab[i].haveAtLease1Item(currentsMissions.get(0))
                distance = tmp ;
                nearest = i;
            }
        }
        return nearest;
    }

    //returns the nearest mission whose warehouse contains all the items
    private Mission getBestMission(){
        Warehouse[] tab = TP4.getWareHouseList();
        int currentWH = nearestWarehouse(tab);
        Warehouse w = tab[currentWH];
        Mission best=null;
        int bestTime = Integer.MAX_VALUE;
        System.out.println();
        System.out.println("Choix d'une mission parmi "+TP4.getMissions().size());
        if(TP4.getMissions().size()==0){return null;}

        for(Mission mission: TP4.getMissions()){
            int tmp = getMoveTime(mission)+getLoadTime()+getDeliverTime();
            if(tmp < bestTime){
                if(w.haveAllItem(mission)){
                    bestTime = tmp;
                    best = mission;
                }
            }
        }
        System.out.println(best+" can be done with only item of "+w);
        TP4.getMissions().remove(best);
        // System.out.println(best);
        // if(best==null){ //TODO que faire si les object ne sont pas a l'entrepots
        //   best = new Mission(TP4.getWareHouseList().get(1));
        // }
        // System.out.println(best);
        return best;
     }

    //Actions functions --------------------------------------------------------
    //Do a mission, starting at the warehouse.
    public void doAMission(){
        Mission m = getBestMission();
        if(m==null){over=true;return;}
        currentsMissions.add(m); //in the futur we can add severaly mission
        while(currentsMissions.size()!=0){ //execute all mission without going back.
            load();
            move();
            deliver();
            if(isOver()){break;}
        }
        //TODO TP4.getWareHouseList()
        Warehouse[] tab = TP4.getWareHouseList();
        int warehouseId = nearestWarehouse(tab);
        currentsMissions.add(new Mission(tab[warehouseId].currentLocation)); //new mission with no load & deliver
        move(); //return to the warehouse by doing the mission move.
        if(timeLeft<1){
          over=true;
        }
    }
    // Function to load DOING
    private void load(){
        int warehouseId = nearestWarehouse(TP4.getWareHouseList());
        Warehouse w = TP4.entrepots[warehouseId];

        Mission mission = currentsMissions.get(0);
        this.currentLocation = mission.currentLocation;
        timeLeft-=getMoveTime();
        if(timeLeft>=0){
            //charger les éléments en fonction de la mission choisi
            int maxsize;
            HashMap<Integer, Integer>  list = new HashMap<>();
            // for (int i=0; i<mission.listOfObject.size(); i++) {
            for(Integer objectId : mission.listOfObject.keySet()){
                // System.out.println("Voulu : "+objectId+" en quantité "+mission.listOfObject.get(objectId)+" pour mission "+mission);
                //TODO use maxsize
                // maxsize += TP4.objectsWeights[objectId];
                for (int i=0; i<mission.listOfObject.get(objectId); i++) {
                  if(w.transfereTo(this, objectId)){
                    sendCommand("L "+warehouseId+" "+objectId+" "+mission.listOfObject.get(objectId));
                  }else{
                    load();
                  }
                }
            }
            // while(maxsize < maxWeigth);
            this.listOfObject = list;
        }
        if(timeLeft<1){
          over=true;
        }
    }

    // Function to deliver OK
    private void deliver(){
        if(currentsMissions.size()!=0){
            timeLeft-=getDeliverTime();
            Mission m = currentsMissions.get(0);
            for (int objectId : m.listOfObject.keySet()) {
              int nbrObject = m.listOfObject.get(objectId);
              for (int i=0; i<nbrObject; i++) {
                if(this.transfereTo(null, objectId) && m.transfereTo(null, objectId)){
                  sendCommand("D "+m.getId()+" "+objectId+" "+m.listOfObject.get(objectId));
                }
              }
            }
            System.out.println(m+" done");
            if(timeLeft>=0){
                currentLocation = m.currentLocation;
            }
        }
        if(currentsMissions.get(0).isDone()){
          currentsMissions.remove(0);
        }
    }
    // Function to move OK
    private void move(){
        timeLeft-=getMoveTime();
        if(timeLeft>=0){
            currentLocation = currentsMissions.get(0).currentLocation;
        }
    }
    /** Write command in the .out file*/
    private void sendCommand(String s){
      TP4.nbrOfDronesCmds++;
      s=id+" "+s+"\n";
      TP4.dronesCmds+=s;
    }
}
