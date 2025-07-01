package org.apache.commons.p000io.input;

import java.io.InputStream;

/* loaded from: Titan.jar:org/apache/commons/io/input/ClosedInputStream.class */
public class ClosedInputStream extends InputStream {
    public static final ClosedInputStream CLOSED_INPUT_STREAM = new ClosedInputStream();

    @Override // java.io.InputStream
    public int read() {
        return -1;
    }
}
