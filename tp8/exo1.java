import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class exo1{
    public static void main(String[] args) throws IOException {
        int sommeglo = 0;
        String filename = args[0];
        filename = filename.substring(0, filename.lastIndexOf('.'));
        List<String> list = parser.readFile(args[0]);
        int largeur = Integer.parseInt(list.get(0));
        int nbPieces = Integer.parseInt(list.get(1));
        int[][] pieces = new int[nbPieces+2][2];
        for(int i = 2; i < nbPieces+2; i++){
            String[] tab = list.get(i).split(" ");
            pieces[i][0] = Integer.parseInt(tab[0]);
            pieces[i][1] = Integer.parseInt(tab[1]);
            sommeglo = sommeglo + Integer.parseInt(tab[0]) * Integer.parseInt(tab[1]);
        }
        Integer a = sommeglo/largeur;
        String k = a.toString();
        outWrite(filename,k);
        System.out.println(sommeglo/largeur);
        //        write in out file the solution
        
        
    }

    public static void outWrite(String str1, String str2) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(str1+".out"));
    writer.write(str2);
    writer.close();
}
}