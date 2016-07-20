package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;
import org.aitek.sorting.utils.IoUtils;

import java.io.File;

public abstract class AbstractSort implements SortStrategy {

    protected int[] values;
    boolean isFinished;
    SortsController sortsController;

    public AbstractSort(SortsController sortsController) {
        this.sortsController = sortsController;
        values = new int[1];
    }

    protected void swap(int i, int j) {
        int tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public int[] getData() {
        return values;
    }

    @Override
    public void setData(int[] values) {
        this.values = values;
        isFinished = false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getHtmlSourceCode() {
        String filename = "/descriptions" + File.separator + this.getClass().getSimpleName() + ".html";
        return IoUtils.readResourceAsString(filename);
    }

}
