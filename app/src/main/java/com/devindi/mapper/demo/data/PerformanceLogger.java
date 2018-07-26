package com.devindi.mapper.demo.data;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by devindi
 */
public class PerformanceLogger {

    //device, mapper title, mapping method, mapping duration
    private static final String ENTRY_FORMAT = "%s;%s;%s;%d";

    private final String mapperTitle;
    private final String outputFilePath;
    private final String deviceName;

    private File outFile;

    public PerformanceLogger(String deviceName, String mapperTitle, String outputFilePath) throws IOException {
        this.mapperTitle = mapperTitle;
        this.outputFilePath = outputFilePath;
        this.deviceName = deviceName;
        init();
    }

    private void init() throws IOException {
        outFile = new File(outputFilePath);
        if (!outFile.exists()) {
            if (!outFile.createNewFile()) {
                throw new IOException("Failed to create output file");
            }
        }
    }

    public void logDuration(String method, long duration) throws IOException {
        String entry = String.format(Locale.US, ENTRY_FORMAT, deviceName, mapperTitle, method, duration);
        writeLogEntry(entry);
    }

    public void logException(String method) throws IOException {
        logDuration(method, -1);
    }

    private void writeLogEntry(String msg) throws IOException {
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
