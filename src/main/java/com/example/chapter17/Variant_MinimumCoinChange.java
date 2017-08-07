package com.example.chapter17;

import java.io.*;
import java.util.*;

class MakeChange {

  public static void main(String[] args) {
    int[] denominators = new int[]{1,3,6,12,24,30};
    int V = 48;
    System.out.println("makeChange('" + V + "') = " + makeChange(V, denominators));
  }

  /**
   * Make change using the smallest number of coins.
   */
  public static List<Integer> makeChange(int V, int[] denominators) {
    int n = denominators.length;
    int[][] memo = new int[n][V + 1];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j <= V; j++) {
        if (j == 0) {
          memo[i][j] = 0;
        } else {
          int withoutCoin = i > 0 ? memo[i - 1][j] : j;
          int withCoin = j >= denominators[i] ? memo[i][j - denominators[i]] + 1 : withoutCoin;
          memo[i][j] = Math.min(withoutCoin, withCoin);
        }
      }
    }
    return buildCoinList(V, denominators, memo);
  }

  public static List<Integer> buildCoinList(int V, int[] denominators, int[][] memo) {
    int i = denominators.length - 1;
    int j = V;

    List<Integer> res = new ArrayList<>();
    while (j > 0) {
      if (i == 0 || memo[i - 1][j] != memo[i][j]) {
        res.add(denominators[i]);
        j -= denominators[i];
      } else {
        i--;
      }
    }

    Collections.reverse(res);
    return res;
  }

  public static void prettyPrint(int[][] table) {
    for (int[] row : table) {
      System.out.println(Arrays.toString(row));
    }
  }
}
