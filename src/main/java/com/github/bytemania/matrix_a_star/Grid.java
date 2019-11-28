package com.github.bytemania.matrix_a_star;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grid {

    static Grid of(int[][] grid) {
        return new Grid(grid);
    }

    static final int OPEN = 0;
    static final int CLOSE = 1;

    private final int[][] grid;

    private Grid(int[][] grid) {
        this.grid = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            this.grid[i] = new int[grid[i].length];
            for (int j = 0; j < grid[i].length; j++)
                this.grid[i][j] = grid[i][j];
        }
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            strb.append("| ");
            for (int j = 0; j < grid[i].length; j++)
                strb.append(grid[i][j] == OPEN ? "  OPEN |" : " CLOSE |");
            strb.append("\n");
        }
        return strb.toString();
    }

    Optional<Path> path(Location from, Location to) {
        Optional<Path> found = Optional.empty();
        PriorityQueue<Path> open = new PriorityQueue<>();
        List<Path> closed = new ArrayList<>();
        open.add(Path.of(from));
        while (found.isEmpty() && !open.isEmpty()) {
            Path shortestPath = open.poll();
            Collection<Path> subPaths = subPaths(shortestPath, to);
            found = subPaths.stream().filter(path -> path.last().equals(to)).findAny();
            open.addAll(subPaths);
            if (subPaths.isEmpty()) {
                closed.add(shortestPath);
            }
        }

        return found;
    }

    private Collection<Path> subPaths(Path initialPath, Location to) {
        return neighbours(initialPath)
                .map(neighbour -> calculatePath(neighbour, initialPath, to))
                .collect(Collectors.toList());
    }

    private Path calculatePath(Location neighbour, Path currentPath,  Location to) {
        return currentPath.add(neighbour, currentPath.getDistance() + 1 + heuristic(neighbour, to));
    }

    private double heuristic(Location current, Location end) {
        return manhattan(current, end) + diagonal(current, end) + euclidian(current, end);
    }

    private Stream<Location> neighbours(Path initialPath) {
        return initialPath.last().neighbours().stream()
                .filter(l -> isNew(initialPath, l))
                .filter(this::isValidLocation)
                .filter(this::isOpen);
    }

    private int manhattan(Location start, Location end) {
        return Math.abs(start.getRow() - end.getRow()) + Math.abs(start.getColumn() - end.getColumn());
    }

    private int diagonal(Location start, Location end) {
        return Math.max(Math.abs(start.getRow() - end.getRow()), Math.abs(start.getColumn() - end.getColumn()));
    }

    private double euclidian(Location start, Location end) {
        return Math.sqrt(Math.pow(start.getRow() - end.getRow(), 2) + Math.pow(start.getColumn() - end.getColumn(), 2));
    }

    private boolean isNew(Path initialPath, Location location) {
        return !initialPath.getPath().contains(location);
    }

    private boolean isOpen(Location location) {
        return grid[location.getRow()][location.getColumn()] == OPEN;
    }

    private boolean isValidLocation(Location location) {
        return location.getRow() >= 0 && location.getRow() < grid.length &&
                location.getColumn() >= 0 && location.getColumn() < grid[location.getRow()].length;
    }
}
