package org.apache.commons.p000io.filefilter;

import java.io.File;
import java.io.Serializable;

/* loaded from: Titan.jar:org/apache/commons/io/filefilter/FileFileFilter.class */
public class FileFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 5345244090827540862L;
    public static final IOFileFilter FILE = new FileFileFilter();

    protected FileFileFilter() {
    }

    @Override // org.apache.commons.p000io.filefilter.AbstractFileFilter, org.apache.commons.p000io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return file.isFile();
    }
}
