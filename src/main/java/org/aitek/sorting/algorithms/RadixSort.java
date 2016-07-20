package org.aitek.sorting.algorithms;

import org.aitek.sorting.core.SortsController;

import java.util.Arrays;

public class RadixSort extends AbstractSort {

    int p;

    public RadixSort(SortsController panelSorter) {

        super(panelSorter);
    }

    @Override
    public void run() {

        synchronized (sortsController) {
            int a[] = Arrays.copyOf(values, values.length);
            int bits = 1;
            int[] b = new int[a.length];

            int rshift = 0;
            for (int mask = ~(-1 << bits); mask != 0; mask <<= bits, rshift += bits) {
                if (rshift > 7) break;
                int[] cntarray = new int[1 << bits];

                for (p = 0; p < a.length; ++p) {
                    int key = (a[p] & mask) >> rshift;
                    ++cntarray[key];
                    printValues(a);
                }

                for (int i = 1; i < cntarray.length; ++i)
                    cntarray[i] += cntarray[i - 1];

                for (p = a.length - 1; p >= 0; --p) {
                    int key = (a[p] & mask) >> rshift;
                    --cntarray[key];
                    b[cntarray[key]] = a[p];
                }

                int[] temp = b;
                b = a;
                a = temp;
                printValues(a);
            }
        }
        isFinished = true;
    }

    private void printValues(int a[]) {

        for (int j = 0; j < values.length; j++) {
            values[j] = a[j];
        }

        try {
            sortsController.wait();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int[] getLastCoupleChanged() {

        return new int[]{p, p};
    }

    @Override
    public int[] getLastCoupleCompared() {

        return new int[]{p, p};
    }

    @Override
    public String getHTMLSourceCode() {

        String desc = "<table width=\"500\"><tr><td>sorts integers by processing individual digits, by comparing individual digits sharing the same significant position. It starts by sorting numbers according to their least significant bits, and then move onto more significant ones, until we reach the end (at which point, the array will be sorted).</td></tr></table>";
        StringBuilder alg = new StringBuilder("<html><h3>Radix Sort</h3>").append(desc).append("<br/><pre>\n");
        alg.append("\nint bits = 1;");
        alg.append("\nint[] b = new int[values.length];");
        alg.append("\n");
        alg.append("\nint rshift = 0;");
        alg.append("\nfor (int mask = ~(-1 &lt;&lt; bits); mask != 0; mask &lt;&lt;= bits, rshift += bits) {");
        alg.append("\n\tint[] cntarray = new int[1 &lt;&lt; bits];");
        alg.append("\n");
        alg.append("\n\tfor (p = 0; p &lt; values.length; ++p) {");
        alg.append("\n\t\tint key = (values[p] &amp; mask) &gt;&gt; rshift;");
        alg.append("\n\t\t++cntarray[key];");
        alg.append("\n\t}");
        alg.append("\n");
        alg.append("\n\tfor (int i = 1; i &lt; cntarray.length; ++i)");
        alg.append("\n\t\tcntarray[i] += cntarray[i - 1];");
        alg.append("\n");
        alg.append("\n\tfor (p = a.length - 1; p &gt;= 0; --p) {");
        alg.append("\n\t\tint key = (values[p] &amp; mask) &gt;&gt; rshift;");
        alg.append("\n\t\t--cntarray[key];");
        alg.append("\n\t\tb[cntarray[key]] = values[p];");
        alg.append("\n\t}");
        alg.append("\n");
        alg.append("\n\tint[] temp = b;");
        alg.append("\n\tb = values;");
        alg.append("\n\tvalues = temp;");
        alg.append("\n}");
        alg.append("\n</pre><br/>More details on: <a href=\"http://en.wikipedia.org/wiki/Radix_sort\">http://en.wikipedia.org/wiki/Radix_sort</a><br/>&nbsp;</html>");

        return alg.toString();
    }
}
