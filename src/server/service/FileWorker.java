package server.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public interface FileWorker {
    String LOG_PATH = "resources"
            + File.separator + "log.txt";

    static void saveInLog(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String readLog() {
        StringBuilder stringBuilder = new StringBuilder();

        try (FileReader reader = new FileReader(LOG_PATH)) {
            int c;

            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }

            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());

            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
