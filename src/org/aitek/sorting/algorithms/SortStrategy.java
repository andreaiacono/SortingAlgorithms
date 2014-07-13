package org.aitek.sorting.algorithms;

public interface SortStrategy extends Runnable {

	public void setData(int[] values);

	public int[] getData();

	public boolean isFinished();

	public int[] getLastCoupleChanged();

	public int[] getLastCoupleCompared();

	public String getHTMLSourceCode();
}
