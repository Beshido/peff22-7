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

public class FilesOp2 {
  //tools
  public static List<String> readFile(String nomDuFichier) {
    List<String> gs = new ArrayList<String>();
    try {
      BufferedReader lecteurAvecBuffer = null;
      String ligne;
      try {
        lecteurAvecBuffer = new BufferedReader(new FileReader(nomDuFichier, StandardCharsets.UTF_8));
      } catch (FileNotFoundException e) {
        System.out.println("Le chargement du fichier " + nomDuFichier + " a échoué.");
      }
      ligne = lecteurAvecBuffer.readLine();
      gs.add(ligne);
      while ((ligne = lecteurAvecBuffer.readLine()) != null) {
        gs.add(ligne.substring(0, ligne.length() - 1));
      }
      lecteurAvecBuffer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return gs;
  }

  public static boolean writeFile(String content, int t0, String nomDuFichier) {
    // nomDuFichier = str.sToDirectoryName(nomDuFichier);
    try {
      BufferedWriter ecriteurAvecBuffer = null;
      String ligne;
      File f = new File(nomDuFichier);
      f.createNewFile(); //it will be crate only if it haven't been yet.
      try {
        ecriteurAvecBuffer = new BufferedWriter(new FileWriter(nomDuFichier, StandardCharsets.UTF_8));
      } catch (FileNotFoundException e) {
        System.out.println("Le fichier n'as pas pu être créer. Le problème peut venir d'un caractère incorecte");
        return false;
      }
      String a = content.length() + "";
      ecriteurAvecBuffer.write(a);
      for (int i = 0; i < content.length(); i++) {
        if (i % 80 == 0) {
          ecriteurAvecBuffer.write("\n");
        }
        ecriteurAvecBuffer.write(content.charAt(i));
      }
      ecriteurAvecBuffer.write("#");
      ecriteurAvecBuffer.close();
    } catch (IOException e) {
      return false;
    }
    return true;
  }
}