package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

import java.util.Arrays;

public class RadixSort extends AbstractSort {

    int p;

    public RadixSort(SortsController panelSorter) {

        super(panelSorter);
    }

    @Override
    public void run() {

        synchronized (sortsController) {
            int a[] = Arrays.copyOf(values, values.length);
            int bits = 1;
            int[] b = new int[a.length];

            int rshift = 0;
            for (int mask = ~(-1 << bits); mask != 0; mask <<= bits, rshift += bits) {
                if (rshift > 7) break;
                int[] cntarray = new int[1 << bits];

                for (p = 0; p < a.length; ++p) {
                    int key = (a[p] & mask) >> rshift;
                    ++cntarray[key];
                    printValues(a);
                }

                for (int i = 1; i < cntarray.length; ++i)
                    cntarray[i] += cntarray[i - 1];

                for (p = a.length - 1; p >= 0; --p) {
                    int key = (a[p] & mask) >> rshift;
                    --cntarray[key];
                    b[cntarray[key]] = a[p];
                }

                int[] temp = b;
                b = a;
                a = temp;
                printValues(a);
            }
        }
        isFinished = true;
    }

    private void printValues(int a[]) {

        for (int j = 0; j < values.length; j++) {
            values[j] = a[j];
        }

        try {
            sortsController.wait();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{p, p};
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{p, p};
    }
}
