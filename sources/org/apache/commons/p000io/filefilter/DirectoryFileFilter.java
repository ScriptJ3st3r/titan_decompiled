package org.apache.commons.p000io.filefilter;

import java.io.File;
import java.io.Serializable;

/* loaded from: Titan.jar:org/apache/commons/io/filefilter/DirectoryFileFilter.class */
public class DirectoryFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -5148237843784525732L;
    public static final IOFileFilter DIRECTORY = new DirectoryFileFilter();
    public static final IOFileFilter INSTANCE = DIRECTORY;

    protected DirectoryFileFilter() {
    }

    @Override // org.apache.commons.p000io.filefilter.AbstractFileFilter, org.apache.commons.p000io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return file.isDirectory();
    }
}
