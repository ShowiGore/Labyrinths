import Auxiliary.*;
import Generators.*;
import Solvers.*;

public class Main {

    public static void main(String[] args) {//23169 max PNG

        Image i = new Image();
        int height, width;
        //height = width = 500;
        height = 23169;
        width = 80;
        Test t1 = new Test();
        Labyrinth l1;
        Solver s1;

        t1.start();

        l1 = new EllerInfinite(0,width);
        //i.mazeToPNG(l1);

        //s1 = new AStar(l1);
        //s1.solve();
        //i.solutionToPNG(s1);

        t1.end();
        t1.print();

        //System.out.println(l1.toString());

    }

}