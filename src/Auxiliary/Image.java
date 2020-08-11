package Auxiliary;

import Generators.Labyrinth;
import Solvers.Solver;
import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.ImageLineInt;
import ar.com.hjg.pngj.PngWriter;
import ar.com.hjg.pngj.chunks.PngChunkPLTE;

import java.io.File;
import java.util.BitSet;

import static Generators.Labyrinth.PATH;
import static Generators.Labyrinth.WALL;

public class Image {

    public void mazeToPNG(Labyrinth labyrinth) {
        mazeToPNG(labyrinth, "Maze");
    }

    public void mazeToPNG(Labyrinth labyrinth, String name) {

        int h=labyrinth.getHeight(), w=labyrinth.getWidth();
        BitSet[] maze = labyrinth.getMaze();

        File f = new File(name+".png");
        ImageInfo ii = new ImageInfo(labyrinth.getWidth(), labyrinth.getHeight(), 1, false, false, true);

        PngWriter pngw = new PngWriter(f,ii,true);

        pngw.setCompLevel(9);

        //creamos la paleta de colores
        PngChunkPLTE palette = pngw.getMetadata().createPLTEChunk();
        palette.setNentries(4);
        palette.setEntry(0,0,0,0);//black
        palette.setEntry(1,255,255,255);//white

        ImageLineInt ili = new ImageLineInt(ii);

        for (int y = 0; y < ii.rows; y++) {
            int [] sl = ili.getScanline();
            for (int x = 0; x < ii.cols; x++) {

                Boolean p = maze[y].get(x);

                if (p == WALL) {
                    sl[x] = 0;
                } else if (p == PATH) {
                    sl[x] = 1;
                }

            }
            pngw.writeRow(ili);
        }

        pngw.end();

    }

    public void solutionToPNG(Solver solver) {
        solutionToPNG(solver,"Solution");
    }

    public void solutionToPNG(Solver solver, String name) {

        int h=solver.getHeight(), w=solver.getWidth();
        BitSet[] maze = solver.getMaze(), solution=solver.getSolution(), visited=solver.getVisited();

        File f = new File(name+".png");
        ImageInfo ii = new ImageInfo(solver.getWidth(), solver.getHeight(), 2, false, false, true);

        PngWriter pngw = new PngWriter(f,ii,true);

        pngw.setCompLevel(9);

        //creamos la paleta de colores
        PngChunkPLTE palette = pngw.getMetadata().createPLTEChunk();
        palette.setNentries(4);
        palette.setEntry(0,0,0,0);//black
        palette.setEntry(1,255,255,255);//white
        palette.setEntry(2,255,0,0);//red
        palette.setEntry(3,0,255,0);//green

        ImageLineInt ili = new ImageLineInt(ii);

        for (int y = 0; y < ii.rows; y++) {
            int [] sl = ili.getScanline();
            for (int x = 0; x < ii.cols; x++) {

                Boolean m = maze[y].get(x), s = solution[y].get(x), v = visited[y].get(x);

                if (m == Labyrinth.WALL) {
                    sl[x] = 0;//black
                } else if (s) {
                    sl[x] = 3;//green
                } else if (v) {
                    sl[x] = 2;//red
                } else if (m == PATH) {
                    sl[x] = 1;//white
                }

            }
            pngw.writeRow(ili);
        }

        pngw.end();

    }

}

/*
    public void solutionToPNG(Solver solver, String name) {
        int h=solver.getHeight(), w=solver.getWidth();
        BitSet[] maze = solver.getMaze(), solution=solver.getSolution(), visited=solver.getVisited();

        BufferedImage image = new BufferedImage(w,h,TYPE_INT_RGB);

        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        Color red = new Color(128,0,0);
        Color green = new Color(0,255,0);
        Color unknown = new Color(128,128,128);

        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {

                Boolean m = maze[i].get(j), s = solution[i].get(j), v = visited[i].get(j);

                if (m == Labyrinth.WALL) {
                    image.setRGB(j,i,black.getRGB());
                } else if (s) {
                    image.setRGB(j,i,green.getRGB());
                } else if (v) {
                    image.setRGB(j,i,red.getRGB());
                } else if (m == PATH) {
                    image.setRGB(j, i, white.getRGB());
                }  else {
                    image.setRGB(j,i,unknown.getRGB());
                }

            }
        }

        File output = new File(name+".png");

        try {
            ImageIO.write(image, "png", output);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
*/

/*
    public void mazeToPNG(Labyrinth labyrinth, String name) {

        int h=labyrinth.getHeight(), w=labyrinth.getWidth();
        BitSet[] maze = labyrinth.getMaze();

        BufferedImage image = new BufferedImage(w, h,TYPE_INT_RGB);

        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        Color unknown = new Color(128,128,128);

        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {

                Boolean p = maze[i].get(j);

                if (p == WALL) {
                    image.setRGB(j,i,black.getRGB());
                } else if (p == PATH) {
                    image.setRGB(j,i,white.getRGB());
                }  else {
                    image.setRGB(j,i,unknown.getRGB());
                }

            }
        }

        File output = new File(name+".png");

        try {
            ImageIO.write(image, "png", output);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
*/