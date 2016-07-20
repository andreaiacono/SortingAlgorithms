package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

public class SelectionSort extends AbstractSort {

    private int min;
    private int i;
    private int j;

    public SelectionSort(SortsController panelSorter) {

        super(panelSorter);
    }

    @Override
    public void run() {

        for (i = 0; i < values.length - 1; i++) {
            min = i;
            for (j = i + 1; j < values.length; j++) {

                if (values[min] > values[j]) {
                    min = j;

                }

                synchronized (sortsController) {
                    try {
                        if (j % 10 == 0) sortsController.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (min != i) swap(min, i);
        }

        isFinished = true;
    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{j, min};
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{i, j};
    }
}
