package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

public class InsertionSort extends AbstractSort {

	public InsertionSort(SortsController panelSorter) {

		super(panelSorter);
	}

	private int j;
	private int i;

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

		return new int[] { j, i };
	}

	@Override
	public int[] getLastCoupleCompared() {

		return new int[] { j, j - 1 };
	}

	@Override
	public String getHTMLSourceCode() {

		String desc = "<table width=\"500\"><tr><td>works by repeatedly taking the next item and inserting it into the final data structure in its proper order with respect to items already inserted.</td></tr></table>";

		StringBuilder alg = new StringBuilder("<html><h3>Insertion Sort</h3>").append(desc).append("<br/><pre>");
		alg.append("\nfor (i = 1; i &lt; values.length; i++) {");
		alg.append("\n\tj = i;");
		alg.append("\n\tint iValue = values[i];");
		alg.append("\n\twhile ((j &gt; 0) && (values[j - 1] &gt; iValue)) {");
		alg.append("\n\t\tvalues[j] = values[j - 1];");
		alg.append("\n\t\tj--;");
		alg.append("\n\t}");
		alg.append("\n");
		alg.append("\n\tvalues[j] = iValue;");
		alg.append("\n}");
		alg.append("\n</pre><br/>More details on: <a href=\"http://en.wikipedia.org/wiki/Insertion_sort\">http://en.wikipedia.org/wiki/Insertion_sort</a><br/>&nbsp;</html>");

		return alg.toString();

	}
}
