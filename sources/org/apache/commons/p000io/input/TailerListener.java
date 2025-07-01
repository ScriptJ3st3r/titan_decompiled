package org.apache.commons.p000io.input;

/* loaded from: Titan.jar:org/apache/commons/io/input/TailerListener.class */
public interface TailerListener {
    void init(Tailer tailer);

    void fileNotFound();

    void fileRotated();

    void handle(String str);

    void handle(Exception exc);
}
