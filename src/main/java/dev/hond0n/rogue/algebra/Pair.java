package dev.hond0n.rogue.algebra;

import dev.hond0n.rogue.world.element.Cell;

import java.util.Objects;

public class Pair {
  private final int x;
  private final int y;

  public Pair(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Cell) {
      Pair pair = ((Cell) o).getCoord();
      return equals(pair);
    }
    if (o == null || getClass() != o.getClass()) return false;
    Pair pair = (Pair) o;
    return Objects.equals(x, pair.x) && Objects.equals(y, pair.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
