package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

public class QuickSort extends AbstractSort {

    private int index;
    private int i;
    private int newPivotIndex;

    public QuickSort(SortsController panelSorter) {

        super(panelSorter);
    }

    @Override
    public void run() {

        quicksort(0, values.length - 1);
        isFinished = true;
    }

    private int[] quicksort(int lo, int hi) {

        if (hi > lo) {
            int partitionPivotIndex = (int) (Math.random() * (hi - lo) + lo);
            newPivotIndex = partition(lo, hi, partitionPivotIndex);
            quicksort(lo, newPivotIndex - 1);
            quicksort(newPivotIndex + 1, hi);
        }
        return values;
    }

    private int partition(int lo, int hi, int pivotIndex) {

        int pivotValue = values[pivotIndex];

        swap(pivotIndex, hi); // send to the back

        index = lo;

        for (i = lo; i < hi; i++) {
            if (values[i] <= pivotValue) {
                swap(i, index);
                synchronized (sortsController) {
                    try {
                        sortsController.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                index++;
            }
        }

        swap(hi, index);

        return index;
    }

    @Override
    public int[] getLastCoupleCompared() {
        return new int[]{i, newPivotIndex};
    }

    @Override
    public int[] getLastCoupleChanged() {
        return new int[]{i, index};
    }
}
