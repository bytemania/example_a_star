package com.github.bytemania.a_star;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.LinkedList;

@EqualsAndHashCode
@ToString
class Path implements Comparable<Path> {

    static Path of(String node) {
        return new Path(node);
    }

    private final LinkedList<String> nodes;
    @Getter
    private int weight;
    private int heuristic;

    private Path() {
        nodes = new LinkedList<>();
        weight = 0;
        heuristic = 0;
    }

    private Path(String node) {
        this();
        nodes.add(node);
    }

    void add(String node, int weight, int heuristic) {
        nodes.add(node);
        this.weight += weight;
        this.heuristic = heuristic;
    }

    String lastNode() {
        return nodes.getLast();
    }

    int size() {
        return nodes.size();
    }

    Path copy() {
        Path newPath = new Path();
        newPath.nodes.addAll(this.nodes);
        newPath.weight = this.weight;
        newPath.heuristic = this.heuristic;
        return newPath;
    }

    @Override
    public int compareTo(Path o) {
        if (o == null) return -1;
        else if (heuristic < o.heuristic) return -1;
        else if (heuristic > o.heuristic) return 1;
        else if (weight < o.weight) return -1;
        else if (weight > o.weight) return 1;
        else if(nodes.size() < o.nodes.size()) return -1;
        else if(nodes.size() > o.nodes.size()) return 1;
        else return 0;
    }

}
