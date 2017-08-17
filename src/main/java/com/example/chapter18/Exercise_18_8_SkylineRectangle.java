package com.example.chapter18;

import java.io.*;
import java.util.*;

class SkylineRectangle {
  public static void main(String[] args) {
    int[] buildings = new int[]{2,1,2,3,1};
    System.out.println("largestRectangle() = " + largestRectangle(buildings));
  }

  public static int largestRectangle(int[] buildings) {
    int max = 0;
    int n = buildings.length;
    Deque<Integer> stack = new LinkedList<Integer>();

    for (int i = 0; i <= n; i++) {
      while(!stack.isEmpty() && hasLargerBuildOrReachEnd(buildings, i, stack.peek())) {
        int h = buildings[stack.pop()];
        int w = stack.isEmpty() ? i : i - stack.peek() - 1;
        max = Math.max(h * w, max);
      }
      stack.push(i);
    }
    return max;
  }

  public static boolean hasLargerBuildOrReachEnd(int[] buildings, int cur, int top) {
    return cur < buildings.length ? buildings[cur] < buildings[top] : true;
  }
}