package org.apache.commons.p000io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/* loaded from: Titan.jar:org/apache/commons/io/comparator/DefaultFileComparator.class */
public class DefaultFileComparator extends AbstractFileComparator implements Serializable {
    private static final long serialVersionUID = 3260141861365313518L;
    public static final Comparator<File> DEFAULT_COMPARATOR = new DefaultFileComparator();
    public static final Comparator<File> DEFAULT_REVERSE = new ReverseComparator(DEFAULT_COMPARATOR);

    @Override // org.apache.commons.p000io.comparator.AbstractFileComparator
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // org.apache.commons.p000io.comparator.AbstractFileComparator
    public /* bridge */ /* synthetic */ List sort(List list) {
        return super.sort((List<File>) list);
    }

    @Override // org.apache.commons.p000io.comparator.AbstractFileComparator
    public /* bridge */ /* synthetic */ File[] sort(File[] fileArr) {
        return super.sort(fileArr);
    }

    @Override // java.util.Comparator
    public int compare(File file1, File file2) {
        return file1.compareTo(file2);
    }
}
