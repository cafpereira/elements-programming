package com.example.chapter18;

import java.util.Arrays;

class RainWaterV2 {
    public static void main(String[] args) {
        int[] towers = new int[]{1,5,2,3,1,7,2};
        System.out.println("maxRainWater(" + Arrays.toString(towers) + ") = " + maxRainWater(towers));
    }

    public static int maxRainWater(int[] towers) {
        int n = towers.length;
        int[] maxHeightLeft = new int[n];
        Arrays.fill(maxHeightLeft, -1);

        int maxLeft = towers[0];
        for (int i = 1; i < n; i++) {
            maxHeightLeft[i] = maxLeft;
            maxLeft = Math.max(maxLeft, towers[i]);
        }

        int res = 0;
        int maxRight = towers[n-1];
        for (int i = n - 2; i > 0; i--) {
            int minHeight = Math.min(maxHeightLeft[i], maxRight);
            if (minHeight > towers[i]) {
                res += minHeight - towers[i];
            }
            maxRight = Math.max(maxRight, towers[i]);
        }
        return res;
    }
}
