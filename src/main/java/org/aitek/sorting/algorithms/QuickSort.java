package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

public class QuickSort extends AbstractSort {

    private int index;
    private int i;
    private int newPivotIndex;

    public QuickSort(SortsController panelSorter) {

        super(panelSorter);
    }

    @Override
    public void run() {

        quicksort(0, values.length - 1);
        isFinished = true;
    }

    private int[] quicksort(int lo, int hi) {

        if (hi > lo) {
            int partitionPivotIndex = (int) (Math.random() * (hi - lo) + lo);
            newPivotIndex = partition(lo, hi, partitionPivotIndex);
            quicksort(lo, newPivotIndex - 1);
            quicksort(newPivotIndex + 1, hi);
        }
        return values;
    }

    private int partition(int lo, int hi, int pivotIndex) {

        int pivotValue = values[pivotIndex];

        swap(pivotIndex, hi); // send to the back

        index = lo;

        for (i = lo; i < hi; i++) {
            if (values[i] <= pivotValue) {
                swap(i, index);
                synchronized (sortsController) {
                    try {
                        sortsController.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                index++;
            }
        }

        swap(hi, index);

        return index;
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{i, newPivotIndex};
    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{i, index};

    }

    @Override
    public String getHTMLSourceCode() {

        String desc = "<table width=\"500\"><tr><td>" + "sorts by employing a divide and conquer strategy to divide a list into two sub-lists." + "The steps are:" + "<ul><li>Pick an element, called a pivot, from the list </li>" + "<li>Reorder the list so that all elements with values less than the pivot come before the pivot, while all elements with values greater than the pivot come after it (equal values can go either way). After this partitioning, the pivot is in its final position (this is the <i>partition operation</i>)" + "<li>Recursively sort the sub-list of lesser elements and the sub-list of greater elements</li>" + "</td></tr></table>";
        StringBuilder alg = new StringBuilder("<html><h3>Quick Sort</h3>").append(desc).append("<pre>\n");
        alg.append("\nprivate int[] quicksort(int[] array, int lo, int hi) {");
        alg.append("\n");
        alg.append("\n\tif (hi &gt; lo) {");
        alg.append("\n\t\tint pivotIndex = (int) (Math.random() * (hi - lo) + lo);");
        alg.append("\n\t\tnewPivotIndex = partition(array, lo, hi, pivotIndex);");
        alg.append("\n\t\tquicksort(array, lo, newPivotIndex - 1);");
        alg.append("\n\t\tquicksort(array, newPivotIndex + 1, hi);");
        alg.append("\n\t}");
        alg.append("\n\treturn (int[]) array;");
        alg.append("\n}");
        alg.append("\n");
        alg.append("\nprivate int partition(int[] array, int lo, int hi, int pivotIndex) {");
        alg.append("\n");
        alg.append("\n\tint pivotValue = array[pivotIndex];");
        alg.append("\n\tswap(array, pivotIndex, hi);");
        alg.append("\n\tindex = lo;");
        alg.append("\n\tfor (i = lo; i &lt; hi; i++) {");
        alg.append("\n\t\tif (array[i] <= pivotValue) {");
        alg.append("\n\t\t\tswap(array, i, index);");
        alg.append("\n\t\t\tindex++;");
        alg.append("\n\t\t}");
        alg.append("\n\t}");
        alg.append("\n\tswap(array, hi, index);");
        alg.append("\n\treturn index;");
        alg.append("\n}");
        alg.append("\n");
        alg.append("\nprivate void swap(int[] array, int i, int j) {");
        alg.append("\n\tint temp = array[i];");
        alg.append("\n\tarray[i] = array[j];");
        alg.append("\n\tarray[j] = temp;");
        alg.append("\n}");
        alg.append("\n</pre>More details on: <a href=\"http://en.wikipedia.org/wiki/Quicksort\">http://en.wikipedia.org/wiki/Quicksort</a></html>");

        return alg.toString();

    }
}
