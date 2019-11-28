package com.github.bytemania.a_star;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Main {
    public static void main(String[] args) {
        Set<Node> nodes = Set.of(
                Node.of("S", 10),
                Node.of("A", 9),
                Node.of("B", 7),
                Node.of("C", 8),
                Node.of("D", 8),
                Node.of("F", 6),
                Node.of("G", 2),
                Node.of("H", 6),
                Node.of("I", 4),
                Node.of("J", 4),
                Node.of("K", 3),
                Node.of("L", 6),
                Node.of("E", 0));

        Set<Edge> edges = Set.of(
                Edge.of("S", "A", 7),
                Edge.of("S", "B", 2),
                Edge.of("S", "C", 3),
                Edge.of("A", "B", 3),
                Edge.of("A", "D", 4),
                Edge.of("B", "D", 4),
                Edge.of("B", "H", 1),
                Edge.of("C", "L", 2),
                Edge.of("D", "F", 5),
                Edge.of("E", "G", 2),
                Edge.of("E", "K", 5),
                Edge.of("F", "H", 3),
                Edge.of("G", "H", 2),
                Edge.of("I", "J", 6),
                Edge.of("I", "K", 4),
                Edge.of("I", "L", 4),
                Edge.of("J", "L", 4),
                Edge.of("J", "K",4));

        Graph g = Graph.of(nodes, edges);
        Optional<Path> path = g.path("S", "E");
        System.out.println(g.toString());

        System.out.println(path);
    }
}
