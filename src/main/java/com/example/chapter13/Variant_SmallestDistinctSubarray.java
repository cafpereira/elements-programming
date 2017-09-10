package com.example.chapter13;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Variant Exercise 13.7 Given an array A, find a shortest subarray A[i : j]
 * such that each distinct value present in A is also present in the
 * subarray.
 */
class SmallestDistinctSubarray {

  public static void main(String[] args) {
    int[] A = new int[]{5,5,1,1,3,1,3,5,3,1};
    System.out.println("findSmallestDistinctSubarray() = " + findSmallestDistinctSubarray(A));
  }

  public static Subarray findSmallestDistinctSubarray(int[] A) {
    Set<Integer> distinctNumbers = distinctNumbersOf(A);
    LinkedHashMap<Integer, Integer> lastOccur = initLastOccurMap(distinctNumbers);
    int foundNumbers = 0;
    Subarray min = new Subarray(-1, -1);

    for (int i = 0; i < A.length; i++) {
      Integer last  = lastOccur.get(A[i]);
      if (last == null) {
        foundNumbers++;
      }

      // Explicitly remove the existing entry then put new ocurrence so LinkedHashMap
      // will move the entry to the front of the queue even if an entry with same key
      // is already present.
      lastOccur.remove(A[i]);
      lastOccur.put(A[i], i);

      if (foundNumbers == distinctNumbers.size()) {
        int start = getValueOfFirstEntry(lastOccur);
        Subarray sub = new Subarray(start, i);
        if (sub.length() < min.length()) {
          min = sub;
          // Performance optimizaion, the smallest interval possible has at least
          // one of each distinct number
          if (min.length() == distinctNumbers.size()) {
            return min;
          }
        }
      }
    }
    return min;
  }

  public static Integer getValueOfFirstEntry(LinkedHashMap<Integer, Integer> lastOccur) {
    return lastOccur.entrySet().iterator().next().getValue();
  }

  public static LinkedHashMap<Integer, Integer> initLastOccurMap(Set<Integer> distinctNumbers) {
    // LinkedHashMap guarantees iteration over key-value pairs takes place in
    // insertion order, most recent first.
    LinkedHashMap<Integer, Integer> lastOccur = new LinkedHashMap<>();
    for (Integer d : distinctNumbers) {
      lastOccur.put(d, null);
    }
    return lastOccur;
  }

  public static Set<Integer> distinctNumbersOf(int[] A) {
    Set<Integer> res = new HashSet<>();
    for (int value : A) {
      res.add(value);
    }
    return res;
  }
}

class Subarray {
  int start;
  int end;

  public Subarray(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public int length() {
    if (start == -1 || end == -1) {
      return Integer.MAX_VALUE;
    }
    return end - start + 1;
  }

  @Override
  public String toString() {
    return "[start:"+start+", end:"+end+", length:"+ length()+"]";
  }
}