package org.apache.commons.p000io.input;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: Titan.jar:org/apache/commons/io/input/BoundedInputStream.class */
public class BoundedInputStream extends InputStream {

    /* renamed from: in */
    private final InputStream f4in;
    private final long max;
    private long pos;
    private long mark;
    private boolean propagateClose;

    public BoundedInputStream(InputStream in, long size) {
        this.pos = 0L;
        this.mark = -1L;
        this.propagateClose = true;
        this.max = size;
        this.f4in = in;
    }

    public BoundedInputStream(InputStream in) {
        this(in, -1L);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.max >= 0 && this.pos >= this.max) {
            return -1;
        }
        int result = this.f4in.read();
        this.pos++;
        return result;
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.max >= 0 && this.pos >= this.max) {
            return -1;
        }
        long maxRead = this.max >= 0 ? Math.min(len, this.max - this.pos) : len;
        int bytesRead = this.f4in.read(b, off, (int) maxRead);
        if (bytesRead == -1) {
            return -1;
        }
        this.pos += bytesRead;
        return bytesRead;
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        long toSkip = this.max >= 0 ? Math.min(n, this.max - this.pos) : n;
        long skippedBytes = this.f4in.skip(toSkip);
        this.pos += skippedBytes;
        return skippedBytes;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.max >= 0 && this.pos >= this.max) {
            return 0;
        }
        return this.f4in.available();
    }

    public String toString() {
        return this.f4in.toString();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.propagateClose) {
            this.f4in.close();
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.f4in.reset();
        this.pos = this.mark;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int readlimit) {
        this.f4in.mark(readlimit);
        this.mark = this.pos;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f4in.markSupported();
    }

    public boolean isPropagateClose() {
        return this.propagateClose;
    }

    public void setPropagateClose(boolean propagateClose) {
        this.propagateClose = propagateClose;
    }
}
