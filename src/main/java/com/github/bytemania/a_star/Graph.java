package com.github.bytemania.a_star;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
public class Graph {

    static Graph of(Set<Node> nodes, Set<Edge> edges) {
        return new Graph(nodes, edges);
    }

    private Map<String, Node> nodes;
    private Map<String, Set<Edge>> transitions;

    private Graph(Set<Node> nodes, Set<Edge> edges) {
        this.nodes = nodes.stream().collect(Collectors.toMap(Node::getName, Function.identity()));

        this.transitions = new TreeMap<>();
        edges.forEach(e -> {
            if (!transitions.containsKey(e.getA())) this.transitions.put(e.getA(), new HashSet<>());
            if (!transitions.containsKey(e.getB())) this.transitions.put(e.getB(), new HashSet<>());
            this.transitions.get(e.getA()).add(e);
            this.transitions.get(e.getB()).add(e);
        });
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();

        strb.append("Nodes:").append("\n");
        nodes.values().forEach(node -> strb.append("\t").append(node).append("\n"));

        strb.append("\n").append("Transitions:").append("\n");

        for (String key: transitions.keySet()) {
            for (Edge edge : transitions.get(key)) {
                strb.append("\t").append(key);
                strb.append(" ---> ");
                strb.append(key.equals(edge.getA()) ? edge.getB() : edge.getA());
                strb.append(" (").append(edge.getWeight()).append(")").append("\n");
            }
            strb.append("\n");
        }
        return strb.toString();
    }

    Optional<Path> path(String from, String to) {
        Optional<Path> found = Optional.empty();
        SortedSet<Path> paths = new TreeSet<>();
        Node current = nodes.get(from);
        paths.add(Path.of(current.getName()));
        while (found.isEmpty() && paths.first().size() < nodes.size()) {
            Collection<Path> newPaths = subPaths(paths.first());
            found = newPaths.stream().filter(p -> p.lastNode().equals(to)).findAny();
            paths.remove(paths.first());
            paths.addAll(newPaths);
        }
        return found;
    }

    private Collection<Path> subPaths(Path initialPath) {
        Collection<Path> paths = new ArrayList<>();
        String lastNode = initialPath.lastNode();
        for (Edge edge: transitions.get(lastNode))
            paths.add(calculatePath(initialPath, edge));
        return paths;
    }

    private Path calculatePath(Path initialPath, Edge edge) {
        String nextNode = edge.getA().equals(initialPath.lastNode()) ? edge.getB() : edge.getA();
        int distance = edge.getWeight();
        int penalization = nodes.get(nextNode).getPenalization();
        int weight = initialPath.getWeight() + distance;
        int heuristic = weight + penalization;

        Path path = initialPath.copy();
        path.add(nextNode, distance, heuristic);
        return path;
    }
}
