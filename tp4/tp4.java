import java.util.List;

class tp4{
    public static void main(String[] args) {
        List<String> list = parsertp4.readFile(args[0]);
        int nbRow = Integer.parseInt((list.get(0).split(" "))[0]);
        System.out.println("Row : "+ nbRow);
        int nbColumns = Integer.parseInt((list.get(0).split(" "))[1]);
        System.out.println("Columns : "+ nbColumns);
        int nbdrones = Integer.parseInt((list.get(0).split(" "))[2]);
        System.out.println("Row : "+ nbdrones);
        int nbTurns = Integer.parseInt((list.get(0).split(" "))[3]);
        System.out.println("Row : "+ nbTurns);
        int MaxPayload = Integer.parseInt((list.get(0).split(" "))[4]);
        System.out.println("Row : "+ MaxPayload);
        int[] ObjectsW = new int[Integer.parseInt(list.get(1))];
        for(int i = 0;i< ObjectsW.length;i++){
                ObjectsW[i] = Integer.parseInt((list.get(2).split(" "))[i]);
                System.out.println("Object numero "+i+" poids : "+ObjectsW[i]+"u");
        }
        
        
    }
}