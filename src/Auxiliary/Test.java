package Auxiliary;

import java.util.StringJoiner;

public class Test {

    Long startTime, endTime, elapsedTime, startMemory, endMemory, usedMemory;

    public Test() {}

    public void start() {
        startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTime = System.nanoTime();
    }

    public void end() {
        endTime = System.nanoTime();
        endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        elapsedTime = endTime-startTime;
        usedMemory = endMemory - startMemory;
    }

    public String getTime() {
        return showTime(elapsedTime);
    }

    public String getMemory() {
        return showMemory(usedMemory);
    }

    public void print() {
        System.out.println("\nTime:\n"+showTime(elapsedTime));
        System.out.println("\nMemory:\n"+showMemory(usedMemory));
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

        if (days>0) {sj.add(days+" d");}
        if (hours>0) {sj.add(hours+" h");}
        if (minutes>0) {sj.add(minutes+" min");}
        if (seconds>0) {sj.add(seconds+" s");}
        if (milliseconds>0) {sj.add(milliseconds+" ms");}
        if (microseconds>0) {sj.add(microseconds+" µs");}
        if (nanoseconds>0) {sj.add(nanoseconds+" ns");}

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

        if (tebibytes>0) {sj.add(tebibytes+" TB");}
        if (gibibytes>0) {sj.add(gibibytes+" GB");}
        if (mebibytes>0) {sj.add(mebibytes+" MB");}
        if (kibibytes>0) {sj.add(kibibytes+" KB");}
        if (bytes>0) {sj.add(bytes+" B");}

        return(sj.toString());

    }

}
