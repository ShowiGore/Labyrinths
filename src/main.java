public class main {

    public static void main (String [ ] args) {

        Labyrinth l = new Labyrinth(4,4);
        
        l.randomizeInside();

        System.out.println(l.toString());

        l.printMatrix();
    }

}
