package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

public class InsertionSort extends AbstractSort {

    private int j;
    private int i;
    public InsertionSort(SortsController panelSorter) {

        super(panelSorter);
    }

    @Override
    public void run() {

        synchronized (sortsController) {

            for (i = 1; i < values.length; i++) {
                j = i;
                int iValue = values[i];
                while ((j > 0) && (values[j - 1] > iValue)) {
                    values[j] = values[j - 1];
                    j--;

                    try {
                        if (i % 5 == 0) sortsController.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                values[j] = iValue;
            }
        }
        isFinished = true;
    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{j, i};
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{j, j - 1};
    }
}
