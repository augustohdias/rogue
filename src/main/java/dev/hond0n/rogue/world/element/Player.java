package dev.hond0n.rogue.world.element;

import dev.hond0n.rogue.algebra.Pair;
import dev.hond0n.rogue.world.element.trait.CanMove;

public class Player extends Body implements CanMove<Player> {

  public Player(Pair position) {
    super(position);
  }

  @Override
  public Player moveTo(Pair position) {
    return new Player(position);
  }
}
