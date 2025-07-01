package org.apache.commons.p000io.output;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: Titan.jar:org/apache/commons/io/output/NullOutputStream.class */
public class NullOutputStream extends OutputStream {
    public static final NullOutputStream NULL_OUTPUT_STREAM = new NullOutputStream();

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) {
    }

    @Override // java.io.OutputStream
    public void write(int b) {
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
    }
}
