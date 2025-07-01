package org.apache.commons.p000io.output;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/* loaded from: Titan.jar:org/apache/commons/io/output/FileWriterWithEncoding.class */
public class FileWriterWithEncoding extends Writer {
    private final Writer out;

    public FileWriterWithEncoding(String filename, String encoding) throws IOException {
        this(new File(filename), encoding, false);
    }

    public FileWriterWithEncoding(String filename, String encoding, boolean append) throws IOException {
        this(new File(filename), encoding, append);
    }

    public FileWriterWithEncoding(String filename, Charset encoding) throws IOException {
        this(new File(filename), encoding, false);
    }

    public FileWriterWithEncoding(String filename, Charset encoding, boolean append) throws IOException {
        this(new File(filename), encoding, append);
    }

    public FileWriterWithEncoding(String filename, CharsetEncoder encoding) throws IOException {
        this(new File(filename), encoding, false);
    }

    public FileWriterWithEncoding(String filename, CharsetEncoder encoding, boolean append) throws IOException {
        this(new File(filename), encoding, append);
    }

    public FileWriterWithEncoding(File file, String encoding) throws IOException {
        this(file, encoding, false);
    }

    public FileWriterWithEncoding(File file, String encoding, boolean append) throws IOException {
        this.out = initWriter(file, encoding, append);
    }

    public FileWriterWithEncoding(File file, Charset encoding) throws IOException {
        this(file, encoding, false);
    }

    public FileWriterWithEncoding(File file, Charset encoding, boolean append) throws IOException {
        this.out = initWriter(file, encoding, append);
    }

    public FileWriterWithEncoding(File file, CharsetEncoder encoding) throws IOException {
        this(file, encoding, false);
    }

    public FileWriterWithEncoding(File file, CharsetEncoder encoding, boolean append) throws IOException {
        this.out = initWriter(file, encoding, append);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.io.Writer initWriter(java.io.File r5, java.lang.Object r6, boolean r7) throws java.io.IOException {
        /*
            r0 = r5
            if (r0 != 0) goto Le
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r1 = r0
            java.lang.String r2 = "File is missing"
            r1.<init>(r2)
            throw r0
        Le:
            r0 = r6
            if (r0 != 0) goto L1c
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r1 = r0
            java.lang.String r2 = "Encoding is missing"
            r1.<init>(r2)
            throw r0
        L1c:
            r0 = 0
            r8 = r0
            r0 = r5
            boolean r0 = r0.exists()
            r9 = r0
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L63
            r1 = r0
            r2 = r5
            r3 = r7
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L63
            r8 = r0
            r0 = r6
            boolean r0 = r0 instanceof java.nio.charset.Charset     // Catch: java.lang.Throwable -> L63
            if (r0 == 0) goto L42
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L63
            r1 = r0
            r2 = r8
            r3 = r6
            java.nio.charset.Charset r3 = (java.nio.charset.Charset) r3     // Catch: java.lang.Throwable -> L63
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L63
            return r0
        L42:
            r0 = r6
            boolean r0 = r0 instanceof java.nio.charset.CharsetEncoder     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L63
            if (r0 == 0) goto L56
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L63
            r1 = r0
            r2 = r8
            r3 = r6
            java.nio.charset.CharsetEncoder r3 = (java.nio.charset.CharsetEncoder) r3     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L63
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L63
            return r0
        L56:
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L63
            r1 = r0
            r2 = r8
            r3 = r6
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L63
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L63 java.lang.Throwable -> L63
            return r0
        L63:
            r10 = move-exception
            r0 = r8
            if (r0 == 0) goto L6d
            r0 = r8
            r0.close()     // Catch: java.io.IOException -> L70
        L6d:
            goto L79
        L70:
            r11 = move-exception
            r0 = r10
            r1 = r11
            r0.addSuppressed(r1)
        L79:
            r0 = r9
            if (r0 != 0) goto L83
            r0 = r5
            boolean r0 = org.apache.commons.p000io.FileUtils.deleteQuietly(r0)
        L83:
            r0 = r10
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.p000io.output.FileWriterWithEncoding.initWriter(java.io.File, java.lang.Object, boolean):java.io.Writer");
    }

    @Override // java.io.Writer
    public void write(int idx) throws IOException {
        this.out.write(idx);
    }

    @Override // java.io.Writer
    public void write(char[] chr) throws IOException {
        this.out.write(chr);
    }

    @Override // java.io.Writer
    public void write(char[] chr, int st, int end) throws IOException {
        this.out.write(chr, st, end);
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        this.out.write(str);
    }

    @Override // java.io.Writer
    public void write(String str, int st, int end) throws IOException {
        this.out.write(str, st, end);
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }
}
