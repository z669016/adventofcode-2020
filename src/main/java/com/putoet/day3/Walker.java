package com.putoet.day3;


import com.putoet.grid.Point;

public class Walker {
    public int walkAndCountTrees(Point slope, TreeArea treeArea) {
        Point position = Point.ORIGIN;
        int trees = 0;

        while (position.y < treeArea.height()) {
            if (treeArea.treeAt(position))
                trees++;

            position = position.add(slope);
        }

        return trees;
    }
}
