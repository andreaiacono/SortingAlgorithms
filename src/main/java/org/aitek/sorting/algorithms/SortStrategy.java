package org.aitek.sorting.algorithms;

public interface SortStrategy extends Runnable {

    public int[] getData();

    public void setData(int[] values);

    public boolean isFinished();

    public int[] getLastCoupleChanged();

    public int[] getLastCoupleCompared();

    public String getHTMLSourceCode();
}
