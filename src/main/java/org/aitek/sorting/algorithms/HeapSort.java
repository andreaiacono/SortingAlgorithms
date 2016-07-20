package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

public class HeapSort extends AbstractSort {

    private int i;
    private int child;

    public HeapSort(SortsController sortsController) {

        super(sortsController);
    }

    @Override
    public void run() {

        for (i = values.length / 2; i >= 0; i--) {
            percDown(i, values.length);
        }

        for (i = values.length - 1; i > 0; i--) {
            swap(0, i);
            percDown(0, i);
        }
        isFinished = true;
    }

    private int leftChild(int i) {

        return 2 * i + 1;
    }

    private void percDown(int i, int n) {

        int tmp;

        synchronized (sortsController) {
            for (tmp = values[i]; leftChild(i) < n; i = child) {
                child = leftChild(i);
                if (child != n - 1 && values[child] < values[child + 1]) child++;
                if (tmp < values[child]) values[i] = values[child];
                else break;

                try {
                    sortsController.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            values[i] = tmp;
        }
    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{0, i};
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{child, i};
    }

    @Override
    public String getHTMLSourceCode() {

        String desc = "<table width=\"500\"><tr><td>begins by building a heap out of the data set, and then removing the largest item and placing it at the end of the partially sorted array. After removing the largest item, it reconstructs the heap, removes the largest remaining item, and places it in the next open position from the end of the partially sorted array. This is repeated until there are no items left in the heap and the sorted array is full.</td></tr></table>";
        StringBuilder alg = new StringBuilder("<html><h3>Heap Sort</h3>").append(desc).append("<br/><pre>");
        alg.append("\npublic int[] heapsort(int[] values) {");
        alg.append("\n");
        alg.append("\n\tfor (i = values.length / 2; i &gt;= 0; i--) {");
        alg.append("\n\t\tpercDown(i, values.length);");
        alg.append("\n\t}");
        alg.append("\n");
        alg.append("\n\tfor (i = values.length - 1; i &gt; 0; i--) {");
        alg.append("\n\t\tswap(0, i);");
        alg.append("\n\t\tpercDown(0, i);");
        alg.append("\n\t}");
        alg.append("\n");
        alg.append("\n\treturn values;");
        alg.append("\n}");
        alg.append("\n");
        alg.append("\nprivate int leftChild(int i) {");
        alg.append("\n\treturn 2 * i + 1;");
        alg.append("\n}");
        alg.append("\n");
        alg.append("\nprivate void percDown(int i, int n) {");
        alg.append("\n");
        alg.append("\n\tfor (int tmp = values[i]; leftChild(i) &lt; n; i = child) {");
        alg.append("\n\t\tchild = leftChild(i);");
        alg.append("\n\t\tif (child != n - 1 &amp;&amp; values[child] &lt; values[child + 1]) child++;");
        alg.append("\n\t\tif (tmp &lt; values[child]) values[i] = values[child];");
        alg.append("\n\t\telse break;");
        alg.append("\n\t}");
        alg.append("\n\tvalues[i] = tmp;");
        alg.append("\n}");
        alg.append("\n");
        alg.append("\nprivate void swap(int index1, int index2) {");
        alg.append("\n");
        alg.append("\n\tint tmp = values[index1];");
        alg.append("\n\tvalues[index1] = values[index2];");
        alg.append("\n\tvalues[index2] = tmp;");
        alg.append("\n}");
        alg.append("\n");
        alg.append("\n</pre><br/>More details on: <a href=\"http://en.wikipedia.org/wiki/Heap_sort\">http://en.wikipedia.org/wiki/Heap_sort</a><br/>&nbsp;</html>");

        return alg.toString();

    }
}
