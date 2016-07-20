package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

public class HeapSort extends AbstractSort {

    private int i;
    private int child;

    public HeapSort(SortsController sortsController) {

        super(sortsController);
    }

    @Override
    public void run() {

        for (i = values.length / 2; i >= 0; i--) {
            percDown(i, values.length);
        }

        for (i = values.length - 1; i > 0; i--) {
            swap(0, i);
            percDown(0, i);
        }
        isFinished = true;
    }

    private int leftChild(int i) {

        return 2 * i + 1;
    }

    private void percDown(int i, int n) {

        int tmp;

        synchronized (sortsController) {
            for (tmp = values[i]; leftChild(i) < n; i = child) {
                child = leftChild(i);
                if (child != n - 1 && values[child] < values[child + 1]) child++;
                if (tmp < values[child]) values[i] = values[child];
                else break;

                try {
                    sortsController.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            values[i] = tmp;
        }
    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{0, i};
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{child, i};
    }
}
