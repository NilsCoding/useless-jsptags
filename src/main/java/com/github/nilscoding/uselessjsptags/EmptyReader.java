package com.github.nilscoding.uselessjsptags;

import java.io.IOException;
import java.io.Reader;

/**
 * An empty Reader
 * @author NilsCoding
 */
public class EmptyReader extends Reader {

    public EmptyReader() {
        super();
    }
    
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return -1;
    }

    @Override
    public void close() throws IOException {
    }
    
}
