package com.github.bytemania.matrix_a_star;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.github.bytemania.matrix_a_star.Grid.CLOSE;
import static com.github.bytemania.matrix_a_star.Grid.OPEN;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Main {

    public static void main(String[] args) {
        int [][] map1 = {
                { OPEN,  OPEN,  OPEN,  OPEN, CLOSE, CLOSE, CLOSE, CLOSE},
                { OPEN,  OPEN,  OPEN,  OPEN,  OPEN, CLOSE, CLOSE, CLOSE},
                {CLOSE, CLOSE,  OPEN, CLOSE, CLOSE,  OPEN,  OPEN,  OPEN},
                {CLOSE,  OPEN,  OPEN,  OPEN,  OPEN,  OPEN,  OPEN,  OPEN},
        };

        int[][] map2 = {
                { OPEN,  OPEN,  OPEN,  OPEN,  OPEN},
                { OPEN,  OPEN,  OPEN, CLOSE,  OPEN},
                {CLOSE, CLOSE,  OPEN, CLOSE,  OPEN},
                { OPEN,  OPEN,  OPEN, CLOSE,  OPEN},
                { OPEN, CLOSE,  OPEN, CLOSE,  OPEN},
                { OPEN, CLOSE,  OPEN,  OPEN,  OPEN},
                { OPEN, CLOSE,  OPEN, CLOSE,  OPEN},
                { OPEN,  OPEN,  OPEN,  OPEN,  OPEN},
        };

        Grid grid1 = Grid.of(map1);
        System.out.println(grid1);
        System.out.println(grid1.path(Location.of(0,0), Location.of(3, 7)));

       Grid grid2 = Grid.of(map2);
       System.out.println(grid2);
       System.out.println(grid2.path(Location.of(7,0), Location.of(0, 0)));

    }

}
