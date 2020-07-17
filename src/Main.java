import Auxiliary.Test;
import Generator.GrowingTree;
import Generator.Labyrinth;
import Generator.RecursiveDivision;
import Solver.AStarMod;

public class Main {

    public static void main(String[] args) {//23169 max PNG

        int height, width;
        height = width = 1000;
        //height = 60;
        //width = 60;
        Test t1 = new Test(), t2 = new Test();
        Labyrinth l1, l2;


        t1.start();
        l1 = new GrowingTree(height,width);
        l1.exportPNG();
        AStarMod ra1 = new AStarMod(l1);
        ra1.solve();
        ra1.exportPNG();
        t1.end();
        System.out.println(height+"x"+width+": "+l1.getSeed());
        t1.print();

    }

}