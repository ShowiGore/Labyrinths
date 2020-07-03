public class Main {

    public static void main(String[] args) {

        Long startTime, endTime, elapsedTime;

        startTime = System.nanoTime();

        Labyrinth_IntArray l1 = new RecursiveDivision_IntArray(10000, 10000);      //(3,3,-462L)

        endTime = System.nanoTime();
        elapsedTime = endTime-startTime;
        System.out.println("Recursive Division:\t\t"+showTime(elapsedTime));


        //System.out.println("Seed: " + l1.getSeed());

        //l1.PNG("unsolved");

        //l1.solve();

        //l1.PNG("solved");


        //System.out.println(l1.toString());
        //System.out.println(l1.toString2())


    }

    public static String showTime(long t) {
        long nanoseconds = t%1000;
        t = t/1000;
        long microseconds = t%1000;
        t = t/1000;
        long milliseconds = t%1000;
        t = t/1000;
        long seconds = t%60;
        t = t/60;
        long minutes = t%60;
        t = t/60;
        long hours = t%24;
        t = t/24;
        long days = t;

        String s = "";

        if (days>0) {s += "Days: "+days+" | ";}
        if (hours>0) {s += "Hours: "+hours+" | ";}
        if (minutes>0) {s += "Minutes: "+minutes+" | ";}
        if (seconds>0) {s += "Sconds: "+seconds+" | ";}
        if (milliseconds>0) {s += "Milliseconds: "+milliseconds+" | ";}
        if (microseconds>0) {s += "Microseconds: "+microseconds+" | ";}
        if (nanoseconds>0) {s += "Nanoseconds: "+nanoseconds+" | ";}

        return(s);
    }

}