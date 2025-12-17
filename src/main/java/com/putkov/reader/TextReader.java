package com.putkov.reader;

import java.io.IOException;

public interface TextReader {
    String textReadFromFile(String path) throws IOException;
}
