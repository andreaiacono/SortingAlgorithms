package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

public class ShellSort extends AbstractSort {

    private int j;
    private int temp;
    public ShellSort(SortsController panelSorter) {

        super(panelSorter);
    }

    @Override
    public void run() {

        int n = values.length;

        synchronized (sortsController) {

            int inc = Math.round(n / 2);
            while (inc > 0) {
                for (int i = inc; i <= n - 1; i++) {
                    temp = values[i];
                    j = i;
                    while (j >= inc && values[j - inc] > temp) {
                        values[j] = values[j - inc];
                        j = j - inc;

                    }
                    values[j] = temp;

                    try {
                        sortsController.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                inc = Math.round((float) (inc / 2.2));
            }
        }
        isFinished = true;
    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{j, temp};
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{j, temp};
    }
}
