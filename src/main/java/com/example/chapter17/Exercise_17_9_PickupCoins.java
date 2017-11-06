package com.example.chapter17;

import java.util.*;

class Exercise_17_9_PickupCoins {

  public static int maxCoinsRec(int[] coins) {
    return maxCoinsRec(coins, 0, coins.length - 1);
  }

  public static int maxCoinsRec(int[] coins, int start, int end) {
    // Base-case
    if (start > end) {
      return 0;
    }

    int left = coins[start] + // If player choose start, he will immediately gain coin[start]
        Math.min( // Next player now has to play between start + 1 and end.
            // He plays to maximize his gain. However since the number of coin is fixed, this amounts to minimize mine
            maxCoinsRec(coins, start + 2, end), // if he plays 'start+1' I'll gain maxCoins(coin, start+2, end)
            maxCoinsRec(coins, start + 1, end - 1) // if he plays 'end' Ill gain maxCoins(coin, start+1, end-1)
        );
    // Same reasoning apply to the line bellow where we play end
    int right = coins[end] + Math.min(maxCoinsRec(coins, start + 1, end - 1), maxCoinsRec(coins, start, end - 2));

    // Left and right vars represent respectively the maximum gain if 'start' or 'end' coins are played.
    return Math.max(left, right);
  }

  public static int maxCoinsMemo(int[] coins) {
    int n = coins.length;
    int[][] memo = new int[n][n];
    return maxCoinsMemo(coins, 0, coins.length - 1, memo);
  }

  public static int maxCoinsMemo(int[] coins, int start, int end, int[][] memo) {
    if (start > end) {
      return 0;
    }
    if (memo[start][end] > 0) {
      return memo[start][end];
    }

    // If player 1 choose left coin, player 2 can pick start + 1 or end
    int left = coins[start] + Math.min(maxCoinsMemo(coins, start + 2, end, memo),
        maxCoinsMemo(coins, start + 1, end - 1, memo));

    // If player 1 choose right coin, player 2 can pick start or end - 1
    int right = coins[end] + Math.min(maxCoinsMemo(coins, start + 1, end - 1, memo),
        maxCoinsMemo(coins, start, end - 2, memo));

    memo[start][end] = Math.max(left, right);
    return memo[start][end];
  }

  public static void main(String[] args) {
    System.out.println("maxCoinsRec(coins) = " + maxCoinsRec(new int[]{5, 25, 10, 1}) + " # Expected: 26");
    System.out.println("maxCoinsMemo(coins) = " + maxCoinsMemo(new int[]{5, 25, 10, 1}) + " # Expected: 26");
  }
}