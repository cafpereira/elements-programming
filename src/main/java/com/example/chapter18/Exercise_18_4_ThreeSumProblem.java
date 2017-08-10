package com.example.chapter18;

import java.util.*;

class ThreeSum {

  public static void main(String[] args) {
    int[] A = new int[]{11, 2, 5, 7, 3};
    int p = 21;
//    int[] A = new int[]{-1, 0, 1, 2, -1, -4};
//    int p = 0;
    System.out.println("threeSum("+ toS(A) + ", p="+ p + ") = " + toS(threeSum(A, p)));
  }

  public static int[] threeSum(int[] A, int p) {
    Arrays.sort(A);

    // Lets search for i,j,k such as:
    // A[i] + A[j] + A[k] = p
    for (int i = 0; i < A.length - 2; i++) {
      // For j and k we can assume that:
      // A[j] + A[k] = p - A[i]
      int diff = p - A[i];
      // Since A is sorted we can find two values betwen A[i..n]
      // where A[j] + A[k] = diff, in O(n) time
      int j = i + 1;
      int k = A.length - 1;
      while (j < k) {
        int curSum = A[j] + A[k];
        if (curSum == diff) {
          return new int[]{A[i], A[j], A[k]}; // Solution found
        } else if (curSum < diff) {
          j++;
        } else { // curSum > diff
          k--;
        }
      }
    }
    // Triplet values not found
    return new int[]{-1,-1,-1};
  }

  public static String toS(int[] array){
    return Arrays.toString(array);
  }
}
