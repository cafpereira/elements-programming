package com.example.chapter17;

class Fibonacci {

  public static void main(String[] args) {
    int n = 8;
    int nth = fibonacci_linear_v3(n);
    System.out.println("F(n)=" + nth + ", n=" + n);
  }

  public static int fibonacci_exponential_v1(int n) {
    if (n <= 1) {
      return n;
    }
    return fibonacci_exponential_v1(n-1) +
        fibonacci_exponential_v1(n-2);
  }

  public static int fibonacci_linear_cache_v2(int n) {
    int[] solutions = new int[n];
    solutions[0] = 0;
    solutions[1] = 1;

    for (int i = 2; i < n; i++) {
      solutions[i] = solutions[i-1] + solutions[i-2];
    }

    return solutions[n-1] + solutions[n-2];
  }

  public static int fibonacci_linear_v3(int n) {
    if (n <= 1) {
      return n;
    }
    int f_minus_2 = 0;
    int f_minus_1 = 1;
    int f_n = 0;

    for (int i = 2; i <= n; i ++) {
      f_n = f_minus_1 + f_minus_2;
      f_minus_2 = f_minus_1;
      f_minus_1 = f_n;
    }
    return f_n;
  }
}
