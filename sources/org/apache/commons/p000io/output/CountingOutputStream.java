package org.apache.commons.p000io.output;

import java.io.OutputStream;

/* loaded from: Titan.jar:org/apache/commons/io/output/CountingOutputStream.class */
public class CountingOutputStream extends ProxyOutputStream {
    private long count;

    public CountingOutputStream(OutputStream out) {
        super(out);
        this.count = 0L;
    }

    @Override // org.apache.commons.p000io.output.ProxyOutputStream
    protected synchronized void beforeWrite(int n) {
        this.count += n;
    }

    public int getCount() {
        long result = getByteCount();
        if (result > 2147483647L) {
            throw new ArithmeticException("The byte count " + result + " is too large to be converted to an int");
        }
        return (int) result;
    }

    public int resetCount() {
        long result = resetByteCount();
        if (result > 2147483647L) {
            throw new ArithmeticException("The byte count " + result + " is too large to be converted to an int");
        }
        return (int) result;
    }

    public synchronized long getByteCount() {
        return this.count;
    }

    public synchronized long resetByteCount() {
        long tmp = this.count;
        this.count = 0L;
        return tmp;
    }
}
