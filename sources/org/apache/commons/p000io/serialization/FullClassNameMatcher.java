package org.apache.commons.p000io.serialization;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: Titan.jar:org/apache/commons/io/serialization/FullClassNameMatcher.class */
final class FullClassNameMatcher implements ClassNameMatcher {
    private final Set<String> classesSet;

    public FullClassNameMatcher(String... classes) {
        this.classesSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(classes)));
    }

    @Override // org.apache.commons.p000io.serialization.ClassNameMatcher
    public boolean matches(String className) {
        return this.classesSet.contains(className);
    }
}
