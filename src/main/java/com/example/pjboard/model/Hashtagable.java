package com.example.pjboard.model;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Hashtagable {
    String provideStringWithHashtags();

    default Collection<String> extractHashtags() {
        return Stream.of(provideStringWithHashtags().split("[\"'`()\\[\\]{},.?!:;*+/|\\\\<=>_\\-~@$%^&\\s]"))
                .filter(w -> w.startsWith("#"))
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }
}
