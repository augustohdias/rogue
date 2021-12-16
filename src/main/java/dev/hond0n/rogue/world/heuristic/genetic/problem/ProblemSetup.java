package dev.hond0n.rogue.world.heuristic.genetic.problem;

import dev.hond0n.rogue.world.element.Biome;

import java.util.Map;

public class ProblemSetup {
  private final int width;
  private final int height;
  private final Map<Biome, Integer> biomeQuota;

  public ProblemSetup(int width, int height, Map<Biome, Integer> biomeProbability) {
    this.width = width;
    this.height = height;
    this.biomeQuota = biomeProbability;
  }

  public int getHeight() {
    return height;
  }

  public Map<Biome, Integer> getBiomeArea() {
    return biomeQuota;
  }

  public int getWidth() {
    return width;
  }

}
