import java.awt.Point;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Drone extends CarryingObjects {
    private static int cptId=0;
    // private int id;
    public int timeLeft;
    public static int maxWeigth;
    public List<Mission> currentsMissions;
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
      return "D"+super.toString()+" timeLeft:"+timeLeft+" currentsMissions:"+currentsMissions+" over:"+over+" weigth:"+getWeigth()+"/"+maxWeigth;
    }

    //Tools to choose what to do -----------------------------------------------
    public int getWeigth(){
      int x=0;
      for (int objectId : listOfObject.keySet()) {
        for (int i=0; i<listOfObject.get(objectId); i++) {
          x+=TP4.objectsWeights[objectId];
        }
      }
      return x;
    }
    //returns the number of the nearest warehouse
    //on retourne la WareHouse la plus proche, si currentWH est != -1
    // on retourne la plus proche differente de currentWH
    private int nearestWarehouse(Warehouse[] tab, int currentWH){
      //TODO si mission.get(0)!=null trouvé un entrepot qui contienne l'objet.
        int nearest = -1;
        int distance = Integer.MAX_VALUE ;
        for(int i = 0; i < tab.length; i++ ){
            Warehouse w = tab[i];
            if(w.isOver()){
              // System.out.println("OVER :"+w);
              continue;}
            int tmp = (int) Math.sqrt(
                Math.pow(w.currentLocation.getX()-currentLocation.getX(),2) +
                Math.pow(w.currentLocation.getY()-currentLocation.getY(),2));
            if(currentWH == -1 || i != currentWH){
              if(tmp < distance){ //  && tab[i].haveAtLease1Item(currentsMissions.get(0))
                distance = tmp ;
                nearest = i;
              }
            }

        }
        // System.out.println("FIND W"+nearest);
        // try {
        //   int z=0;
        //   z=1/z;
        // }catch (Exception e) {
        //   e.printStackTrace();
        // }
        return nearest;
    }
    private int nearestWarehouse(Warehouse tab[]){return nearestWarehouse(tab, -1);}

    private void moveTo(Warehouse w){
      // System.out.println("MOVE "+this+" move to "+w+" "+(!w.currentLocation.equals(currentLocation)));
      if(w.currentLocation.equals(currentLocation)){return;}
      try {
        if(haveAllItem(currentsMissions.get(0))){return;}
      }catch (Exception e) {}
      currentsMissions.add(0,new Mission(w.currentLocation));
      move(); //allez a w.
    }
    //TODO chercher une mission ou une partie et dans l'entrepot
    //TODO couper en 2 mission
    //Allez a l'entrepot suivant (qui permet de commencer une mission)
    //Warehouse.canDoAMission() for(Mission in TP4.getMissions()){if(this.haveAtLease1Item()){return true;}}
    //splitIn2Mission(); //renvoie une mission faisable et TP4.getMissions().add(l'autre mission)
    //Use w.haveAllItem(Mission.listOfObject)

    //returns the nearest mission whose warehouse contains all the items
    private void addBestMission(int cwh){
        Warehouse[] tab = TP4.getWareHouseList();
        int currentWH;
        if(cwh == -1){
           currentWH = nearestWarehouse(tab, -1);
        }else{
          currentWH = cwh;
        }
        if(currentWH==-1){over=true;return;}
        Warehouse w = tab[currentWH];
        moveTo(w);
        Mission best=null;
        int bestTime = Integer.MAX_VALUE;
        // System.out.println("\nChoix d'une mission parmi "+TP4.getMissions().size());//@z
        if(TP4.getMissions().size()==0){over=true;return;}

        for(Mission mission: TP4.getMissions()){
            int tmp = getMoveTime(mission)+getLoadTime()+getDeliverTime();
            if(tmp < bestTime){
                if(w.haveAllItem(mission)){
                    bestTime = tmp;
                    best = mission;
                }
            }
        }
        // currentsMissions.add(new Mission(TP4.getWareHouseList().get(1).curentLocation));
        // System.out.println(best+" can be done with only item of "+w);//@z
        // System.out.println(best);
        //TODO que faire si les object ne sont pas a l'entrepots
        // Des idées :
        // soit on vas a un autre entrepots qui en a plus.
        // soit on en charge une partie if (haveAtLease1Item()==true)
        // soit on découpe la mission en 2 plus petite en fonction de ce que contient cet entrepot.
        if(best==null){
            for(Mission mission: TP4.getMissions()){
                int tmp = getMoveTime(mission)+getLoadTime()+getDeliverTime();
                if(tmp < bestTime){
                    if(w.haveAtLease1Item(mission)){
                      // System.out.println(w+" haveAtLease1Item of "+mission);
                        bestTime = tmp;
                        best = mission;
                    }
                }
            }
            if(best!=null){
              splitIn2Missions(w,best);
              // System.out.println(best+" splited in 2 mission because "+w+" don't have all");
              // System.out.println(this);
              // System.out.println(TP4.getMissions());
              TP4.getMissions().remove(best);
              return;
            }
        }
        if(best==null){
          //TODO aller a l'entrepot suivant
          // System.out.println("--------------- Going to next Warehouse");
          int nextWH = nearestWarehouse(tab,currentWH);
          moveTo(tab[nextWH]);
          addBestMission(nextWH);
          return;
        }
        if(best==null){
          System.out.println("Error in chosing mission");
        }
        // System.out.println(best);
        if(best==null){
          over=true;
          return;
        }
        TP4.getMissions().remove(best);
        currentsMissions.add(best); //in the futur we can add several mission
     }
     // SPLIT 2 MISSIONS
    public void splitIn2Missions( Warehouse currentWH, Mission mission){
        // HashMap<Integer, Integer>  list1 = new HashMap<>();
        // HashMap<Integer, Integer>  list2 = new HashMap<>();
        Mission mission1 = new Mission((int) mission.getX(), (int) mission.getY());
        Mission mission2 = new Mission((int) mission.getX(),(int) mission.getY());
        //TODO les missions doivent conservé le même id pour le fichier de test.
        for(Integer objectId : mission.listOfObject.keySet()) {
            int objectInM = (int) mission.listOfObject.get(objectId);
            if ((currentWH.listOfObject.containsKey(objectId))) {
                int objectInW = (int) currentWH.listOfObject.get(objectId);
                if (objectInW >= objectInM) {
                    // list1.put(objectId, objectInM);
                    mission1.addObject(objectId, objectInM);
                } else {
                    // list1.put(objectId, (objectInW));
                    // list2.put(objectId, (objectInM - objectInW));
                    mission1.addObject(objectId, objectInW);
                    mission2.addObject(objectId, objectInM - objectInW);
                }
            } else{
                // list2.put(objectId, objectInM);
                mission2.addObject(objectId, objectInM);
            }
        }
        // Mission mission1 = new Mission((int) mission.getX(), (int) mission.getY());
        // mission1.initializedObjectsHashmap(list1);
        // Mission mission2 = new Mission((int) mission.getX(),(int) mission.getY());
        // mission2.initializedObjectsHashmap(list2);
        // this.currentsMissions.remove(0);
        mission1.idM=mission.getIdM();
        mission2.idM=mission.getIdM();
        this.currentsMissions.add(mission1);
        TP4.getMissions().add(mission2);

    }

    //Actions functions --------------------------------------------------------
    //Do a mission, starting at the warehouse.
    public void doAMission(){
        addBestMission(-1);
        // System.out.println("Mission ADD to "+this);
        while(currentsMissions.size()!=0){ //execute all mission without going back.
            load();
            move();
            deliver();
            if(isOver()){break;}
        }
        // Warehouse[] tab = TP4.getWareHouseList();
        // int warehouseId = nearestWarehouse(tab);
        // moveTo(tab[warehouseId]);
        // if(warehouseId<0){
        //   over=true;
        //   return;
        // }
        // currentsMissions.add(new Mission(tab[warehouseId].currentLocation)); //new mission with no load & deliver
        // move(); //return to the warehouse by doing the mission move.
        // if(timeLeft<1){
        //   over=true;
        // }
    }
    // Function to load DOING
    private void load(){
        int warehouseId = nearestWarehouse(TP4.getWareHouseList());
        if(warehouseId<0){
          over=true;
          return;
        }
        Warehouse w = TP4.entrepots[warehouseId];
        moveTo(w);

        Mission mission = currentsMissions.get(0);
        // this.currentLocation = mission.currentLocation;
        // timeLeft-=getMoveTime();
        if(timeLeft>=0){
            //charger les éléments en fonction de la mission choisi
            int maxsize;
            // HashMap<Integer, Integer>  list = new HashMap<>();
            // for (int i=0; i<mission.listOfObject.size(); i++) {
            for(Integer objectId : mission.listOfObject.keySet()){
                // System.out.println("Voulu a "+timeLeft+" : "+objectId+" en quantité "+mission.listOfObject.get(objectId)+" pour mission "+mission);
                //TODO use maxsize
                // maxsize += TP4.objectsWeights[objectId];
                for (int i=0; i<mission.listOfObject.get(objectId); i++) {
                  if(w.transfereTo(this, objectId)){
                    sendCommand("L "+warehouseId+" "+objectId+" 1");
                    // sendCommand("L "+warehouseId+" "+objectId+" "+mission.listOfObject.get(objectId));
                  }else{
                    // System.out.println("unable to transfere "+objectId+" to "+this+" from "+w);//@z
                  }
                }
            }
            // System.out.println(this+" loaded");//@z
            // while(maxsize < maxWeigth);
            // this.listOfObject = list;
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
            List<Integer> listToRemove = new LinkedList<Integer>();
            for (int objectId : m.listOfObject.keySet()) {
              int nbrObject = m.listOfObject.get(objectId);
              for (int i=0; i<nbrObject; i++) {
                // System.out.println("Transfere "+objectId+" from "+this+" & "+m);//@z
                if(this.transfereTo(null, objectId)){
                  // sendCommand("D "+m.getIdM()+" "+objectId+" "+Math.max(m.listOfObject.get(objectId),listOfObject.get(objectId)));
                  sendCommand("D "+m.getIdM()+" "+objectId+" 1");
                  listToRemove.add(objectId);
                }else{
                  // System.out.println("fail to transfere "+objectId+" from "+this+" & "+m);//@z
                }
              }
            }
            for (int objectId : listToRemove) {
              m.transfereTo(null, objectId);
            }
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
