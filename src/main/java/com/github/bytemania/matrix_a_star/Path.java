package com.github.bytemania.matrix_a_star;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.LinkedList;

@Getter
@EqualsAndHashCode
@ToString
public class Path implements Comparable<Path> {

    static Path of(Location location) {
        return new Path(location);
    }

    private final LinkedList<Location> path;
    private int distance;
    private double heuristic;

    private Path() {
        path = new LinkedList<>();
        distance = 0;
        heuristic = 0;
    }

    private Path(Location location) {
        this();
        path.add(location);
    }

    Path add(Location location, double heuristic) {
        Path newPath = new Path();
        newPath.path.addAll(path);
        newPath.path.add(location);
        newPath.distance = distance + 1;
        newPath.heuristic = heuristic;
        return newPath;
    }

    Location last() {
        return path.getLast();
    }

    @Override
    public int compareTo(Path that) {
        if (that == null) return -1;
        else if (heuristic < that.heuristic) return -1;
        else if (heuristic > that.heuristic) return 1;
        else if (distance < that.distance) return -1;
        else if (distance > that.distance) return 1;
        else if(path.size() < that.path.size()) return -1;
        else if(path.size() > that.path.size()) return 1;
        else return 0;
    }

}
