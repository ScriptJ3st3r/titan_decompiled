package org.apache.commons.p000io.filefilter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: Titan.jar:org/apache/commons/io/filefilter/MagicNumberFileFilter.class */
public class MagicNumberFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -547733176983104172L;
    private final byte[] magicNumbers;
    private final long byteOffset;

    public MagicNumberFileFilter(byte[] magicNumber) {
        this(magicNumber, 0L);
    }

    public MagicNumberFileFilter(String magicNumber) {
        this(magicNumber, 0L);
    }

    public MagicNumberFileFilter(String magicNumber, long offset) {
        if (magicNumber == null) {
            throw new IllegalArgumentException("The magic number cannot be null");
        }
        if (magicNumber.isEmpty()) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        this.magicNumbers = magicNumber.getBytes(Charset.defaultCharset());
        this.byteOffset = offset;
    }

    public MagicNumberFileFilter(byte[] magicNumber, long offset) {
        if (magicNumber == null) {
            throw new IllegalArgumentException("The magic number cannot be null");
        }
        if (magicNumber.length == 0) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        this.magicNumbers = new byte[magicNumber.length];
        System.arraycopy(magicNumber, 0, this.magicNumbers, 0, magicNumber.length);
        this.byteOffset = offset;
    }

    @Override // org.apache.commons.p000io.filefilter.AbstractFileFilter, org.apache.commons.p000io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        if (file != null && file.isFile() && file.canRead()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                Throwable th = null;
                try {
                    try {
                        byte[] fileBytes = new byte[this.magicNumbers.length];
                        randomAccessFile.seek(this.byteOffset);
                        int read = randomAccessFile.read(fileBytes);
                        if (read == this.magicNumbers.length) {
                            boolean equals = Arrays.equals(this.magicNumbers, fileBytes);
                            if (randomAccessFile != null) {
                                if (0 != 0) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                } else {
                                    randomAccessFile.close();
                                }
                            }
                            return equals;
                        }
                        if (randomAccessFile != null) {
                            if (0 != 0) {
                                try {
                                    randomAccessFile.close();
                                } catch (Throwable th3) {
                                    th.addSuppressed(th3);
                                }
                            } else {
                                randomAccessFile.close();
                            }
                        }
                        return false;
                    } finally {
                    }
                } catch (Throwable th4) {
                    th = th4;
                    throw th4;
                }
            } catch (IOException e) {
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // org.apache.commons.p000io.filefilter.AbstractFileFilter
    public String toString() {
        return super.toString() + "(" + new String(this.magicNumbers, Charset.defaultCharset()) + "," + this.byteOffset + ")";
    }
}
