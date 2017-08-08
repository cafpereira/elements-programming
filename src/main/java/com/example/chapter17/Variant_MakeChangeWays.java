package com.example.chapter17;

import java.util.HashMap;

class ChangeWays {

    public static void main(String[] args) {
        int[] coins = new int[]{25, 10, 5, 1};
        System.out.println("makeChangeWays('" + 27 + "') = " + makeChangeWays(coins, 27));
    }

    public static long makeChangeWays(int[] coins, int money) {
        return changeWays(coins, money, 0, new HashMap<>());
    }

    public static long changeWays(int[] coins, int money, int index, HashMap<String, Long> memo) {
        if (money == 0) {
            return 1;
        }
        if (index >= coins.length) {
            return 0;
        }
        String key = money + "-" + index;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int amountWithCoin = 0;
        long ways = 0;
        while (amountWithCoin <= money) {
            int remaining = money - amountWithCoin;
            ways += changeWays(coins, remaining, index + 1, memo);
            amountWithCoin += coins[index];
        }
        memo.put(key, ways);
        return ways;
    }
}
