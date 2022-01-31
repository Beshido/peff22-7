import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class Main {
  public static void main(String[] args) {
    List<String> list = readFile("newprobA.in");
    // System.out.println(list);
    // int nbrVille = Integer.valueOf();
    int i=0;
    int t[]=new int[4];
    for (String s : list) {
      if(i<4){
        t[i]=Integer.valueOf(s);
      }else if(i<4+t[0]){
        System.out.println("ville "+s);
      }else{
        System.out.println("chemin "+s);
      }
      i++;
    }
  }

  //tools
  public static List<String> readFile(String nomDuFichier){
    List<String> gs= new ArrayList<String>();
    try {
      BufferedReader lecteurAvecBuffer = null;
      String ligne;
      try {
        lecteurAvecBuffer = new BufferedReader(new FileReader(nomDuFichier, StandardCharsets.UTF_8));
      } catch(FileNotFoundException e) {
        System.out.println("Le chargement du fichier "+ nomDuFichier+" a échoué.");
      }
      while ((ligne = lecteurAvecBuffer.readLine()) != null){
        gs.add(ligne);
      }
      lecteurAvecBuffer.close();
    }catch (IOException e) {
      e.printStackTrace();
    }
    return gs;
  }
}
