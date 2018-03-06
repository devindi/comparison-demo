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

    private final String mapperTitle;
    private final String outputFilePath;

    public PerformanceLogger(String mapperTitle, String outputFilePath) {
        this.mapperTitle = mapperTitle;
        this.outputFilePath = outputFilePath;
    }

    public void logDuration(String method, int loopCount, long duration) throws IOException {
        String entry = String.format(Locale.US, "%s - %s - %d times in %s millis", mapperTitle, method, loopCount, duration);
        writeLogEntry(entry);
    }

    public void logException(String method, Exception exc) throws IOException {
        String entry = String.format(Locale.US, "%s - %s - failed due to %s", mapperTitle, method, exc.getClass().getSimpleName());
        writeLogEntry(entry);
    }

    private void writeLogEntry(String msg) throws IOException {
        Log.d("perf", msg);
        File outfile = new File(outputFilePath);
        if (!outfile.exists()) {
            if (!outfile.createNewFile()) {
                throw new IOException("Failed to create output file");
            }
        }
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(outfile, true);
            stream.write(msg.getBytes("UTF-8"));
            stream.write("\n".getBytes("UTF-8"));
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}
