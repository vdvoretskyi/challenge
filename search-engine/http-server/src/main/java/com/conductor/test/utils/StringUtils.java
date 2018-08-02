package com.conductor.test.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class StringUtils {

    private StringUtils() {
    }

    public static List<String> splitByWords(final String text) {
        Objects.requireNonNull(text);
        return Arrays.asList(text.split(WORD_SPLIT_REGEX));
    }
    private static final String WORD_SPLIT_REGEX = "\\s+";
}
