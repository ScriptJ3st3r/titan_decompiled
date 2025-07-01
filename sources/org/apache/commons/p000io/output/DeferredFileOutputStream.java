package org.apache.commons.p000io.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.p000io.FileUtils;
import org.apache.commons.p000io.IOUtils;

/* loaded from: Titan.jar:org/apache/commons/io/output/DeferredFileOutputStream.class */
public class DeferredFileOutputStream extends ThresholdingOutputStream {
    private ByteArrayOutputStream memoryOutputStream;
    private OutputStream currentOutputStream;
    private File outputFile;
    private final String prefix;
    private final String suffix;
    private final File directory;
    private boolean closed;

    public DeferredFileOutputStream(int threshold, File outputFile) {
        this(threshold, outputFile, null, null, null, 1024);
    }

    public DeferredFileOutputStream(int threshold, int initialBufferSize, File outputFile) {
        this(threshold, outputFile, null, null, null, initialBufferSize);
        if (initialBufferSize < 0) {
            throw new IllegalArgumentException("Initial buffer size must be atleast 0.");
        }
    }

    public DeferredFileOutputStream(int threshold, String prefix, String suffix, File directory) {
        this(threshold, null, prefix, suffix, directory, 1024);
        if (prefix == null) {
            throw new IllegalArgumentException("Temporary file prefix is missing");
        }
    }

    public DeferredFileOutputStream(int threshold, int initialBufferSize, String prefix, String suffix, File directory) {
        this(threshold, null, prefix, suffix, directory, initialBufferSize);
        if (prefix == null) {
            throw new IllegalArgumentException("Temporary file prefix is missing");
        }
        if (initialBufferSize < 0) {
            throw new IllegalArgumentException("Initial buffer size must be atleast 0.");
        }
    }

    private DeferredFileOutputStream(int threshold, File outputFile, String prefix, String suffix, File directory, int initialBufferSize) {
        super(threshold);
        this.closed = false;
        this.outputFile = outputFile;
        this.prefix = prefix;
        this.suffix = suffix;
        this.directory = directory;
        this.memoryOutputStream = new ByteArrayOutputStream(initialBufferSize);
        this.currentOutputStream = this.memoryOutputStream;
    }

    @Override // org.apache.commons.p000io.output.ThresholdingOutputStream
    protected OutputStream getStream() throws IOException {
        return this.currentOutputStream;
    }

    @Override // org.apache.commons.p000io.output.ThresholdingOutputStream
    protected void thresholdReached() throws IOException {
        if (this.prefix != null) {
            this.outputFile = File.createTempFile(this.prefix, this.suffix, this.directory);
        }
        FileUtils.forceMkdirParent(this.outputFile);
        FileOutputStream fos = new FileOutputStream(this.outputFile);
        try {
            this.memoryOutputStream.writeTo(fos);
            this.currentOutputStream = fos;
            this.memoryOutputStream = null;
        } catch (IOException e) {
            fos.close();
            throw e;
        }
    }

    public boolean isInMemory() {
        return !isThresholdExceeded();
    }

    public byte[] getData() {
        if (this.memoryOutputStream != null) {
            return this.memoryOutputStream.toByteArray();
        }
        return null;
    }

    public File getFile() {
        return this.outputFile;
    }

    @Override // org.apache.commons.p000io.output.ThresholdingOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this.closed = true;
    }

    public void writeTo(OutputStream out) throws IOException {
        if (!this.closed) {
            throw new IOException("Stream not closed");
        }
        if (isInMemory()) {
            this.memoryOutputStream.writeTo(out);
            return;
        }
        FileInputStream fis = new FileInputStream(this.outputFile);
        Throwable th = null;
        try {
            IOUtils.copy(fis, out);
            if (fis != null) {
                if (0 != 0) {
                    try {
                        fis.close();
                        return;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                        return;
                    }
                }
                fis.close();
            }
        } catch (Throwable th3) {
            if (fis != null) {
                if (0 != 0) {
                    try {
                        fis.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                } else {
                    fis.close();
                }
            }
            throw th3;
        }
    }
}
