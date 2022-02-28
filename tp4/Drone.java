import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Drone extends CarryingObjects {
    private int timeLeft;
    private int maxWeigth;
    private List<Mission> currentsMissions;
    private boolean over;
    public Drone(int x, int y, int maxW, int timeLeft){
        super(x,y);
        maxWeigth=maxW;
        this.timeLeft=timeLeft;
        currentsMissions = new ArrayList<Mission>();
        isOver=false;
    }

    //Time fct -----------------------------------------------------------------
    private int getMoveTime(){
        double dist = Math.sqrt(
                Math.pow(currentsMissions.get(0).getX()-currentLocation.getX(),2) +
                Math.pow(currentsMissions.get(0).getY()-currentLocation.getY(),2));
        return (int)(dist+1);
    }
    private static int getLoadTime(){return 1;}
    private static int getDeliverTime(){return 1;}
    private int getMissionTime(){
      return getMoveTime()+getLoadTime()+getDeliverTime(); //+ load & deliver
    }
    private boolean isOver(){return over;}

    //Tools to choose what to do -----------------------------------------------
    //renvoie le numero de l'entrepot le plus proche
    private int nearestWarehouse(Warehouse[] tab){
        int nearest =  -1;
        int distance = Integer.MAX_VALUE ;
        for(int i = 0; i < tab.length; i++ ){
            int tmp = (int) Math.sqrt(
                Math.pow(tab[i].currentLocation.getX()-currentLocation.getX(),2) +
                Math.pow(tab[i].currentLocation.getY()-currentLocation.getY(),2));
            if(tmp < distance ){
                distance = tmp ;
                nearest = i;
            }

        }

        return nearest;
    }
    private Mission getBestMission(){
        //TODO trouver la meilleurs mission
        //meilleurs mission = celle avec le plus petit getMissionTime() & tout les éléments en stock dans l'entrepot.
        return null;
    }
    //Actions functions --------------------------------------------------------
    //Do a mission, starting at the warehouse.
    public void doAMission(){
        currentsMissions.add(getBestMission()); //in the futur we can add severaly mission
        while(currentsMissions.size()!=0){ //execute all mission without going back.
            load();
            move();
            deliver();
            currentsMissions.remove(0);
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
        //TODO TP4.getWareHouseOnLocation(currentLocation)
        // Warehouse warehouse=TP4.getWareHouseOnLocation(currentLocation);
        timeLeft-=getMoveTime();
        if(timeLeft>=0){
            //TODO charger les éléments en fonction de la mission choisi
        }
    }
    // Function to deliver OK
    private void deliver(){
        if(currentsMissions.size()==0){
          timeLeft-=getDeliverTime();
          if(timeLeft>=0){
              currentLocation = currentsMissions.get(0).currentLocation;
          }
        }
    }
    // Function to move OK
    private void move(){
        timeLeft-=getMoveTime();
        if(timeLeft>=0){
            currentLocation = currentsMissions.get(0).currentLocation;
        }
    }

}
