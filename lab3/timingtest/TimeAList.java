package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
                //1000 2000 4000 8000 16000 32000 64000 128000
        AList<Integer> ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer>opCounts = new AList<>();
        int iniTn = 1000;
        for (int i = 0; i <= 7; i++) {
            Stopwatch sw = new Stopwatch();
            AList<Integer> nList = new AList<>();
            int opconut =0;
            for (; opconut < iniTn; opconut++) {
                nList.addLast(opconut);
            }
            double time = sw.elapsedTime();
            ns.addLast(iniTn);
            times.addLast(time);
            opCounts.addLast(opconut);
            iniTn *=2;
        }
        printTimingTable(ns, times, opCounts);
    }


}
