import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    List<String> list = FilesOp.readFile(args[0]);
    System.out.println(list);
  }
  public List<List<Integer>> reserch(String chaineLongue, String mots[]){
    List<List<Integer>> listOfList = new ArrayList<List<Integer>>();
    for (String mot : mots) {
      listOfList.add(makeIntList(chaineLongue, mot));
    }
    return listOfList;
  }
  public List<Integer> makeIntList(String s, String s2){return null;}
}
