package com.github.bytemania.matrix_a_star;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor(staticName = "of")
@Getter
@EqualsAndHashCode
@ToString
class Location {
    private final int row;
    private final int column;

    private Location up() {
        return Location.of(row - 1, column);
    }

    private Location down() {
        return Location.of(row + 1, column);
    }

    private Location left() {
        return Location.of(row, column - 1);
    }

    private Location right() {
        return Location.of(row, column + 1);
    }

    List<Location> neighbours() {
        return List.of(up(), right(), down(), left());
    }
}
