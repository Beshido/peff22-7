import java.awt.Point;

public class Mission extends CarryingObjects {
    private static int cptId=0;
    // private int id;
    public int getId(){return id;}
    // public int getIdOfficiel(){return id;}

    public Mission(int x, int y){
        super(x,y);
        id=cptId++;
    }
    public Mission(Point p){
        super(p);
        id=cptId++;
    }

    @Override
    public String toString(){
      return "M"+super.toString();
    }

   public boolean isDone(){
     return listOfObject.size()==0;
   }

}
