package org.apache.commons.p000io.input;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.p000io.input.ObservableInputStream;

/* loaded from: Titan.jar:org/apache/commons/io/input/MessageDigestCalculatingInputStream.class */
public class MessageDigestCalculatingInputStream extends ObservableInputStream {
    private final MessageDigest messageDigest;

    /* loaded from: Titan.jar:org/apache/commons/io/input/MessageDigestCalculatingInputStream$MessageDigestMaintainingObserver.class */
    public static class MessageDigestMaintainingObserver extends ObservableInputStream.Observer {

        /* renamed from: md */
        private final MessageDigest f5md;

        public MessageDigestMaintainingObserver(MessageDigest pMd) {
            this.f5md = pMd;
        }

        @Override // org.apache.commons.io.input.ObservableInputStream.Observer
        void data(int pByte) throws IOException {
            this.f5md.update((byte) pByte);
        }

        @Override // org.apache.commons.io.input.ObservableInputStream.Observer
        void data(byte[] pBuffer, int pOffset, int pLength) throws IOException {
            this.f5md.update(pBuffer, pOffset, pLength);
        }
    }

    public MessageDigestCalculatingInputStream(InputStream pStream, MessageDigest pDigest) {
        super(pStream);
        this.messageDigest = pDigest;
        add(new MessageDigestMaintainingObserver(pDigest));
    }

    public MessageDigestCalculatingInputStream(InputStream pStream, String pAlgorithm) throws NoSuchAlgorithmException {
        this(pStream, MessageDigest.getInstance(pAlgorithm));
    }

    public MessageDigestCalculatingInputStream(InputStream pStream) throws NoSuchAlgorithmException {
        this(pStream, MessageDigest.getInstance("MD5"));
    }

    public MessageDigest getMessageDigest() {
        return this.messageDigest;
    }
}
