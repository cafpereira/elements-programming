package com.example.chapter19;

import java.util.*;

/**
 * Variant question 19.7 chain exponentiation problem
 */
class ChainExponentiation {

    /**
     * given n, find the shortest chain to reach x^n from x.
     * Each element in the chain is either x^(2*i), or x^j*x^i,
     * where x^i, x^j are previous elements.
     * solution: BFS
     */
    public static List<Integer> shortestAdditionChain(int n) {
        Queue<List<Integer>> queueOfChains = new LinkedList<>();
        queueOfChains.add(Arrays.asList(1));

        while (!queueOfChains.isEmpty()) {
            List<Integer> chain = queueOfChains.remove();
            Integer cur = chain.get(chain.size() - 1);
            for (Integer prev : chain) {
                List<Integer> path = new ArrayList<>(chain);
                Integer sum = prev + cur;
                path.add(sum);
                if (sum == n) {
                    return path;
                } else {
                    queueOfChains.add(path);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("shortestAdditionChain(" + 15 + ") = " + shortestAdditionChain(15));
    }
}
