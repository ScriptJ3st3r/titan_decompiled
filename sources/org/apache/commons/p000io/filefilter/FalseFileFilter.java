package org.apache.commons.p000io.filefilter;

import java.io.File;
import java.io.Serializable;

/* loaded from: Titan.jar:org/apache/commons/io/filefilter/FalseFileFilter.class */
public class FalseFileFilter implements IOFileFilter, Serializable {
    private static final long serialVersionUID = 6210271677940926200L;
    public static final IOFileFilter FALSE = new FalseFileFilter();
    public static final IOFileFilter INSTANCE = FALSE;

    protected FalseFileFilter() {
    }

    @Override // org.apache.commons.p000io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return false;
    }

    @Override // org.apache.commons.p000io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File dir, String name) {
        return false;
    }
}
