package org.apache.commons.p000io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/* loaded from: Titan.jar:org/apache/commons/io/filefilter/IOFileFilter.class */
public interface IOFileFilter extends FileFilter, FilenameFilter {
    @Override // java.io.FileFilter
    boolean accept(File file);

    boolean accept(File file, String str);
}
