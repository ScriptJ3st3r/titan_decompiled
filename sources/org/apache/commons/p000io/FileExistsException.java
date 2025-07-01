package org.apache.commons.p000io;

import java.io.File;
import java.io.IOException;

/* loaded from: Titan.jar:org/apache/commons/io/FileExistsException.class */
public class FileExistsException extends IOException {
    private static final long serialVersionUID = 1;

    public FileExistsException() {
    }

    public FileExistsException(String message) {
        super(message);
    }

    public FileExistsException(File file) {
        super("File " + file + " exists");
    }
}
