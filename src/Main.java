public class Main {

    public static void main(String[] args) {

        Labyrinth l1 = new Labyrinth(4, 20);
        System.out.println(l1);
        l1.randomize();
        System.out.println(l1.toString());
        System.out.println(l1.toStringAccurate());

        /*
        Labyrinth_IntArray l1 = new RecursiveDivision_IntArray(80, 80);      //(3,3,-462L)
        System.out.println("Seed: " + l1.getSeed());

        l1.PNG("unsolved");

        l1.minPath();

        l1.PNG("solved");
*/

        //System.out.println(l1.toString());
        //System.out.println(l1.toString2())


    }

}