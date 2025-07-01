package org.apache.commons.p000io.filefilter;

import java.util.List;

/* loaded from: Titan.jar:org/apache/commons/io/filefilter/ConditionalFileFilter.class */
public interface ConditionalFileFilter {
    void addFileFilter(IOFileFilter iOFileFilter);

    List<IOFileFilter> getFileFilters();

    boolean removeFileFilter(IOFileFilter iOFileFilter);

    void setFileFilters(List<IOFileFilter> list);
}
