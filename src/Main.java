import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Labyrinth l1 = new RecursiveDivision(1000, 1000);      //(3,3,-462L)
        System.out.println("Seed: " + l1.getSeed());

        //System.out.println(l1.toString());
        //System.out.println(l1.toString2());

        l1.PNG();


    }

}