package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

public class SelectionSort extends AbstractSort {

    private int min;
    private int i;
    private int j;

    public SelectionSort(SortsController panelSorter) {

        super(panelSorter);
    }

    @Override
    public void run() {

        for (i = 0; i < values.length - 1; i++) {
            min = i;
            for (j = i + 1; j < values.length; j++) {

                if (values[min] > values[j]) {
                    min = j;

                }

                synchronized (sortsController) {
                    try {
                        if (j % 10 == 0) sortsController.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (min != i) swap(min, i);
        }

        isFinished = true;
    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{j, min};
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{i, j};
    }

    @Override
    public String getHTMLSourceCode() {

        StringBuilder alg = new StringBuilder("<pre>\n");
        alg.append("\nfor (i = 0; i &lt; values.length - 1; i++) {");
        alg.append("\n\tmin = i;");
        alg.append("\n\tfor (j = i + 1; j &lt; values.length; j++) {");
        alg.append("\n\t\tif (values[min] &gt; values[j]) {");
        alg.append("\n\t\t\tmin = j;");
        alg.append("\n\t\t}");
        alg.append("\n\t}");
        alg.append("\n");
        alg.append("\n\tif (min != i) {");
        alg.append("\n\t\tint k = values[min];");
        alg.append("\n\t\tvalues[min] = values[i];");
        alg.append("\n\t\tvalues[i] = k;");
        alg.append("\n\t}");
        alg.append("\n}");
        alg.append("\n</pre><br/>More details on: <a href=\"http://en.wikipedia.org/wiki/Selection_sort\">http://en.wikipedia.org/wiki/Selection_sort</a><br/>&nbsp;</html>");

        return "<html><h3>Selection Sort</h3><table width=\"500\"><tr><td>works as follows:<br/>" + "<ul><li>Find the minimum value in the list</li>" + "<li>Swap it with the value in the first position</li>" + "<li>Repeat the steps above for the remainder of the list (starting at the second position and advancing each time)</li></ul>" + " Effectively, the list is divided into two parts: the sublist of items already sorted, which is built up from left to right and is found at the beginning, and the sublist of items remaining to be sorted, occupying the remainder of the array.</td></tr></table>" + alg.toString();

    }
}
