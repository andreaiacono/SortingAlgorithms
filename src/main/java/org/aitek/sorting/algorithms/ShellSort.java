package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

public class ShellSort extends AbstractSort {

    private int j;
    private int temp;
    public ShellSort(SortsController panelSorter) {

        super(panelSorter);
    }

    @Override
    public void run() {

        int n = values.length;

        synchronized (sortsController) {

            int inc = Math.round(n / 2);
            while (inc > 0) {
                for (int i = inc; i <= n - 1; i++) {
                    temp = values[i];
                    j = i;
                    while (j >= inc && values[j - inc] > temp) {
                        values[j] = values[j - inc];
                        j = j - inc;

                    }
                    values[j] = temp;

                    try {
                        sortsController.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                inc = Math.round((float) (inc / 2.2));
            }
        }
        isFinished = true;
    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{j, temp};
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{j, temp};
    }

    @Override
    public String getHTMLSourceCode() {

        String desc = "<table width=\"500\"><tr><td>improves <i>Insertion Sort</i> by allowing the comparison and exchange of elements that are far apart. The last step of Shell sort is a plain insertion sort, but by then, the array of data is guaranteed to be almost sorted.</td></tr></table>";
        StringBuilder alg = new StringBuilder("<html><h3>Shell Sort</h3>").append(desc).append("<br/><pre>");
        alg.append("\nint n = values.length;");
        alg.append("\nint inc = Math.round(n / 2);");
        alg.append("\nwhile (inc &gt; 0) {");
        alg.append("\n\tfor (int i = inc; i &lt;= n - 1; i++) {");
        alg.append("\n\t\ttemp = values[i];");
        alg.append("\n\t\tj = i;");
        alg.append("\n\t\twhile (j &gt;= inc &amp;&amp; values[j - inc] &gt; temp) {");
        alg.append("\n\t\t\tvalues[j] = values[j - inc];");
        alg.append("\n\t\t\tj = j - inc;");
        alg.append("\n");
        alg.append("\n\t\t}");
        alg.append("\n\t\tvalues[j] = temp;");
        alg.append("\n\t}");
        alg.append("\n\tinc = Math.round((float) (inc / 2.2));");
        alg.append("\n}");
        alg.append("\n</pre><br/>More details on: <a href=\"http://en.wikipedia.org/wiki/Shell_sort\">http://en.wikipedia.org/wiki/Shell_sort</a><br/>&nbsp;</html>");

        return alg.toString();
    }
}
