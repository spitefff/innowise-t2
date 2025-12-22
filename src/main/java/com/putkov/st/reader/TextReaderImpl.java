package com.putkov.st.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TextReaderImpl implements TextReader {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String textReadFromFile(String path) throws IOException {
        if (path == null || path.isBlank()) {
            logger.error("Path is incorrect '{}'", path);
            throw new IllegalArgumentException("Path is incorrect!");
        }

        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            if (inputStream == null) {
                logger.error("File '{}' not found in resources", path);
                throw new IOException("File not found: " + path);
            }
            logger.info("Input file '{}' data read", path);
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Input file '{}' read error", path);
            throw new IOException("Input file read error!", e);
        }
    }
}
