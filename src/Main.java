import Auxiliary.Image;
import Auxiliary.Test;
import Generators.Eller;
import Generators.Labyrinth;
import Solvers.Recursive;
import Solvers.Solver;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {//23169 max PNG



        Image i = new Image();
        int height, width;
        height = width = 750;//90620
        //height = 10000;
        //width = 200;
        Test t1 = new Test();
        Labyrinth l1;
        Solver s1;



        t1.start();//

        l1 = new Eller(height, width, 0L);

        t1.end();//
        t1.print();

        s1 = new Recursive(l1);

        t1.start();//

        s1.solve();

        t1.end();//
        t1.print();


        //i.mazeToPNG(l1);
        //i.solutionToPNG(s1);
    }

}