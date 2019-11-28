package com.github.bytemania.a_star;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
@EqualsAndHashCode
class Node {
    private final String name;
    private final int penalization;

    @Override
    public String toString() {
        return name + " (" + penalization + ")";
    }
}
