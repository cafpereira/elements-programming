package com.example.chapter6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Variant question 6.4 Advancing Through an Array
 *
 * Each element in the array represents your maximum jump length at that position.
 * Compute the minimum number of steps needed to advance to the last location.
 */
class Variant_AdvancingThroughArray {

    /**
     * Recursive implementation
     * Complexity: O(n!)
     */
    public static int minJumpsRec(int[] A, int index) {
        if (index >= A.length - 1) {
            return 0;
        }
        int jumps = Integer.MAX_VALUE;
        for (int i = 1; i <= A[index]; i++) {
            jumps = Math.min(jumps, 1 + minJumpsRec(A, index + i));
        }
        return jumps;
    }

    /**
     * Dynamic programming
     * Complexity: O(n^2)
     */
    public static int minJumpsDP(int[] A) {
        int n = A.length;
        int[] jumps = new int[n];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;
        for (int i = 1; i < n; i ++) {
            for (int j = 0; j < i; j ++) {
                // Can we reach 'i' from 'j' plus A[j] jump?
                if (j + A[j] >= i) {
                    jumps[i] = Math.min(jumps[i], jumps[j] + 1);
                }
            }
        }
        return jumps[n - 1];
    }

    /**
     * Linear greedy solution
     * Complexity: O(n)
     */
    public static int minJumpsGreedy(int[] A) {
        int n = A.length;
        int currLadder = -1;
        int nextLadder = -1;
        int jumps = 0;
        for (int i = 0; i < n; i++) {
            nextLadder = Math.max(currLadder, i + A[i]);
            if (i > currLadder) {
                jumps++;
                currLadder = nextLadder;
                // Lets assume there is always a way to reach the end
                // no need to check if nextLadder is long enough
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        int[] array = {2,3,1,1,4};
        System.out.println("minJumpsRec(array) = " + minJumpsRec(array, 0) + " # Expected: 2");
        System.out.println("minJumpsDP(array) = " + minJumpsDP(array) + " # Expected: 2");
        System.out.println("minJumpsGreedy(array) = " + minJumpsGreedy(array) + " # Expected: 2");
    }
}
