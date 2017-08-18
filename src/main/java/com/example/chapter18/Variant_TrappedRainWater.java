package com.example.chapter18;

import java.io.*;
import java.util.*;

class RainWater {
    public static void main(String[] args) {
        int[] towers = new int[]{1,5,2,3,1,7,2};
        System.out.println("maxRainWater(" + Arrays.toString(towers) + ") = " + maxRainWater(towers));
    }

    public static int maxRainWater(int[] towers) {
        int n = towers.length;
        int[] maxHeightLeft = new int[n];
        Arrays.fill(maxHeightLeft, -1);

        int[] maxHeightRight = new int[n];
        Arrays.fill(maxHeightRight, -1);

        int maxLeft = towers[0];
        for (int i = 1; i < n; i++) {
            maxHeightLeft[i] = maxLeft;
            maxLeft = Math.max(maxLeft, towers[i]);
        }

        int maxRight = towers[n-1];
        for (int i = n - 2; i >= 0; i--) {
            maxHeightRight[i] = maxRight;
            maxRight = Math.max(maxRight, towers[i]);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            int minHeight = Math.min(maxHeightLeft[i], maxHeightRight[i]);
            if (minHeight > towers[i]) {
                res += minHeight - towers[i];
            }
        }
        return res;
    }
}
