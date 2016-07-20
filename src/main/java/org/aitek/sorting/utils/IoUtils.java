package org.aitek.sorting.utils;

import java.io.*;
import java.util.stream.Collectors;

public class IoUtils {

    public static String stackTraceToString(Exception ex) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pwWriter = new PrintWriter(baos, true);
        ex.printStackTrace(pwWriter);
        return baos.toString();
    }

    public static String readResourceAsString(String filename) {
        InputStream stream = IoUtils.class.getResourceAsStream(filename);
        if (stream == null) {
            return "The description file [" + filename + "] was not found in resources directory.";
        }
        return new BufferedReader(new InputStreamReader(stream))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
