package org.apache.commons.p000io.input;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.commons.p000io.Charsets;
import org.apache.commons.p000io.IOUtils;

/* loaded from: Titan.jar:org/apache/commons/io/input/ReversedLinesFileReader.class */
public class ReversedLinesFileReader implements Closeable {
    private final int blockSize;
    private final Charset encoding;
    private final RandomAccessFile randomAccessFile;
    private final long totalByteLength;
    private final long totalBlockCount;
    private final byte[][] newLineSequences;
    private final int avoidNewlineSplitBufferSize;
    private final int byteDecrement;
    private FilePart currentFilePart;
    private boolean trailingNewlineOfFileSkipped;

    @Deprecated
    public ReversedLinesFileReader(File file) throws IOException {
        this(file, 4096, Charset.defaultCharset());
    }

    public ReversedLinesFileReader(File file, Charset charset) throws IOException {
        this(file, 4096, charset);
    }

    /* JADX WARN: Type inference failed for: r1v24, types: [byte[], byte[][]] */
    public ReversedLinesFileReader(File file, int blockSize, Charset encoding) throws IOException {
        this.trailingNewlineOfFileSkipped = false;
        this.blockSize = blockSize;
        this.encoding = encoding;
        Charset charset = Charsets.toCharset(encoding);
        CharsetEncoder charsetEncoder = charset.newEncoder();
        float maxBytesPerChar = charsetEncoder.maxBytesPerChar();
        if (maxBytesPerChar == 1.0f) {
            this.byteDecrement = 1;
        } else if (charset == StandardCharsets.UTF_8) {
            this.byteDecrement = 1;
        } else if (charset == Charset.forName("Shift_JIS") || charset == Charset.forName("windows-31j") || charset == Charset.forName("x-windows-949") || charset == Charset.forName("gbk") || charset == Charset.forName("x-windows-950")) {
            this.byteDecrement = 1;
        } else if (charset == StandardCharsets.UTF_16BE || charset == StandardCharsets.UTF_16LE) {
            this.byteDecrement = 2;
        } else {
            if (charset == StandardCharsets.UTF_16) {
                throw new UnsupportedEncodingException("For UTF-16, you need to specify the byte order (use UTF-16BE or UTF-16LE)");
            }
            throw new UnsupportedEncodingException("Encoding " + encoding + " is not supported yet (feel free to submit a patch)");
        }
        this.newLineSequences = new byte[]{IOUtils.LINE_SEPARATOR_WINDOWS.getBytes(encoding), IOUtils.LINE_SEPARATOR_UNIX.getBytes(encoding), "\r".getBytes(encoding)};
        this.avoidNewlineSplitBufferSize = this.newLineSequences[0].length;
        this.randomAccessFile = new RandomAccessFile(file, "r");
        this.totalByteLength = this.randomAccessFile.length();
        int lastBlockLength = (int) (this.totalByteLength % blockSize);
        if (lastBlockLength > 0) {
            this.totalBlockCount = (this.totalByteLength / blockSize) + 1;
        } else {
            this.totalBlockCount = this.totalByteLength / blockSize;
            if (this.totalByteLength > 0) {
                lastBlockLength = blockSize;
            }
        }
        this.currentFilePart = new FilePart(this.totalBlockCount, lastBlockLength, null);
    }

    public ReversedLinesFileReader(File file, int blockSize, String encoding) throws IOException {
        this(file, blockSize, Charsets.toCharset(encoding));
    }

    public String readLine() throws IOException {
        String line;
        String readLine = this.currentFilePart.readLine();
        while (true) {
            line = readLine;
            if (line != null) {
                break;
            }
            this.currentFilePart = this.currentFilePart.rollOver();
            if (this.currentFilePart == null) {
                break;
            }
            readLine = this.currentFilePart.readLine();
        }
        if ("".equals(line) && !this.trailingNewlineOfFileSkipped) {
            this.trailingNewlineOfFileSkipped = true;
            line = readLine();
        }
        return line;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.randomAccessFile.close();
    }

