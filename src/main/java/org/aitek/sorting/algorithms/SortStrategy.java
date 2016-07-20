package org.aitek.sorting.algorithms;

public interface SortStrategy extends Runnable {

    int[] getData();

    void setData(int[] values);

    boolean isFinished();

    int[] getLastCoupleChanged();

    int[] getLastCoupleCompared();

    String getHtmlSourceCode();
}
