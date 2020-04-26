import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Labyrinth l1 = new RecursiveDivision(10, 60);      //(3,3,-462L)
        System.out.println("Seed: " + l1.getSeed());

        System.out.println(l1.toString());
        //System.out.println(l1.toString2());

        //l1.PNG();


    }

}