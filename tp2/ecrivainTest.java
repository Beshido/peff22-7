import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ecrivainTest {


    public static void main(String[] args) {
     try {
 
      File file = new File("sol.out");
   
      // creation du fichier s'il n'existe pas
      if (!file.exists()) {
       file.createNewFile();
      }
    String []tabA = {"Je suis une ligne 1",
                    "Je suis une ligne 2",
                    "Je suis une ligne 3"
      };
    String []tabB = {"Je suis une ligne 1",
                    "Je suis une ligne 2",
                    "Je suis une ligne 3"
};
    String []tabC = {"Je suis une ligne 1",
                    "Je suis une ligne 2",
                    "Je suis une ligne 3"
};
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("###");
      bw.newLine();
      bw.write("A");
      bw.newLine();
      
      
      for (int i = 0; i < tabA.length; i++) {
        bw.write(tabA[i]);
        bw.newLine();
    }
    bw.write("###");
      bw.newLine();
      bw.write("B");
      bw.newLine();
      
      for (int i = 0; i < tabB.length; i++) {
        bw.write(tabB[i]);
        bw.newLine();
      }

      if(tabC.length != 0){
      bw.write("###");
      bw.newLine();
      bw.write("C");
      bw.newLine();
      
      
      for (int i = 0; i < tabC.length; i++) {
        bw.write(tabC[i]);
        bw.newLine();
      }
    }
      bw.close();
   
     } catch (IOException e) {
      e.printStackTrace();
     }
    }
   }
