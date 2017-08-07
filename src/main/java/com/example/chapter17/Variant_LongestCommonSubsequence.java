package com.example.chapter17;

class LCS {

  public static void main(String[] args) {
    String s1 = "welcometocoderpad";
    String s2 = "thispadisrunningjava";

    System.out.println("s1: " + s1 + "s2: " + s2);
    System.out.println("LCS: " + longestCommomSubsequence(s1, s2));
  }

  public static int longestCommomSubsequence(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();

    int[][] memo = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        int up = i > 0 ? memo[i - 1][j] : 0;
        int left = j > 0 ? memo[i][j - 1] : 0;
        int diagon = (i > 0 && j > 0) ? memo[i - 1][j - 1] : 0;
        if (s1.charAt(i) == s2.charAt(j)) {
          memo[i][j] = diagon + 1;
        } else {
          memo[i][j] = Math.max(up, left);
        }
      }
    }
    return memo[n - 1][m - 1];
  }
}
