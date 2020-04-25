import java.util.Random;

public class main {

    public static void main (String [ ] args) {

        Labyrinth l1 = new RecursiveDivision(10,70);

        System.out.println(l1.toString());

        System.out.println(l1.getSeed());

        Labyrinth l2 = new RecursiveDivision(10, 70, l1.getSeed());

        System.out.println(l2.toString());

        System.out.println(l2.getSeed());

    }

}
