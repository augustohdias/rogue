package dev.hond0n.rogue.action;

public enum Direction {
    UP(0, 1), DOWN(0, -1), RIGHT(1, 0), LEFT(-1, 0), STEADY(0, 0)
    ;

    public final int x;
    public final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
