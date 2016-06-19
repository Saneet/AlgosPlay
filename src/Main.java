import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = HackerRankKaprekarNumbers.fetchModKaprekarList(77778, 77778);
        for (int i = 0; i<list.size(); i++){
            System.out.println(list.get(i));
        }


    }
}


