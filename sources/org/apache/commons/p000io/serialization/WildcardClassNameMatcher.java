package org.apache.commons.p000io.serialization;

import org.apache.commons.p000io.FilenameUtils;

/* loaded from: Titan.jar:org/apache/commons/io/serialization/WildcardClassNameMatcher.class */
final class WildcardClassNameMatcher implements ClassNameMatcher {
    private final String pattern;

    public WildcardClassNameMatcher(String pattern) {
        this.pattern = pattern;
    }

    @Override // org.apache.commons.p000io.serialization.ClassNameMatcher
    public boolean matches(String className) {
        return FilenameUtils.wildcardMatch(className, this.pattern);
    }
}
