package com.example.chapter17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

class StringInMatrix {

  private static class Attempt {
    public Integer x;
    public Integer y;
    public Integer offset;

    public Attempt(Integer x, Integer y, Integer offset) {
      this.x = x;
      this.y = y;
      this.offset = offset;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Attempt cacheEntry = (Attempt)o;

      if (x != null ? !x.equals(cacheEntry.x) : cacheEntry.x != null) {
        return false;
      }
      if (y != null ? !y.equals(cacheEntry.y) : cacheEntry.y != null) {
        return false;
      }
      if (offset != null ? !offset.equals(cacheEntry.offset)
          : cacheEntry.offset != null) {
        return false;
      }

      return true;
    }

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(x, y, offset); }
    // clang-format on
  }

  // @include
  public static boolean isPatternContainedInGrid(List<List<Integer>> grid,
                                                 List<Integer> pattern) {
    // Each entry in previousAttempts is a point in the grid and suffix of
    // pattern (identified by its offset). Presence in previousAttempts
    // indicates the suffix is not contained in the grid starting from that
    // point.
    Set<Attempt> previousAttempts = new HashSet<>();
    for (int i = 0; i < grid.size(); ++i) {
      for (int j = 0; j < grid.get(i).size(); ++j) {
        if (isPatternSuffixContainedStartingAtXY(grid, i, j, pattern, 0,
            previousAttempts)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean isPatternSuffixContainedStartingAtXY(
      List<List<Integer>> grid, int x, int y, List<Integer> pattern, int offset,
      Set<Attempt> previousAttempts) {
    if (pattern.size() == offset) {
      // Nothing left to complete.
      return true;
    }
    // Check if (x, y) lies outside the grid.
    if (x < 0 || x >= grid.size() || y < 0 || y >= grid.get(x).size()
        || previousAttempts.contains(new Attempt(x, y, offset))
        || !grid.get(x).get(y).equals(pattern.get(offset))) {
      return false;
    }

    if (isPatternSuffixContainedStartingAtXY(grid, x - 1, y, pattern,
        offset + 1, previousAttempts)
        || isPatternSuffixContainedStartingAtXY(grid, x + 1, y, pattern,
        offset + 1, previousAttempts)
        || isPatternSuffixContainedStartingAtXY(grid, x, y - 1, pattern,
        offset + 1, previousAttempts)
        || isPatternSuffixContainedStartingAtXY(grid, x, y + 1, pattern,
        offset + 1, previousAttempts)) {
      return true;
    }
    previousAttempts.add(new Attempt(x, y, offset));
    return false;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(9) + 2;
    }
//    List<List<Integer>> A = randMatrix(n);
    List<List<Integer>> A = sampleMatrix();
    System.out.println("A = " + A);
    int size = r.nextInt(n * n / 2) + 1;
//    List<Integer> S = new ArrayList<>();
//    for (int i = 0; i < size; ++i) {
//      S.add(r.nextInt(n));
//    }
//    System.out.println("S = " + S + ", isPatternContainedInGrid: " + isPatternContainedInGrid(A, S));

    List<Integer> S1 = Arrays.asList(1, 3, 4, 6);
    System.out.println("S1 = " + S1 + ", isPatternContainedInGrid: " + isPatternContainedInGrid(A, S1));

    List<Integer> S2 = Arrays.asList(1, 2, 3, 4);
    System.out.println("S2 = " + S2 + ", isPatternContainedInGrid: " + isPatternContainedInGrid(A, S2));
  }

  private static List<List<Integer>> randMatrix(int n) {
    Random r = new Random();
    List<List<Integer>> matrix = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      matrix.add(new ArrayList<Integer>(n));
      for (int j = 0; j < n; ++j) {
        matrix.get(i).add(r.nextInt(n));
      }
    }
    return matrix;
  }

  private static List<List<Integer>> sampleMatrix() {
    List<List<Integer>> matrix = new ArrayList<>();
    matrix.add(Arrays.asList(1, 2, 3));
    matrix.add(Arrays.asList(3, 4, 5));
    matrix.add(Arrays.asList(5, 6, 7));
    return matrix;
  }
}