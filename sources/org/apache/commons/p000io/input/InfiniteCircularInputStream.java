package org.apache.commons.p000io.input;

import java.io.InputStream;

/* loaded from: Titan.jar:org/apache/commons/io/input/InfiniteCircularInputStream.class */
public class InfiniteCircularInputStream extends InputStream {
    private final byte[] repeatedContent;
    private int position = -1;

    public InfiniteCircularInputStream(byte[] repeatedContent) {
        this.repeatedContent = repeatedContent;
    }

    @Override // java.io.InputStream
    public int read() {
        this.position = (this.position + 1) % this.repeatedContent.length;
        return this.repeatedContent[this.position] & 255;
    }
}
