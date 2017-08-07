package com.example.chapter17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class PalindromePartition {

  public static void main(String[] args) {
    // String input = "0204451881";
    String input = "abcbm";
    System.out.println("Minimum Palindromic Decomposition: '" + input + "' ");
    for (String s : splitPalindromes(input)) {
      System.out.print(s + ", ");
    }
  }

  public static List<String> splitPalindromes(String str) {
    // Lookup table that is used to check if a given prefix s[i:j]
    // is palindrome or not in O(1) time
    boolean[][] isPalin = initPalinTable(str);

    // palinLength[j] contains the length of palindrome s[i:j], such as:
    // 0 <= i < j and s[0:j] is the optimal palindromic decomposition
    // otherwise, s[i] == -1
    int[] palinLength = new int[str.length()];
    Arrays.fill(palinLength, -1);

    // Memoization table that stores the optimal number of splits
    // for all prefixes s[i,j];
    int[][] memo = new int[str.length()][str.length()];

    for (int len = 0; len < str.length(); len++) {
      for (int i = 0; i < str.length() - len; i++) {
        int j = i + len;
        if (isPalin[i][j]) {
          memo[i][j] = 0;
          palinLength[j] = j - i + 1;
        } else {
          int minSplits = Integer.MAX_VALUE;
          for (int split = i; split < j; split++) {
            if (isPalin[i][split]) {
              int curSplits = memo[i][split] + memo[split+1][j] + 1;
              if (curSplits < minSplits) {
                minSplits = curSplits;
                memo[i][j] = minSplits;
                palinLength[i] = split - i;
              }
            }
          }
        }
      }
    }
    // Extract mimimum palindrome list from length array
    return buildMinPalinList(str, palinLength);
  }


  public static boolean[][] initPalinTable(String str) {
    boolean[][] table = new boolean[str.length()][str.length()];

    for (int len = 0; len < str.length(); len++) {
      for (int i = 0; i < str.length() - len; i++) {
        int j = i + len;
        if (str.charAt(i) == str.charAt(j)) {
          table[i][j] = (len <= 1 || table[i+1][j-1]);
        }
      }

    }
    return table;
  }

  public static List<String> buildMinPalinList(String str, int[] palinLength) {
    List<String> result = new ArrayList<>();
    int i = palinLength.length;
    while (i >= 0) {
      int len = palinLength[i];
      result.add(str.substring(i - len, i));
      i -= len;
    }
    Collections.reverse(result);
    return result;
  }
}
