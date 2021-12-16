package dev.hond0n.rogue.world.heuristic.genetic;

import dev.hond0n.rogue.algebra.Pair;
import dev.hond0n.rogue.world.element.Biome;
import dev.hond0n.rogue.world.element.Cell;
import dev.hond0n.rogue.world.heuristic.genetic.gambiarra.ANSI;
import dev.hond0n.rogue.world.heuristic.genetic.problem.ProblemSetup;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  private static final long BASE_SEED = System.currentTimeMillis();
  private static final Random RNG = new Random();

  private final ProblemSetup setup;
  private final List<Pair> availableCoordinates;

  public Solution(ProblemSetup setup) {
    this.setup = setup;
    this.availableCoordinates =
        IntStream.range(0, setup.getWidth())
            .mapToObj(x -> IntStream.range(0, setup.getHeight()).mapToObj(y -> new Pair(x, y)))
            .flatMap(Function.identity())
            .collect(Collectors.toCollection(LinkedList::new));
  }

  public List<Cell> solve() {
    List<Cell> cells = new ArrayList<>();
    System.out.println("Creating regions");
    Queue<Biome> q = new ArrayDeque<>(setup.getBiomeArea().keySet());
    q.remove(Biome.OCEAN);
    while (!q.isEmpty()) {
      Biome biome = q.remove();
      while (setup.getBiomeArea().get(biome) > 0) {
        List<Pair> region = createRegion(biome);
        System.out.println(biome.name() + " area: " + region.size());
        System.out.println("Adding new region to list");
        cells.addAll(
            region.stream()
                .map(p -> new Cell(Cell.CellState.EMPTY, biome, p))
                .collect(Collectors.toList()));
        System.out.println(biome.name() + " created.");
      }
    }

    if (availableCoordinates.size() > 0) {
      cells.addAll(
          availableCoordinates.stream()
              .map(p -> new Cell(Cell.CellState.EMPTY, Biome.OCEAN, p))
              .collect(Collectors.toList()));
    }

    System.out.println("Sorting");
    return cells.stream().sorted(compareCell()).collect(Collectors.toList());
  }

  public List<Pair> createRegion(Biome biome) {
    AtomicInteger failCount = new AtomicInteger();
    List<Pair> copy = new ArrayList<>(availableCoordinates);
    Pair origin = copy.get(RNG.nextInt(copy.size()));
    List<Pair> p =
        copy.stream()
            .filter(point -> regionFilter(origin, point, failCount, biome))
            .collect(Collectors.toList());
    System.out.println("done");
    return p;
  }

  private boolean regionFilter(Pair origin, Pair point, AtomicInteger failCount, Biome biome) {
    if (!rule(distance(origin, point), setup.getBiomeArea().get(biome))) {
      failCount.addAndGet(1);
    }
    long den = setup.getWidth();
    int area = setup.getWidth() * setup.getHeight();
    RNG.setSeed(BASE_SEED + cantorPairEnumeration(point.getX(), point.getY()));
    long toleranceRoll = RNG.nextInt(setup.getWidth());

    int tolerance =
        toleranceRoll <= failCount.get() / den ? (failCount.get() % Math.round(area * 0.1f)) : 0;

    if (setup.getBiomeArea().get(biome) > 0
        && rule(distance(origin, point) - tolerance, setup.getBiomeArea().get(biome))) {
      availableCoordinates.remove(point);
      setup.getBiomeArea().put(biome, setup.getBiomeArea().get(biome) - 1);
      return true;
    }
    return false;
  }

  public static double distance(Pair a, Pair b) {
    double sqr1 = Math.pow((b.getX() - a.getX()), 2);
    double sqr2 = Math.pow((b.getY() - a.getY()), 2);

    return Math.sqrt(sqr1 + sqr2);
  }

  public boolean rule(double distance, double biomeArea) {
    return distance < biomeArea/setup.getWidth();
  }

  public static long cantorPairEnumeration(long x, long y) {
    return ((x + y) * (x + y + 1) / 2) + y;
  }

  public void print(List<Cell> region) {
    List<Pair> toPrint =
        IntStream.range(0, setup.getWidth())
            .mapToObj(x -> IntStream.range(0, setup.getHeight()).mapToObj(y -> new Pair(x, y)))
            .flatMap(Function.identity())
            .sorted(comparePair().reversed())
            .collect(Collectors.toList());

    StringBuffer stringBuffer = new StringBuffer();
    for (Pair p : toPrint) {
      if ((setup.getWidth() - 1) == p.getX()) stringBuffer.append("\n");
      List<Pair> pos = region.stream().map(Cell::getCoord).collect(Collectors.toList());
      if (pos.contains(p)) {
        int i = region.indexOf(p);
        Cell cell = region.get(i);
        switch (cell.getBiome()) {
          case FOREST:
            stringBuffer.append(ANSI.ANSI_GREEN + "█" + ANSI.ANSI_RESET);
            break;
          case OCEAN:
            stringBuffer.append(ANSI.ANSI_BLUE + "▓" + ANSI.ANSI_RESET);
            break;
          case DESERT:
            stringBuffer.append(ANSI.ANSI_YELLOW + "▓" + ANSI.ANSI_RESET);
            break;
          case MANGROVE:
            stringBuffer.append(ANSI.ANSI_GREEN + "▒" + ANSI.ANSI_RESET);
            break;
          case SWAMP_FOREST:
            stringBuffer.append(ANSI.ANSI_GREEN + "▓" + ANSI.ANSI_RESET);
            break;
          case TROPICAL_FOREST:
            stringBuffer.append(ANSI.ANSI_GREEN + "░" + ANSI.ANSI_RESET);
            break;
        }
      } else stringBuffer.append("█");
    }
    stringBuffer.append("\n");
    System.out.println(stringBuffer);
  }

  private Comparator<Pair> comparePair() {
    return Comparator.comparingInt(a -> a.getX() + a.getY() * setup.getWidth());
  }

  private Comparator<Cell> compareCell() {
    return Comparator.comparingInt(
        c -> c.getCoord().getX() + c.getCoord().getY() * setup.getWidth());
  }

  private List<Cell> createChunks() {
    return Collections.emptyList();
  }
}
