package com.example.chapter17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 Variant: Palindromic decompositions were described in Problem 16.7 on Page 293.
 Observe every string s has at least one palindromic decomposition, which is the trivial
 one consisting of the individual characters. For example, if s is "0204451881" then
 "0", "2",“0”, "4", "4", "5", "1", "8", "8", "1" is such a trivial decomposition. The
 minimum decomposition of s is "020", "44", "5", "1881". How would you compute a
 palindromic decomposition of a string s that uses a minimum number of substrings?
 */

class PalindromePartitionV3 {
  public static void main(String[] args) {
//    String input = "abcbm";
    String input = "0204451881";
    System.out.println("Minimum Palindrome Partition: '" + input + "': " + minPartition(input));
  }

  public static List<String> minPartition(String str) {
    int n = str.length();
    // Lookup table that is used to check if a given prefix s[i:j]
    // is palindrome or not in O(1) time
    boolean[][] isPalin = new boolean[n][n];

    // minCuts[i] contains the minimum cuts needed to partition
    // the prefix s[0:i] as a list of palindromes
    int[] minCuts = new int[n];
    Arrays.fill(minCuts, Integer.MAX_VALUE);

    // splitIdx[i] = k contains the start index of the palindrome
    // used on the minimum split on minCuts[i]
    int[] splitIdx = new int[n];

    for (int len = 0; len < n; len++) {
      for (int i = 0; i < n - len; i++) {
        int j = i + len;
        if (str.charAt(i) == str.charAt(j)) {
          isPalin[i][j] = (len <= 1) || (isPalin[i+1][j-1]);
        }
      }
      if (isPalin[0][len]) {
        minCuts[len] = 0;
        splitIdx[len] = 0;
      } else {
        for (int split = 0; split < len; split++) {
          if (isPalin[split + 1][len]) {
            int cuts = minCuts[split] + 1;
            if (cuts < minCuts[len]) {
              minCuts[len] = cuts;
              splitIdx[len] = split + 1;
            }
          }
        }
      }
    }
    return buildResult(str, splitIdx);
  }

  public static List<String> buildResult(String str, int[] splitIdx) {
    List<String> result = new ArrayList<>();
    int end = splitIdx.length;
    int start;
    do {
      start = splitIdx[end - 1];
      result.add(str.substring(start, end));
      end = start;
      start--;
    }
    while (start >= 0);
    Collections.reverse(result);
    return result;
  }
}
