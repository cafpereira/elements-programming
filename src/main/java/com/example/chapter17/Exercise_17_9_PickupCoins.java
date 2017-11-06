package com.example.chapter17;

import java.util.*;

class Exercise_17_9_PickupCoins {

  public static int maxCoinsRec(int[] coins) {
    return maxCoinsRec(coins, 0, coins.length - 1, true);
  }

  /**
   *
   if start > end:
     return 0

   int a = coin[start] + min( max_coin( coin, start+2,end ), max_coin( coin, start+1,end-1 ) )
   int b = coin[end] + min( max_coin( coin, start+1,end-1 ), max_coin( coin, start,end-2 ) )

   return max(a,b)
   */
  private static int maxCoinsRec(int[] coins, int start, int end, boolean player) {
    if (end - start == 1) {
      return player ? Math.max(coins[start], coins[end]) : Math.min(coins[start], coins[end]);
    }
    int left = maxCoinsRec(coins, start + 1, end, !player);
    int right = maxCoinsRec(coins, start, end - 1, !player);

    if (player) {
      int scoreLeft = left + coins[start];
      int scoreRight = right + coins[end];
      return Math.max(scoreLeft, scoreRight);
    } else {
      return Math.min(left, right);
    }
  }

  public static void main(String[] args) {
    System.out.println("maxCoinsRec(coins) = " + maxCoinsRec(new int[]{5, 25, 10, 1}));
  }
}