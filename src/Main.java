import Auxiliary.*;
import Generators.*;
import Solvers.*;

public class Main {

    public static void main(String[] args) {//23169 max PNG

        int height, width;
        height = width = 30;
        //height = 60;
        //width = 60;
        Test t1 = new Test();
        Labyrinth l1;


        t1.start();

        l1 = new GrowingTree(height,width);
        l1.exportPNG();
        Solver ra1 = new AStar(l1);
        ra1.solve();
        ra1.exportPNG();

        t1.end();
        t1.print();

    }

}