import java.util.StringJoiner;

public class Main {

    public static void main(String[] args) {


        Long startTime, endTime, elapsedTime, beforeUsedMem, afterUsedMem, actualMemUsed;

        beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTime = System.nanoTime();

        Labyrinth l1 = new RecursiveDivision(10000,10000);
        l1.exportPNG();
        //Labyrinth_IntArray l2 = new RecursiveDivision_IntArray(10000,10000);
        //l2.PNG("maze");

        endTime = System.nanoTime();
        afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        elapsedTime = endTime-startTime;
        actualMemUsed = afterUsedMem - beforeUsedMem;


        System.out.println("Time:\t\t"+showTime(elapsedTime));
        System.out.println("Memory:\t\t"+showMemory(actualMemUsed));

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

        StringJoiner sj = new StringJoiner(" | ", "[ ", " ]");

        if (days>0) {sj.add("Days: "+days);}
        if (hours>0) {sj.add("Hours: "+hours);}
        if (minutes>0) {sj.add("Minutes: "+minutes);}
        if (seconds>0) {sj.add("Sconds: "+seconds);}
        if (milliseconds>0) {sj.add("Milliseconds: "+milliseconds);}
        if (microseconds>0) {sj.add("Microseconds: "+microseconds);}
        if (nanoseconds>0) {sj.add("Nanoseconds: "+nanoseconds);}

        return(sj.toString());

    }

    public static String showMemory(long m) {

        long bytes = m%1024;
        m = m/1024;
        long kibibytes = m%1024;
        m = m/1024;
        long mebibytes = m%1024;
        m = m/1024;
        long gibibytes = m%1024;
        m = m/1024;
        long tebibytes = m%1024;
        m = m/1024;

        StringJoiner sj = new StringJoiner(" | ", "[ ", " ]");

        if (tebibytes>0) {sj.add(tebibytes+"TB");}
        if (gibibytes>0) {sj.add(gibibytes+"GB");}
        if (mebibytes>0) {sj.add(mebibytes+"MB");}
        if (kibibytes>0) {sj.add(kibibytes+"KB");}
        if (bytes>0) {sj.add(bytes+"B");}

        return(sj.toString());

    }

}