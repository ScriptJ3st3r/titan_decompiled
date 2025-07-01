package org.apache.commons.p000io.input;

import java.io.InputStream;

/* loaded from: Titan.jar:org/apache/commons/io/input/CloseShieldInputStream.class */
public class CloseShieldInputStream extends ProxyInputStream {
    public CloseShieldInputStream(InputStream in) {
        super(in);
    }

    @Override // org.apache.commons.p000io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.in = new ClosedInputStream();
    }
}
