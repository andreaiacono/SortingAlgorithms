package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

public class BubbleSort extends AbstractSort {

    private int j;
    private int i;
    public BubbleSort(SortsController sortsController) {

        super(sortsController);
    }

    @Override
    public void run() {

        synchronized (sortsController) {

            for (j = 0; j < values.length; j++) {
                for (i = 0; i < values.length; i++) {
                    if (values[j] < values[i]) {
                        swap(i, j);

                        try {
                            if (i % 3 == 0) sortsController.wait();
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        isFinished = true;
    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{i, j};
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{i, j};
    }
}
