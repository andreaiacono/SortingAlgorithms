package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.Constants;
import org.aitek.sorting.core.SortsController;

public class MergeSort extends AbstractSort {

    private int posRight;
    private int posAux;
    public MergeSort(SortsController sortsController) {

        super(sortsController);
    }

    @Override
    public void run() {

        int[] temp = new int[Constants.SORT_SIZE];
        mergeSort(values, temp, 0, values.length - 1);
        isFinished = true;
    }

    private void mergeSort(int[] a, int[] vectorTemp, int left, int right) {

        synchronized (sortsController) {

            if (left < right) {
                int center = (left + right) / 2;
                mergeSort(a, vectorTemp, left, center);
                mergeSort(a, vectorTemp, center + 1, right);
                merge(a, vectorTemp, left, center + 1, right);
                try {
                    sortsController.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void merge(int[] a, int[] vectorAux, int posLeft, int posRight, int posEnd) {

        this.posRight = posRight;

        synchronized (sortsController) {

            int endLeft = posRight - 1;
            posAux = posLeft;
            int numElemen = posEnd - posLeft + 1;

            while (posLeft <= endLeft && posRight <= posEnd) {
                if ((a[posLeft]) < (a[posRight])) vectorAux[posAux++] = a[posLeft++];
                else vectorAux[posAux++] = a[posRight++];
                try {
                    sortsController.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (posLeft <= endLeft)
                vectorAux[posAux++] = a[posLeft++];

            while (posRight <= posEnd)
                vectorAux[posAux++] = a[posRight++];

            for (int i = 0; i < numElemen; i++, posEnd--)
                a[posEnd] = vectorAux[posEnd];

        }
    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{posAux, posRight};
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{0, 0};
    }
}
