package dev.hond0n.rogue.world.element.trait;

import dev.hond0n.rogue.algebra.Pair;

public interface CanMove<T> extends Trait<T> {
    T moveTo(Pair position);
}
