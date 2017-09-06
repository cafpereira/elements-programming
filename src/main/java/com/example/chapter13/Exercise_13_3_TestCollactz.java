package com.example.chapter13;

import java.io.*;
import java.util.*;

/**
 * Collatz conjecture:
 * Take any natural number. If it is odd, triple it and add one; if it is even, halve it.
 * Repeat the process indefinitely. No matter what number you begin with, you will
 * eventually arrive at 1.
 *
 * Test the Collatz conjecture for the first n positive integers.
 */
class TestCollatz {
    public static void main(String[] args) {
        int n = 10;
        System.out.println("testCollatz( "+ n + " ) = " + testCollatz(n));
    }

    public static boolean testCollatz(int n) {
        // History keeps numbers already tested to converge to 1
        Set<Integer> history = new HashSet<>();

        for (int i = 3; i <= n; i++) {
            if (history.contains(i)) {
                continue;
            }
            Set<Integer> loopCheck = new HashSet<>();
            int cur = i;
            loopCheck.add(cur);
            do {
                int next = nextCollatz(cur);
                if (!loopCheck.add(next)) {
                    // Loop found, sequence does not converge to 1
                    return false;
                }
                if (oddNumber(cur) && (next <= cur)) {
                    // Integer overflow, collatz sequence converges to infinity
                    return false;
                }
                cur = next;
                if (!history.add(cur)) {
                    // already verified cur converges to 1
                    break;
                }
            } while (cur >= i);
        }
        return true;
    }

    public static int nextCollatz(int x) {
        if (x % 2 == 0) {
            return x / 2;
        } else {
            return 3 * x + 1;
        }
    }

    public static boolean oddNumber(int x) {
        return x % 2 != 0;
    }
}