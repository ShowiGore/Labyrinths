import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Main {

    public static void main(String[] args) {

        int height, width;
        height = width = 40; //23169 max PNG
        Test t1 = new Test(), t2 = new Test();
        Labyrinth l1, l2;

        t1.start();
        l1 = new GrowingTree(height,width);
        l1.exportPNG();
        RecursiveAlgorithm ra1 = new RecursiveAlgorithm(l1);
        ra1.solve();
        ra1.exportPNG();
        t1.end();
        t1.print();

    }

}