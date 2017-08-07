package com.example.chapter17;

import java.util.Arrays;

class PalindromePartitionV2 {

  public static void main(String[] args) {
    String input = "abcbm";
    System.out.println("Minimum Palindrome Splits: '" + input + "': " + minSplits(input));
  }

  /*
   * Given a string s, partition s such that every substring of the partition is a palindrome.
   * Return the minimum cuts needed for a palindrome partitioning of s.
   */
  public static int minSplits(String str) {
    int n = str.length();
    // Lookup table that is used to check if a given prefix s[i:j]
    // is palindrome or not in O(1) time
    boolean[][] isPalin = new boolean[n][n];

    // minCuts[i] contains the minimum cuts needed to partition
    // the prefix s[0:i] as a list of palindromes
    int[] minCuts = new int[n];
    Arrays.fill(minCuts, Integer.MAX_VALUE);

    for (int len = 0; len < n; len++) {
      for (int i = 0; i < n - len; i++) {
        int j = i + len;
        if (str.charAt(i) == str.charAt(j)) {
          isPalin[i][j] = (len <= 1) || (isPalin[i+1][j-1]);
        }
      }
      if (isPalin[0][len]) {
        minCuts[len] = 0;
      } else {
        for (int split = 0; split < len; split++) {
          if (isPalin[split+1][len]) {
            minCuts[len] = Math.min(minCuts[len], minCuts[split] + 1);
          }
        }
      }
    }
    return minCuts[n-1];
  }
}
