package org.apache.commons.p000io.output;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: Titan.jar:org/apache/commons/io/output/ChunkedOutputStream.class */
public class ChunkedOutputStream extends FilterOutputStream {
    private static final int DEFAULT_CHUNK_SIZE = 4096;
    private final int chunkSize;

    public ChunkedOutputStream(OutputStream stream, int chunkSize) {
        super(stream);
        if (chunkSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.chunkSize = chunkSize;
    }

    public ChunkedOutputStream(OutputStream stream) {
        this(stream, DEFAULT_CHUNK_SIZE);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] data, int srcOffset, int length) throws IOException {
        int bytes = length;
        int i = srcOffset;
        while (true) {
            int dstOffset = i;
            if (bytes > 0) {
                int chunk = Math.min(bytes, this.chunkSize);
                this.out.write(data, dstOffset, chunk);
                bytes -= chunk;
                i = dstOffset + chunk;
            } else {
                return;
            }
        }
    }
}
