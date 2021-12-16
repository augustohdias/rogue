package dev.hond0n.rogue.action;

import dev.hond0n.rogue.world.element.Body;

public class Attack<T extends Body> implements Action<T> {
  @Override
  public T run(Body actor) {
    System.out.println("ATTACK");
    return null;
  }
}
