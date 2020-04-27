import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Labyrinth l1 = new RecursiveDivision(80, 80);      //(3,3,-462L)
        System.out.println("Seed: " + l1.getSeed());

        l1.PNG("unsolved");

        l1.minPath();

        l1.PNG("solved");


        //System.out.println(l1.toString());
        //System.out.println(l1.toString2())


    }

}