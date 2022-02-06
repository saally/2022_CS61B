package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static AList<Integer> Ns = new AList<>();
    private static AList<Double> times = new AList<>();
    private static AList<Integer> opCounts = new AList<>();

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
        printTimingTable(Ns, times, opCounts);
    }

    public static void timeAListConstruction() {

        int[] NList = new int[]{1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        for (int N : NList) {
            int opCount = 0;
            AList<Integer> tmpAList = new AList<>();
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < N; i += 1) {
                tmpAList.addLast(1);
                opCount += 1;
            }
            double timeInSeconds = sw.elapsedTime();

            Ns.addLast(N);
            times.addLast(timeInSeconds);
            opCounts.addLast(opCount);
        }
    }
}
