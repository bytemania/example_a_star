package com.github.bytemania.a_star;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
@EqualsAndHashCode
class Edge {
    private final String a;
    private final String b;
    private final int weight;

    @Override
    public String toString() {
        return a + " <---> " + b + " (" + weight + ")" ;
    }
}
