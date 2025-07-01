package org.apache.commons.p000io.serialization;

import java.util.regex.Pattern;

/* loaded from: Titan.jar:org/apache/commons/io/serialization/RegexpClassNameMatcher.class */
final class RegexpClassNameMatcher implements ClassNameMatcher {
    private final Pattern pattern;

    public RegexpClassNameMatcher(String regex) {
        this(Pattern.compile(regex));
    }

    public RegexpClassNameMatcher(Pattern pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Null pattern");
        }
        this.pattern = pattern;
    }

    @Override // org.apache.commons.p000io.serialization.ClassNameMatcher
    public boolean matches(String className) {
        return this.pattern.matcher(className).matches();
    }
}
