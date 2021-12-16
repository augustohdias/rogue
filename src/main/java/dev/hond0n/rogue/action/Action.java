package dev.hond0n.rogue.action;


import dev.hond0n.rogue.world.element.Body;

public interface Action<T extends Body> {
  T run(T actor);
}
