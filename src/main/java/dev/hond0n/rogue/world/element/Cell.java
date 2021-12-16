package dev.hond0n.rogue.world.element;

import dev.hond0n.rogue.algebra.Pair;

import java.util.Objects;

public class Cell {
  public enum CellState {
    EMPTY,
    SOLID,
    ;
  }

  private final CellState state;
  private final Biome biome;
  private final Pair coord;

  public Cell(CellState state, Biome biome, Pair coord) {
    this.state = state;
    this.biome = biome;
    this.coord = coord;
  }

  public Biome getBiome() {
    return biome;
  }

  public CellState getState() {
    return state;
  }

  public Pair getCoord() {
    return coord;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Pair) {
      Pair p = ((Pair) o);
      return coord.equals(p);
    }
    if (o == null || getClass() != o.getClass()) return false;
    Cell cell = (Cell) o;
    return coord.equals(cell.coord);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coord);
  }
}
