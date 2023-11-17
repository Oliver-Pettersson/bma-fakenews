package org.bma.simulator.utils;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ObjectArrayToCSVUtils {
    private ObjectArrayToCSVUtils() {}

    public static void writeDataToCSV(Object[][] data, String csvFilePath) throws IOException {
        // Write content to CSV file
        try (FileWriter csvWriter = new FileWriter(csvFilePath)) {
            Set<String> uniqueKeys = new LinkedHashSet<>();

            for (Object[] row : data) {
                uniqueKeys.add(row[0].toString());
            }

            // Write header row
            for (String key : uniqueKeys) {
                csvWriter.append(key).append(",");
            }
            csvWriter.append("\n");

            // Write data rows
            boolean isFirstRow = true;
            for (Object[] row : data) {
                if (!isFirstRow && row[0].equals("id")) {
                    csvWriter.append("\n");
                }
                csvWriter.append((row[1] != null ? row[1].toString() : "")).append(",");
                isFirstRow = false;
            }
        }
    }

}