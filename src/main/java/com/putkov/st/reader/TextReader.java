package com.putkov.st.reader;

import java.io.IOException;

public interface TextReader {
    String textReadFromFile(String path) throws IOException;
}
