import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
  public static void main(String[] args) {
    List<String> list = readFile("newprobA.in");
    // Map map = new HashMap();
    Set<Ville> villes = new HashSet<Ville>();
    int i=0;
    int t[]=new int[4];
    // t[0] = nombre de ville
    // t[1] = nombre de bus A
    // t[2] = nombre de bus B
    // t[3] = nombre de bus C
    for (String s : list) {
      if(i<4){
        t[i]=Integer.valueOf(s);
      }else if(i<4+t[0]){
        villes.add(new Ville(s));
      }else{
        //create chemin

        System.out.println("chemin "+s);
      }
      i++;
    }
    System.out.println(villes);
    traitement();
  }
  public static void traitement(){
    // Ville bestVille = null;
    // for (Ville v : villes) {
    //   if(villes.getDistanceBus(0)){
    //
    //   }
    // }
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
