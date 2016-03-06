package com.github.nilscoding.uselessjsptags;

import java.io.IOException;
import java.io.Writer;

/**
 * A Writer that discards all data
 * @author NilsCoding
 */
public class NullWriter extends Writer {

    public NullWriter() {
        super();
    }
    
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }
    
}
