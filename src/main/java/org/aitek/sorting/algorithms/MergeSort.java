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

    @Override
    public String getHTMLSourceCode() {

        String desc = "<table width=\"500\"><tr><td>works as follows: if the list is of length 0 or 1, then it is already sorted. Otherwise:" + "<ul><li>divide the unsorted list into two sublists of about half the size</li>" + "<li>sort each sublist recursively by re-applying merge sort</li>" + "<li>merge the two sublists back into one sorted list</li></ul></td></tr></table>";
        StringBuilder alg = new StringBuilder("<html><h3>Merge Sort</h3>").append(desc).append("<br/><pre>");
        alg.append("private void mergeSort(int[] a, int[] vectorTemp, int left, int right) {");
        alg.append("\n");
        alg.append("\n\tif (left &lt; right) {");
        alg.append("\n\t\tint center = (left + right) / 2;");
        alg.append("\n\t\tmergeSort(a, vectorTemp, left, center);");
        alg.append("\n\t\tmergeSort(a, vectorTemp, center + 1, right);");
        alg.append("\n\t\tmerge(a, vectorTemp, left, center + 1, right);");
        alg.append("\n\t}");
        alg.append("\n}");
        alg.append("\n");
        alg.append("\nprivate void merge(int[] a, int[] vectorAux, int posLeft, int posRight, int posEnd) {");
        alg.append("\n");
        alg.append("\n\tthis.posLeft = posLeft;");
        alg.append("\n\tthis.posRight = posRight;");
        alg.append("\n");
        alg.append("\n\tint endLeft = posRight - 1;");
        alg.append("\n\tint posAux = posLeft;");
        alg.append("\n\tint numElemen = posEnd - posLeft + 1;");
        alg.append("\n");
        alg.append("\n\twhile (posLeft &lt;= endLeft &amp;&amp; posRight &lt;= posEnd) {");
        alg.append("\n\t\tif ((a[posLeft]) &lt; (a[posRight])) vectorAux[posAux++] = a[posLeft++];");
        alg.append("\n\t\telse vectorAux[posAux++] = a[posRight++];");
        alg.append("\n\t}");
        alg.append("\n");
        alg.append("\n\twhile (posLeft &lt;= endLeft)");
        alg.append("\n\t\tvectorAux[posAux++] = a[posLeft++];");
        alg.append("\n");
        alg.append("\n\twhile (posRight &lt;= posEnd)");
        alg.append("\n\t\tvectorAux[posAux++] = a[posRight++];");
        alg.append("\n");
        alg.append("\n\tfor (int i = 0; i &lt; numElemen; i++, posEnd--)");
        alg.append("\n\t\ta[posEnd] = vectorAux[posEnd];");
        alg.append("\n}");
        alg.append("\n</pre><br/>More details on: <a href=\"http://en.wikipedia.org/wiki/Merge_sort\">http://en.wikipedia.org/wiki/Merge_sort</a><br/>&nbsp;</html>");

        return alg.toString();

    }
}
