import Auxiliary.Image;
import Auxiliary.Test;
import Generators.Eller;
import Generators.Labyrinth;
import Solvers.Backtracking;
import Solvers.Solver;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {//23169 max PNG



        Image i = new Image();
        int height, width;
        //height = width = 1000;//90620
        height = 20;
        width = 40;
        Test t1 = new Test();
        Labyrinth l1;
        Solver s1;



        t1.start();//

        l1 = new Eller(height, width, 0L);

        t1.end();//
        t1.print();

        s1 = new Backtracking(l1);

        t1.start();//

        s1.solve();

        t1.end();//
        t1.print();


        i.mazeToPNG(l1);
        i.solutionToPNG(s1);
    }

}