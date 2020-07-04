import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Main {

    public static void main(String[] args) {

        int height, width;

        height = width = 2; //23169 max PNG
        //BufferedImage image = new BufferedImage(width,height,TYPE_INT_RGB);

        /*Test t2 = new Test();
        t2.start();
        Labyrinth_IntArray l2 = new RecursiveDivision_IntArray(height,width);
        t2.end();
        System.out.println("RecursiveDivision(int):");
        t2.print();*/


        Test t1 = new Test();
        t1.start();

        Labyrinth l1 = new RecursiveDivision(height,width);
        l1.exportPNG();

        RecursiveAlgorithm ra1 = new RecursiveAlgorithm(l1);
        ra1.solve();
        ra1.exportPNG();

        t1.end();
        System.out.println("RecursiveDivision:");
        t1.print();


        //l1.exportPNG();
        //Labyrinth_IntArray l2 = new RecursiveDivision_IntArray(10000,10000);
        //l2.PNG("maze");



        //System.out.println("Seed: " + l1.getSeed());

        //l1.PNG("unsolved");

        //l1.solve();

        //l1.PNG("solved");


        //System.out.println(l1.toString());
        //System.out.println(l1.toString2());

    }

}