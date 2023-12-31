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

public class Main {
  public static void main(String[] args) {
    List<String> list = readFile("newprobA.in");
    // Map map = new HashMap();
    HashMap<String,Ville> villes = new HashMap<String,Ville>();
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
        String tbis [] = s.split(" ");
        villes.put(tbis[0],new Ville(s));
      }else{
        //create chemin
        Ville.setVilleVoisine(villes, s);
        // System.out.println("chemin "+s);
      }
      i++;
    }
    System.out.println(villes);
    System.out.println();
    //TODO utiliser plusieurs bus A si il y en a plusieurs.
    //ca peut marcher avec une liste d'Object busName, path
    List<Ville> l0 = getChemin(0,villes);
    List<Ville> l1 = getChemin(1,villes);
    List<Ville> l2 = getChemin(2,villes);
    System.out.println();
    System.out.println(l0);
    System.out.println();
    System.out.println(l1);
    System.out.println();
    System.out.println(l2);
    String content = "";
    content+="###\n";
    content+="A\n";
    for (Ville s : l0) {
      content+=s.toSol()+"\n";
    }
    content+="###\n";
    content+="B\n";
    for (Ville s : l1) {
      content+=s.toSol()+"\n";
    }
    content+="###\n";
    content+="C\n";
    for (Ville s : l2) {
      content+=s.toSol()+"\n";
    }
    writeFile(content, "out");
  }
  public static List<Ville> getChemin(int busId, Map<String, Ville> villes){
    List<Ville> l1 = new ArrayList<Ville>();
    Ville v1 = choix1aVille(busId, villes);
    Ville lastV = v1;
    l1.add(v1);
    int essence = v1.getDistanceBus(busId);
    while(essence>0){
      Ville vT = lastV.findBestCity();
      lastV.removeVilleVoisine();
      villes.remove(lastV.nom);
      if(vT==null){return l1;}
      essence-=Ville.movingCost;
      if(essence>-1){
        l1.add(vT);
      }
      lastV=vT;
      System.out.println(vT.nom+" -> "+essence+" voisin:"+vT.nextVille.size());
    }
    lastV.removeVilleVoisine();
    villes.remove(lastV.nom);
    return l1;
  }
  public static Ville choix1aVille(int busId, Map<String, Ville> villes){
    Ville bestVille = null;
    for (String nomVille : villes.keySet()) {
      Ville v = villes.get(nomVille);
      if(bestVille==null || bestVille.getDistanceBus(busId)<v.getDistanceBus(busId)){
        bestVille=v;
      }
    }
    return bestVille;
  }
  // public static traitement(){
  //
  // }

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
  public static boolean writeFile(String content, String nomDuFichier) {
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

      ecriteurAvecBuffer.write(content);
      ecriteurAvecBuffer.close();
    }catch (IOException e) {
      return false;
    }
    return true;
  }
  public static boolean writeFile(List<String> list, String nomDuFichier){
    String content = "";
    for (String s : list) {
      content+=s+"\n";
    }
    return writeFile(content, nomDuFichier);
  }
}
