import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TP4{
    public static Warehouse[] entrepots;
    public static void main(String[] args) {
        List<String> list = parsertp4.readFile(args[0]);
        int nbRow = Integer.parseInt((list.get(0).split(" "))[0]);
        System.out.println("Row : "+ nbRow);
        int nbColumns = Integer.parseInt((list.get(0).split(" "))[1]);
        System.out.println("Columns : "+ nbColumns);
        int nbDrones = Integer.parseInt((list.get(0).split(" "))[2]);
        System.out.println("Drones : "+ nbDrones);
        int nbTurns = Integer.parseInt((list.get(0).split(" "))[3]);
        System.out.println("Turns : "+ nbTurns);
        int maxDroneWeigth = Integer.parseInt((list.get(0).split(" "))[4]);
        System.out.println("Payload max : "+ maxDroneWeigth);
        Drone.maxWeigth=maxDroneWeigth;
        int[] ObjectsW = new int[Integer.parseInt(list.get(1))];
        for(int i = 0;i< ObjectsW.length;i++){
                ObjectsW[i] = Integer.parseInt((list.get(2).split(" "))[i]);
                System.out.println("Object numero "+i+" poids : "+ObjectsW[i]+"u");
        }
        entrepots = new Warehouse[Integer.parseInt(list.get(3))+1];
        int compteur = 0;
        for(int i = 4; i< Integer.parseInt(list.get(3))*2+3;i = i+2){
            entrepots[compteur] = new Warehouse(Integer.parseInt(list.get(i).split(" ")[0]), Integer.parseInt(list.get(i).split(" ")[1]));
            //System.out.println(entrepots[i-4].currentLocation.getY());
            int[] objs = new int[list.get(i+1).split(" ").length];
            for(int j = 0 ; j< list.get(i+1).split(" ").length; j ++){
                objs[j] = Integer.parseInt(list.get(i+1).split(" ")[j]);
            }
            entrepots[compteur].initializedObjects(objs);

            compteur++;
        }
        int endLine = Integer.parseInt(list.get(3))*2+1+3;
        for(int i = endLine+1 ; i < Integer.parseInt(list.get(endLine))*3+endLine+1;i = i+3){
           // System.out.println(list.get(i));
           // System.out.println(list.get(i+1));
           // System.out.println(list.get(i+2));

        }
        runTheDrone(nbDrones, nbTurns);
    }
    public static Warehouse [] getWareHouseList(){
        return entrepots;
    }
    public static void runTheDrone(int nbDrones, int nbTurns){
        Set<Drone> listDrone = new HashSet<Drone>();
        for (int i=0; i<nbDrones; i++) {
            int x = (int)entrepots[0].currentLocation.getX();
            int y = (int)entrepots[0].currentLocation.getY();
            listDrone.add(new Drone(x,y,nbTurns));
        }
        System.out.println(listDrone);
        // TODO launch a mission for the drone that have the more time to use.
        // while(listDrone.size()>0){
        //     for (drone : listDrone) {
        //         drone.doAMission();
        //         if(drone.isOver()){
        //           //TODO remove drone when it have not enoth time for an other mission.
        //         }
        //     }
        // }
    }
}
