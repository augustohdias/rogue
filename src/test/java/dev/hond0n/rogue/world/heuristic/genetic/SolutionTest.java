package dev.hond0n.rogue.world.heuristic.genetic;

import dev.hond0n.rogue.world.element.Biome;
import dev.hond0n.rogue.world.element.Cell;
import dev.hond0n.rogue.world.heuristic.genetic.problem.ProblemSetup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lwjgl.system.CallbackI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SolutionTest {

  @Test
  void solve() {
    int w = 300;
    int h = 100;
    Map<Biome, Integer> biome = new HashMap<>();
    int forestArea = Math.round(w * h * 0.13f);
    int swampArea = Math.round(w * h * 0.19f);
    int desertArea = Math.round(w * h * 0.20f);
    int mangroveArea = Math.round(w * h * 0.12f);
    int tropicalArea = Math.round(w * h * 0.16f);
    int oceanArea = Math.round(w * h * 0.1f);

    biome.put(Biome.FOREST, forestArea);
    biome.put(Biome.SWAMP_FOREST, swampArea);
    biome.put(Biome.DESERT, desertArea);
    biome.put(Biome.MANGROVE, mangroveArea);
    biome.put(Biome.TROPICAL_FOREST, tropicalArea);
    biome.put(Biome.OCEAN, oceanArea);

    Solution s = new Solution(new ProblemSetup(w, h, biome));
    long start = System.currentTimeMillis();
    List<Cell> cells = s.solve();
    long finish = System.currentTimeMillis();
    System.out.println(finish - start + " ms");

    Assertions.assertTrue(
        forestArea >= cells.stream().filter(c -> Biome.FOREST.equals(c.getBiome())).count());
    Assertions.assertTrue(
        swampArea >= cells.stream().filter(c -> Biome.SWAMP_FOREST.equals(c.getBiome())).count());
    Assertions.assertTrue(
        desertArea >= cells.stream().filter(c -> Biome.DESERT.equals(c.getBiome())).count());
    Assertions.assertTrue(
        mangroveArea >= cells.stream().filter(c -> Biome.MANGROVE.equals(c.getBiome())).count());
    Assertions.assertTrue(
        tropicalArea
            >= cells.stream().filter(c -> Biome.TROPICAL_FOREST.equals(c.getBiome())).count());

    s.print(cells);
  }
}
