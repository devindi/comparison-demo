package com.devindi.mapper.demo.data;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by devindi on 06.03.18.
 */
public class PerformanceLogger {

    //mapper title, mapping method, number of calls, mapping duration, message
    private static final String ENTRY_FORMAT = "%s;%s;%d;%d;%s";

    private final String mapperTitle;
    private final String outputFilePath;

    private File outFile;

    public PerformanceLogger(String mapperTitle, String outputFilePath) throws IOException {
        this.mapperTitle = mapperTitle;
        this.outputFilePath = outputFilePath;
        init();
    }

    public void init() throws IOException {
        outFile = new File(outputFilePath);
        if (!outFile.exists()) {
            if (!outFile.createNewFile()) {
                throw new IOException("Failed to create output file");
            }
        }
    }

    public void logDuration(String method, int loopCount, long duration) throws IOException {
        String entry = String.format(Locale.US, ENTRY_FORMAT, mapperTitle, method, loopCount, duration, null);
        writeLogEntry(entry);
    }

    public void logException(String method, Exception exc) throws IOException {
        String entry = String.format(Locale.US, ENTRY_FORMAT, mapperTitle, method, -1, -1, exc.getClass().getSimpleName());
        writeLogEntry(entry);
    }

    private void writeLogEntry(String msg) throws IOException {
        Log.d("perf", msg);

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(outFile, true);
            stream.write(msg.getBytes("UTF-8"));
            stream.write("\n".getBytes("UTF-8"));
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}
