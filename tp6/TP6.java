import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
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
public class TP6{
    public static void main(String[] args) {
        List<String> list = parsertp6.readFile(args[0]);
        //System.out.println(list);
        String a[] = list.get(0).split(" ");
        int sommets = Integer.parseInt(a[0]);
        int nbjoueurs = Integer.parseInt(a[1]);
        int nbequipes = Integer.parseInt(a[2]);
        int nbarcs = Integer.parseInt(a[3]);
        System.out.println("s : " +sommets+" Joueurs : "+ nbjoueurs+" Equipes : "+ nbequipes+ " Arcs : "+ nbarcs );

    }
}