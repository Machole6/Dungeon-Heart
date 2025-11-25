package nl.saxion.game.dungeonheart.managers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public class LogsManager {
    private static final Logger logger = LoggerFactory.getLogger(LogsManager.class);

    private static final int BUFFER_SIZE = 100;
    private static final Deque<String> rollingBuffer = new ArrayDeque<>(BUFFER_SIZE);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            flushBufferToFile("logs/frame.log");
        }));
    }

    private static void addToBuffer(String message) {
        if (rollingBuffer.size() == BUFFER_SIZE) {
            rollingBuffer.removeFirst();
        }
        rollingBuffer.addLast(message);
    }

    public static void flushBufferToFile(String filename) {
        try {
            File file = new File(filename);

            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter fw = new FileWriter(file, false)) {
                for (String msg : rollingBuffer) {
                    fw.write(msg + System.lineSeparator());
                }
                fw.flush();
            }
        } catch (IOException io) {
            logger.error("Failed to flush buffer to file: {}", filename, io);
        }
    }

    public static void runWithLogging(String description, Runnable task) {
        try {
            addToBuffer("INFO: " + description);
            task.run();
        } catch (Exception e) {
            logger.error("Task failed: {}", description, e);
            addToBuffer("ERROR: Task failed: " + description + " - " + e.getMessage());
            flushBufferToFile("logs/last_frame.log");
        }
    }

    public static <T> T callWithLogging(String description, Supplier<T> task) {
        try {
            addToBuffer("INFO: " + description);
            return task.get();
        } catch (Exception e) {
            logger.error("Task failed: {}", description, e);
            addToBuffer("ERROR: Task failed: " + description + " - " + e.getMessage());
            flushBufferToFile("logs/last_frame.log");
            return null;
        }
    }

    public static void clearBuffer() {
        rollingBuffer.clear();
    }
}