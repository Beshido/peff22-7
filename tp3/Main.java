import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<String> list = FilesOp.readFile(args[0]);
    System.out.println(list);
  }
}