    /* loaded from: Titan.jar:org/apache/commons/io/input/ReversedLinesFileReader$FilePart.class */
    private class FilePart {

        /* renamed from: no */
        private final long f6no;
        private final byte[] data;
        private byte[] leftOver;
        private int currentLastBytePos;

        private FilePart(long no, int length, byte[] leftOverOfLastFilePart) throws IOException {
            this.f6no = no;
            int dataLength = length + (leftOverOfLastFilePart != null ? leftOverOfLastFilePart.length : 0);
            this.data = new byte[dataLength];
            long off = (no - 1) * ReversedLinesFileReader.this.blockSize;
            if (no > 0) {
                ReversedLinesFileReader.this.randomAccessFile.seek(off);
                int countRead = ReversedLinesFileReader.this.randomAccessFile.read(this.data, 0, length);
                if (countRead != length) {
                    throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
                }
            }
            if (leftOverOfLastFilePart != null) {
                System.arraycopy(leftOverOfLastFilePart, 0, this.data, length, leftOverOfLastFilePart.length);
            }
            this.currentLastBytePos = this.data.length - 1;
            this.leftOver = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FilePart rollOver() throws IOException {
            if (this.currentLastBytePos > -1) {
                throw new IllegalStateException("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=" + this.currentLastBytePos);
            }
            if (this.f6no > 1) {
                return ReversedLinesFileReader.this.new FilePart(this.f6no - 1, ReversedLinesFileReader.this.blockSize, this.leftOver);
            }
            if (this.leftOver != null) {
                throw new IllegalStateException("Unexpected leftover of the last block: leftOverOfThisFilePart=" + new String(this.leftOver, ReversedLinesFileReader.this.encoding));
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String readLine() throws IOException {
            String line = null;
            boolean isLastFilePart = this.f6no == 1;
            int i = this.currentLastBytePos;
            while (true) {
                if (i > -1) {
                    if (!isLastFilePart && i < ReversedLinesFileReader.this.avoidNewlineSplitBufferSize) {
                        createLeftOver();
                        break;
                    }
                    int newLineMatchByteCount = getNewLineMatchByteCount(this.data, i);
                    if (newLineMatchByteCount <= 0) {
                        i -= ReversedLinesFileReader.this.byteDecrement;
                        if (i < 0) {
                            createLeftOver();
                            break;
                        }
                    } else {
                        int lineStart = i + 1;
                        int lineLengthBytes = (this.currentLastBytePos - lineStart) + 1;
                        if (lineLengthBytes < 0) {
                            throw new IllegalStateException("Unexpected negative line length=" + lineLengthBytes);
                        }
                        byte[] lineData = new byte[lineLengthBytes];
                        System.arraycopy(this.data, lineStart, lineData, 0, lineLengthBytes);
                        line = new String(lineData, ReversedLinesFileReader.this.encoding);
                        this.currentLastBytePos = i - newLineMatchByteCount;
                    }
                } else {
                    break;
                }
            }
            if (isLastFilePart && this.leftOver != null) {
                line = new String(this.leftOver, ReversedLinesFileReader.this.encoding);
                this.leftOver = null;
            }
            return line;
        }

        private void createLeftOver() {
            int lineLengthBytes = this.currentLastBytePos + 1;
            if (lineLengthBytes > 0) {
                this.leftOver = new byte[lineLengthBytes];
                System.arraycopy(this.data, 0, this.leftOver, 0, lineLengthBytes);
            } else {
                this.leftOver = null;
            }
            this.currentLastBytePos = -1;
        }

        private int getNewLineMatchByteCount(byte[] data, int i) {
            for (byte[] newLineSequence : ReversedLinesFileReader.this.newLineSequences) {
                boolean match = true;
                for (int j = newLineSequence.length - 1; j >= 0; j--) {
                    int k = (i + j) - (newLineSequence.length - 1);
                    match &= k >= 0 && data[k] == newLineSequence[j];
                }
                if (match) {
                    return newLineSequence.length;
                }
            }
            return 0;
        }
    }
}
