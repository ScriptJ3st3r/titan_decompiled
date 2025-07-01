package org.apache.commons.p000io.filefilter;

import java.io.File;
import java.io.Serializable;

/* loaded from: Titan.jar:org/apache/commons/io/filefilter/EmptyFileFilter.class */
public class EmptyFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 3631422087512832211L;
    public static final IOFileFilter EMPTY = new EmptyFileFilter();
    public static final IOFileFilter NOT_EMPTY = new NotFileFilter(EMPTY);

    protected EmptyFileFilter() {
    }

    @Override // org.apache.commons.p000io.filefilter.AbstractFileFilter, org.apache.commons.p000io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        if (!file.isDirectory()) {
            return file.length() == 0;
        }
        File[] files = file.listFiles();
        return files == null || files.length == 0;
    }
}
