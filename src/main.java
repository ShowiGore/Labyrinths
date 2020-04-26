import java.util.Arrays;

public class main {

    public static void main(String[] args) {

        Labyrinth l1 = new RecursiveDivision(3, 3, -462L);
        System.out.println("Seed: " + l1.getSeed());

        System.out.println(l1.toString());
        System.out.println(l1.toString2());
    }

}