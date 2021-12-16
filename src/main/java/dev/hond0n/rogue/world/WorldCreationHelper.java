package dev.hond0n.rogue.world;

import dev.hond0n.rogue.algebra.Pair;
import dev.hond0n.rogue.world.element.Biome;
import dev.hond0n.rogue.world.element.Body;
import dev.hond0n.rogue.world.element.Cell;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class WorldCreationHelper {
  private static final Random RNG = new Random(System.currentTimeMillis());

  public static Map<Pair, Cell> createBodies(Integer amount, Integer rowSize) {
    return Collections.emptyMap();
  }

  private static Optional<Body> bodyHeuristics(Integer index, Integer rowSize) {
    double roll = Math.abs(RNG.nextDouble()) % 1;
    if (roll <= 0.3) {
      return Optional.of(new Body(new Pair(index % rowSize, Math.floorDiv(index, rowSize))));
    }
    return Optional.empty();
  }

  private static Cell cellHeuristics(Integer index, Integer rowSize) {
    return new Cell(Cell.CellState.EMPTY, Biome.FOREST, new Pair(0, 0));
  }
}
