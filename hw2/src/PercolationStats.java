import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double T;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        double[] ratio = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation p = new Percolation(N);
            while (!p.percolates()) {
                int randRow = StdRandom.uniform(N);
                int randCol = StdRandom.uniform(N);
                p.open(randRow, randCol);
            }
            ratio[i] = ((double) p.numberOfOpenSites()) / (N * N);
        }

        this.mean = StdStats.mean(ratio);
        this.stddev = StdStats.stddev(ratio);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLow() {
        return mean - 1.96 * stddev / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean + 1.96 * stddev / Math.sqrt(T);
    }

    public static void main(String[] args) {
        int[] count = {20, 40, 80, 160, 320, 640,1280};
        for (int i : count) {
            int trials = 100, gridSize = i;


            // 启动计时器
            Stopwatch stopwatch = Stopwatch.createStarted();

            // 运行代码
            PercolationStats ps = new PercolationStats(gridSize, trials);


            // 停止计时器
            stopwatch.stop();


//        System.out.printf("Grid Size: %d x %d | Number of Trials: %d%n", gridSize, gridSize, trials);
//        System.out.printf("平均渗流阈值为 %.2f%n", ps.mean());
//        System.out.printf("渗流阈值的标准差为 %.2f.%n", ps.stddev());
//        System.out.printf("95%% 置信区间为 [%.3f, %.3f].%n", ps.confidenceLow(), ps.confidenceHigh());

            // 获取并打印执行时间

            long duration = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("size: "+ i +" time: " + duration + "ms");
        }
    }
}
