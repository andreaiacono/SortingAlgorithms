package org.aitek.sorting.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextConverter {

    public static void main(String[] args) {

        String alg = "Radix";
        File f = new File("/home/andrea/test.java");
        StringBuilder sb = new StringBuilder("String desc = \"<table width=\\\"500\\\"><tr><td></td></tr></table>\";\nStringBuilder alg = new StringBuilder(\"<html><h3>" + alg + " Sort</h3>\").append(desc).append(\"<br/><pre>\\n\");\n");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = null;
            while ((line = br.readLine()) != null) {

                sb.append("alg.append(\"\\n" + line.replaceAll("&", "&amp;").replaceAll("\t", "\\\\t").replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "\");\n");
            }

            sb.append("alg.append(\"\\n</pre><br/>More details on: <a href=\\\"http://en.wikipedia.org/wiki/" + alg + "_sort\\\">http://en.wikipedia.org/wiki/" + alg + "_sort</a><br/>&nbsp;</html>\");\n\nreturn alg.toString();");
            System.out.println(sb.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
