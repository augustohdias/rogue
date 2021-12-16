package dev.hond0n.rogue.world;

import dev.hond0n.rogue.action.Action;
import dev.hond0n.rogue.algebra.Pair;
import dev.hond0n.rogue.world.element.Cell;
import dev.hond0n.rogue.world.element.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Environment {
  Map<Pair, Cell> cells;
  Player player;

  public Environment(Map<Pair, Cell> cells) {
    this.player = new Player(new Pair(0, 0));
    this.cells = cells;
  }

  public Transition getStaticTransition() {
    return new Transition(cells, cells);
  }

  private List<Cell> getSurroundings(Integer x, Integer y) {
    List<Pair> query =
        Arrays.asList(
            new Pair(x + 1, y),
            new Pair(x - 1, y),
            new Pair(x, y + 1),
            new Pair(x, y - 1),
            new Pair(x + 1, y + 1),
            new Pair(x - 1, y - 1),
            new Pair(x + 1, y - 1),
            new Pair(x - 1, y + 1));

    return query.stream().map(cells::get).filter(Objects::nonNull).collect(Collectors.toList());
  }

  public Transition iterate(Action<Player> playerAction) {
    Player player /*Effect effect*/ = playerAction.run(this.player /*, bodies*/);
    /*applyEffect(effect);*/
    return new Transition(Collections.emptyMap(), /* new state */ Collections.emptyMap());
  }
}
