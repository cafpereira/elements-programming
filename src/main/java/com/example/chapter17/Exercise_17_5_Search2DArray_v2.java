package com.example.chapter17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

class StringInMatrixV2 {

  private static class Attempt {
    public int x;
    public int y;
    public int offset;

    public Attempt(int x, int y, int offset) {
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

      Attempt other = (Attempt) o;

      return this.x == other.x
          && this.y == other.y
          && this.offset == other.offset;
    }

    @Override
    public int hashCode() { return Objects.hash(x, y, offset); }
  }

  public static boolean isPatternContainedInGrid(List<List<Integer>> grid, List<Integer> query) {
    // Each entry in 'visited' is a point in the grid and suffix of the query
    // (identified by its offset). Presence in visited set indicates that the
    // suffix is not contained in the grid starting from that point.
    Set<Attempt> visited = new HashSet<>();
    for (int i = 0; i < grid.size(); ++i) {
      for (int j = 0; j < grid.get(i).size(); ++j) {
        if (isPatternSuffixContainedStartingAtXY(grid, i, j, query, 0, visited)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean isPatternSuffixContainedStartingAtXY( List<List<Integer>> grid, int x, int y, List<Integer> query, int offset,
                                                               Set<Attempt> visited) {
    if (query.size() == offset) {
      // Nothing left to complete.
      return true;
    }
    // Check if coordinates (x, y) lies outside the grid.
    if (x < 0 || x >= grid.size() || y < 0 || y >= grid.get(x).size()
        || visited.contains(new Attempt(x, y, offset))) {
      return false;
    }

    if (grid.get(x).get(y).equals(query.get(offset))) {
      if (isPatternSuffixContainedStartingAtXY(grid, x - 1, y, query, offset + 1, visited)
          || isPatternSuffixContainedStartingAtXY(grid, x + 1, y, query,offset + 1, visited)
          || isPatternSuffixContainedStartingAtXY(grid, x , y - 1, query,offset + 1, visited)
          || isPatternSuffixContainedStartingAtXY(grid, x , y + 1, query,offset + 1, visited)) {
        return true;
      }
    }
    // Query not found, mark as visited
    visited.add(new Attempt(x, y, offset));
    return false;
  }

  public static void main(String[] args) {
    List<List<Integer>> grid =  sampleMatrix();
    List<Integer> pattern = Arrays.asList(1, 3, 4, 5, 3, 2); // cointained = true
    System.out.println("Pattern = " + pattern + ", isPatternContainedInGrid: " + isPatternContainedInGrid(grid, pattern));

    pattern = Arrays.asList(1, 3, 4, 5, 3, 7);               // cointained = false
    System.out.println("Pattern = " + pattern + ", isPatternContainedInGrid: " + isPatternContainedInGrid(grid, pattern));
  }

  private static List<List<Integer>> sampleMatrix() {
    List<List<Integer>> matrix = new ArrayList<>();
    matrix.add(Arrays.asList(1, 2, 3));
    matrix.add(Arrays.asList(3, 4, 5));
    matrix.add(Arrays.asList(5, 6, 7));
    return matrix;
  }
}