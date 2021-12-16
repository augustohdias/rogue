package dev.hond0n.rogue.action;

import dev.hond0n.rogue.algebra.Pair;
import dev.hond0n.rogue.world.element.Body;
import dev.hond0n.rogue.world.element.trait.CanMove;

public class Move<T extends Body & CanMove<T>> implements Action<T> {
  private final Direction direction;

  public Move(Direction direction) {
    this.direction = direction;
  }

  @Override
  public T run(T actor) {
    Pair position = actor.getPosition();
    Pair newPosition = new Pair(position.getX() + direction.x, position.getY() + direction.y);
    return actor.moveTo(newPosition);
  }
}
