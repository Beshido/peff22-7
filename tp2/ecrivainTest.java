import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ecrivainTest {
    public static void main(String[] args) {
     try {
   
      String content = "CECI EST UN TEST";
   
      File file = new File("test.txt");
   
      // creation du fichier s'il n'existe pas
      if (!file.exists()) {
       file.createNewFile();
      }
   
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(content);
      bw.close();
   
     } catch (IOException e) {
      e.printStackTrace();
     }
    }
   }
