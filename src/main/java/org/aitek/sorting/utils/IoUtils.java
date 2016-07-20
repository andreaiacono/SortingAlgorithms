package org.aitek.sorting.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class IoUtils {

    /**
     * transforms a stack trace into a string
     *
     * @param ex The exception where to get the stack trace
     * @return the stack trace
     */
    public static String stackTraceToString(Exception ex) {

        // gets a new stream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // uses it as a source for a PrintWriter
        PrintWriter pwWriter = new PrintWriter(baos, true);

        // writes the stacktrace to the printwriter
        ex.printStackTrace(pwWriter);

        // and return the stream as a string
        return baos.toString();
    }

}
