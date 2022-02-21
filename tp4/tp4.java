import java.util.HashMap;
import java.util.List;

class tp4{
    public static void main(String[] args) {
        List<String> list = parsertp4.readFile(args[0]);
        int nbRow = Integer.parseInt((list.get(0).split(" "))[0]);
        System.out.println("Row : "+ nbRow);
        int nbColumns = Integer.parseInt((list.get(0).split(" "))[1]);
        System.out.println("Columns : "+ nbColumns);
        int nbdrones = Integer.parseInt((list.get(0).split(" "))[2]);
        System.out.println("Drones : "+ nbdrones);
        int nbTurns = Integer.parseInt((list.get(0).split(" "))[3]);
        System.out.println("Turns : "+ nbTurns);
        int MaxPayload = Integer.parseInt((list.get(0).split(" "))[4]);
        System.out.println("Payload max : "+ MaxPayload);
        int[] ObjectsW = new int[Integer.parseInt(list.get(1))];
        for(int i = 0;i< ObjectsW.length;i++){
                ObjectsW[i] = Integer.parseInt((list.get(2).split(" "))[i]);
                System.out.println("Object numero "+i+" poids : "+ObjectsW[i]+"u");
        }
        Warehouse[] entrepots = new Warehouse[Integer.parseInt(list.get(3))+1];
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
        
    }
}