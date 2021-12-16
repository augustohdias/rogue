package dev.hond0n.rogue.action;

import dev.hond0n.rogue.algebra.Pair;
import dev.hond0n.rogue.world.element.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MoveTest {

  @Test
  void run() {
    Move<Player> moveUp = new Move<>(Direction.UP);
    Move<Player> moveLeft = new Move<>(Direction.LEFT);
    Move<Player> moveRight = new Move<>(Direction.RIGHT);
    Move<Player> moveDown = new Move<>(Direction.DOWN);

    Player dummyPlayer = new Player(new Pair(0, 0));

    Player resultUp = moveUp.run(dummyPlayer);
    Player resultLeft = moveLeft.run(dummyPlayer);
    Player resultRight = moveRight.run(dummyPlayer);
    Player resultDown = moveDown.run(dummyPlayer);

    Assertions.assertEquals(dummyPlayer.getX(), resultUp.getX());
    Assertions.assertEquals(dummyPlayer.getY() + 1, resultUp.getY());

    Assertions.assertEquals(dummyPlayer.getX(), resultDown.getX());
    Assertions.assertEquals(dummyPlayer.getY() - 1, resultDown.getY());

    Assertions.assertEquals(dummyPlayer.getY(), resultLeft.getY());
    Assertions.assertEquals(dummyPlayer.getX() - 1, resultLeft.getX());

    Assertions.assertEquals(dummyPlayer.getY(), resultRight.getY());
    Assertions.assertEquals(dummyPlayer.getX() + 1, resultRight.getX());
  }
}
