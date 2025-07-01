package org.apache.commons.p000io.monitor;

import java.io.File;

/* loaded from: Titan.jar:org/apache/commons/io/monitor/FileAlterationListener.class */
public interface FileAlterationListener {
    void onStart(FileAlterationObserver fileAlterationObserver);

    void onDirectoryCreate(File file);

    void onDirectoryChange(File file);

    void onDirectoryDelete(File file);

    void onFileCreate(File file);

    void onFileChange(File file);

    void onFileDelete(File file);

    void onStop(FileAlterationObserver fileAlterationObserver);
}
