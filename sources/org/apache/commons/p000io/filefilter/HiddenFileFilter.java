package org.apache.commons.p000io.filefilter;

import java.io.File;
import java.io.Serializable;

/* loaded from: Titan.jar:org/apache/commons/io/filefilter/HiddenFileFilter.class */
public class HiddenFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 8930842316112759062L;
    public static final IOFileFilter HIDDEN = new HiddenFileFilter();
    public static final IOFileFilter VISIBLE = new NotFileFilter(HIDDEN);

    protected HiddenFileFilter() {
    }

    @Override // org.apache.commons.p000io.filefilter.AbstractFileFilter, org.apache.commons.p000io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return file.isHidden();
    }
}
