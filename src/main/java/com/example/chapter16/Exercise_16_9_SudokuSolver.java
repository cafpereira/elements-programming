package com.example.chapter16;

import java.util.ArrayList;
import java.util.List;

class SudokuSolver {

  public static int EMPTY_ENTRY = 0;

  public boolean genarateSolution(int[][] board) {
    return solvePuzzle(board, board[0].length, 0, 0);
  }

  public boolean solvePuzzle(int[][] board, int n, int row, int col) {
    // Either advance to next entry or return solution.
    // Entries are scanned by row first and collumn (top-down)
    if (row == n) {
      row = 0; // Start new row
      if (++col == n) {
        // All entries filled, solution found.
        return true;
      }
    }

    // Skip non-empty entries and advance to next row
    if (board[row][col] != EMPTY_ENTRY) {
      return solvePuzzle(board, n, row + 1, col);
    }

    List<Integer> candidates = getValidEntries(board, n, row, col);
    for (Integer cand : candidates) {
      board[row][col] = cand;
      if (solvePuzzle(board, n, row + 1, col)) {
        return true;
      }
    }

    // Backtrack
    board[row][col] = EMPTY_ENTRY;
    return false;
  }

  List<Integer> getValidEntries(int[][] board, int n, int row, int col) {
    boolean[] used = new boolean[n + 1];

    // Check vertical entries
    for (int i = 0; i < n; i++) {
      if (board[i][col] != EMPTY_ENTRY) {
        int val = board[i][col];
        used[val] = true;
      }
    }
    // Check horizontal entries
    for (int j = 0; j < n; j++) {
      if (board[row][j] != EMPTY_ENTRY) {
        int val = board[row][j];
        used[val] = true;
      }
    }

    // Check sub-matrix entries
    int regionSize = (int) Math.sqrt(n);
    int regionRow = ((row / regionSize) * regionSize);
    int regionCol = ((col / regionSize) * regionSize);
    for (int i = regionRow; i < regionRow + regionSize ; i++) {
      for (int j = regionCol; j < regionCol + regionSize; j++) {
        if (board[i][j] != EMPTY_ENTRY) {
          int val = board[i][j];
          used[val] = true;
        }
      }
    }

    List<Integer> validEntries = new ArrayList<>();
    for (int k = 1; k <= n; k++) {
      if (!used[k]) {
        validEntries.add(k);
      }
    }
    return validEntries;
  }

  public static void print(int[][] board) {
    System.out.println("**********************");
    for (int p = 0; p < board.length; p++) {
      for (int q = 0; q < board[0].length; q++) {
        System.out.print(board[p][q] + ", ");
      }
      System.out.println("");
    }
  }
}

