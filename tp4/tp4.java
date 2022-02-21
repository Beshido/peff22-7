import java.util.List;

class tp4{
    public static void main(String[] args) {
        List<String> list = parsertp4.readFile(args[0]);
        System.out.println(list);
    }
}