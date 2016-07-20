package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

public class BubbleSort extends AbstractSort {

    private int j;
    private int i;
    public BubbleSort(SortsController sortsController) {

        super(sortsController);
    }

    @Override
    public void run() {

        synchronized (sortsController) {

            for (j = 0; j < values.length; j++) {
                for (i = 0; i < values.length; i++) {
                    if (values[j] < values[i]) {
                        swap(i, j);

                        try {
                            if (i % 3 == 0) sortsController.wait();
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        isFinished = true;
    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{i, j};
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{i, j};
    }

    @Override
    public String getHTMLSourceCode() {

        return "<html><h3>Bubble Sort</h3><table width=\"500\"><tr><td>is a simple sorting algorithm that works by repeatedly stepping through the list to be sorted, comparing each pair of adjacent items and swapping them if they are in the wrong order.</td></tr></table><pre>" + "for (j = 0; j &lt; values.length; j++) {" + "\n    for (i = 0; i &lt; values.length; i++) {" + "\n      if (values[j] &lt; values[i]) {" + "\n         temp = values[j];" + "\n         values[j] = values[i];" + "\n         values[i] = temp;" + "\n      }" + "\n   }" + "\n}</pre><br/>More details on: <a href=\"http://en.wikipedia.org/wiki/Bubble_sort\">http://en.wikipedia.org/wiki/Bubble_sort</a><br/>&nbsp;</html>";
    }
}
