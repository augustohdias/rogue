package dev.hond0n.rogue.world.element;

import dev.hond0n.rogue.algebra.Pair;

public class Body {
  protected int x;
  protected int y;

  public Body(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Body(Pair position) {
    this.x = position.getX();
    this.y = position.getY();
  }

  public Pair getPosition() {
    return new Pair(x, y);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
