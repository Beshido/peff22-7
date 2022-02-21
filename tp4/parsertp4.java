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


public class parsertp4 {
  //tools
  public static List<String> readFile(String nomDuFichier){
    List<String> gs= new ArrayList<String>();
    try {
      BufferedReader lecteurAvecBuffer = null;
      String ligne;
      String tmp = "";
      try {
        lecteurAvecBuffer = new BufferedReader(new FileReader(nomDuFichier, StandardCharsets.UTF_8));
      } catch(FileNotFoundException e) {
        System.out.println("Le chargement du fichier "+ nomDuFichier+" a échoué.");
      }
      ligne = lecteurAvecBuffer.readLine();
      gs.add(ligne);
      while ((ligne = lecteurAvecBuffer.readLine()) != null){
        tmp = tmp + ligne;
        if(ligne.charAt(ligne.length()-1) == '#'){
          gs.add(tmp.substring(0,tmp.length()-1));
          tmp = "";
        }
        else{
        }
      }
      lecteurAvecBuffer.close();
    }catch (IOException e) {
      e.printStackTrace();
    }
    return gs;
  }
  public static boolean writeFile(String content,int t0, String nomDuFichier) {
    // nomDuFichier = str.sToDirectoryName(nomDuFichier);
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
      String a = content.length()+"\n";
      ecriteurAvecBuffer.write(a);
      String s = t0+"";
      ecriteurAvecBuffer.write(s);
      for(int i = 0 ; i<content.length();i++){
        if(i%80 == 0){
          ecriteurAvecBuffer.write("\n");
        }
        ecriteurAvecBuffer.write(content.charAt(i));
      }
      ecriteurAvecBuffer.write("#");
      ecriteurAvecBuffer.close();
    }catch (IOException e) {
      return false;
    }
    return true;
  }
  /*public static boolean writeFile(List<String> list, String nomDuFichier){
    String content = "";
    for (String s : list) {
      content+=s+"\n";
    }
    return writeFile(content, nomDuFichier);
  }*/
}
