public class main {

    public static void main (String [ ] args) {

        Labyrinth l = new Labyrinth(10,40);

        l.randomizeInside();

        System.out.println(l.toString());

        l.printMatrix();
    }

}
