import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class TP4 {
    public static int[] objectsWeights;
    public static Warehouse[] entrepots;
    // public static Mission[] missions;
    public static List<Mission> missions;
    public static Set<Drone> listDrone;
    public static String dronesCmds="";
    public static int nbrOfDronesCmds=0;

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
        objectsWeights = new int[Integer.parseInt(list.get(1))];
        for(int i = 0;i< objectsWeights.length;i++){
                objectsWeights[i] = Integer.parseInt((list.get(2).split(" "))[i]);
                System.out.println("Object numero "+i+" poids : "+objectsWeights[i]+"u");
        }
        entrepots = new Warehouse[Integer.parseInt(list.get(3))];
        // System.out.println("init entrepots "+entrepots);
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
        // missions = new Mission[Integer.parseInt(list.get(endLine))];
        missions = new LinkedList<Mission>(); //car bcp d'insertion et de supression.
        compteur = 0;
        for(int i = endLine+1 ; i < Integer.parseInt(list.get(endLine))*3+endLine+1;i = i+3){
            Mission m = new Mission(Integer.parseInt(list.get(i).split(" ")[0]), Integer.parseInt(list.get(i).split(" ")[1]));
            int[] objs = new int[Integer.parseInt(list.get(i+1))];
            //int[] objs = new int[list.get(i+2).split(" ").length];
            String t [] = list.get(i+2).split(" ");
            for(int j = 0 ; j< t.length; j ++){
                // objs[j] = Integer.parseInt(list.get(i+2).split(" ")[j]);
                int objectId = Integer.parseInt(t[j]);
                m.addObject(objectId);
            }
            // m.initializedObjects(objs);
            missions.add(m);
            // System.out.println(list.get(i));
            compteur++;
        }
        System.out.println(missions);
        runTheDrone(nbDrones, nbTurns);
        dronesCmds=nbrOfDronesCmds+"\n"+dronesCmds;
        writeFile(dronesCmds, args[0].substring(0, args[0].length()-3)+".out");
    }
    public static Warehouse [] getWareHouseList(){
        return entrepots;
    }
    /** create & launch the drones */
    public static void runTheDrone(int nbDrones, int nbTurns){
        listDrone = new HashSet<Drone>();
        for (int i=0; i<nbDrones; i++) {
            int x = (int)entrepots[0].currentLocation.getX();
            int y = (int)entrepots[0].currentLocation.getY();
            listDrone.add(new Drone(x,y,nbTurns));
        }
        System.out.println(listDrone);
        while(listDrone.size()>0){
            //launch a mission for the drone that have the more time to use.
            Drone droneWithMoreTime=null;
            for (Drone drone : listDrone) {
                //if drone have more time than droneWithMoreTime, update droneWithMoreTime.
                // System.out.println(!drone.isOver() && (droneWithMoreTime==null || droneWithMoreTime.timeLeft < drone.timeLeft));
                if(!drone.isOver() && (droneWithMoreTime==null || droneWithMoreTime.timeLeft < drone.timeLeft)){
                    droneWithMoreTime=drone;
                }
            }
            if(droneWithMoreTime==null){break;}// if all drone are over.
            droneWithMoreTime.doAMission();
        }
    }
    public static List<Mission> getMissions(){
      // System.out.println();
      // System.out.println(Arrays.asList(missions));
      return missions;
    }

    public static boolean writeFile(String content, String nomDuFichier) {
      try {
        BufferedWriter ecriteurAvecBuffer = null;
        String ligne;
        File f = new File(nomDuFichier);
        f.createNewFile(); //it will be crate only if it haven't been yet.
        try {
          ecriteurAvecBuffer = new BufferedWriter(new FileWriter(nomDuFichier, StandardCharsets.UTF_8));
        } catch(FileNotFoundException e) {
          System.out.println("Le fichier n'as pas pu être créer. Le problème peut venir d'un caractère incorecte");
          return false;
        }
        ecriteurAvecBuffer.write(content);
        ecriteurAvecBuffer.close();
      }catch (IOException e) {
        return false;
      }
      return true;
    }
}
