package CommonAlgo;

import java.util.ArrayList;
import java.util.Arrays;

public class Common {
    public static void main(String[] args) {
        // prefix sum:  fast calculation of sums of elements in given slice
//        System.out.println(Arrays.toString(prefix_sums(new int[]{1, 2, 3, 4, 5})));
//        System.out.println(Arrays.toString(prefix_sums(new int[]{10, 22, 33, 44, 55})));
//        System.out.println(Arrays.toString(prefix_sums(new int[]{1})));
//
//        // count distinct
//        System.out.println(countDistinct(new int[]{1, 2, 4, 2, 3, 90, 87, 90, -1, 23}));

        // fibonacci
        System.out.println(fibonacciDyn(31));
    }

    public static int[] prefix_sums(int[] A) {
        int n = A.length;
        if (n == 0) return new int[1];

        int[] prefixSums = new int[n+1]; // include the first value
        prefixSums[0] = 0;

        for (int i = 1; i < n + 1; i++) {
            prefixSums[i] = prefixSums[i - 1] + A[i - 1];
        }

        return prefixSums;
    }

    public static int countDistinct(int[] A) {
        if (A.length == 0) return 0;
        int len = A.length;
        int result = 1;

        Arrays.sort(A);
        for (int i = 1; i < len; i++) {
            if (A[i] != A[i - 1]) {
                result++;
            }
        }

        return result;
    }

    public static int findLeader(int[] A) {
        // create temp stack and find the candidate
        // we say this is the candidate because this only finds the value with the largest occurrence
        // that will remain when we loop through the array
        // an example of a candidate that remains but is not: 1234567777 -> 7 is the candidate but is not the leader
        int size = 0;
        int value = 0;
        for (int i = 0; i < A.length; i++) {
            if (size == 0) {
                size += 1;
                value = A[i];
            } else if (value != A[i]) {
                size -= 1;
            } else {
                size += 1;
            }
        }

        // get leader candidate
        int candidate = -1;
        if (size != 0) {
            candidate = value;
        }
        int leader = -1;
        int count = 0;

        // final check to see if candidate is actually leader
        for (int i = 0; i < A.length; i++) {
            if (A[i] == candidate) {
                count++;
            }
        }
        if (count > A.length / 2) {
            leader = candidate;
        }

        return leader;
    }

    public static int golden_max_slice(int[] A) {
        int maxSlice = 0;
        int maxEnding = 0;

        for (int i = 0; i < A.length; i++) {
            int value = A[i];

            // get the max ending when reach each position, if the ending is < 0 then we can assume that any slice that ends at this position
            // will be lesser than the empty slice (= 0)
            // so the next maxEnding count will start from the next position, not considering the previous
            maxEnding = Math.max(0, maxEnding + value);
            maxSlice = Math.max(maxSlice, maxEnding);
        }

        return maxSlice;
    }

    // count the number of divisors of a positive number (not just prime divisors)
    public static int divisors(int n) {
        int i = 0;
        int result = 0;

        while (i * i < n) {
            if (n % i == 0) {
                // count 2 as n % i = 0 && n % (n / i) == 0
                result += 2;
            }
            i++;
        }

        // this is when i * i >= n is true
        // count only 1 for this case
        if (i * i == n) {
            result += 1;
        }

        return result;
    }

    // check if number is prime
    public static boolean primality(int n) {
        int i = 2;
        while (i * i <= n) {
            if (n % i == 0) return false;
            i++;
        }

        return true;
    }

    // get all prime number - sieve of Eratosthenes
    public static boolean[] sieve(int n) {
        boolean[] sieve = new boolean[n + 1]; // so we can get the correct index
        Arrays.fill(sieve, true);
        sieve[0] = sieve[1] = false;

        int i = 2;
        while (i * i <= n) {
            if (sieve[i]) {
                int k = i * i;
                while (k <= n) {
                    sieve[k] = false;
                    k += i;
                }
            }

            i++;
        }

        return sieve;
    }

    ////////////////////////////////
    // get factorization of a number
    public static int[] arrayF(int n) {
        int[] F = new int[n + 1];
        int i = 2;

        while (i * i <= n) {
            if (F[i] == 0) {
                int k = i * i;
                while (k <= n) {
                    if (F[k] == 0) {
                        F[k] = i;
                    }
                    k += i;
                }
            }

            i++;
        }

        return F;
    }

    // F: array of lowest factorization of each number
    public static ArrayList<Integer> factorization(int x, int[] F) {
        ArrayList<Integer> primeFactors = new ArrayList<>();

        while (F[x] > 0) {
            primeFactors.add(F[x]);
            x /= F[x];
        }

        primeFactors.add(x); // the last value (which is a prime number)
        return primeFactors;
    }

    ///////////////////////////////
    // GCD: greatest common divisor
    // reference: https://codility.com/media/train/10-Gcd.pdf
    // find gcd by subtraction: O(n) -> worst performer
    public static long gcdBySubtraction(long a, long b) {
        if (a == b) {
            return a;
        } else if (a > b) {
            return gcdBySubtraction(a - b, b);
        } else {
            return gcdBySubtraction(a, b - a);
        }
    }

    // find gcd by dividing: O(log(a + b) but performs terrible for large number
    public static long gcdByDividing(long a, long b) {
        if (a % b == 0) {
            return b;
        } else {
            return gcdByDividing(b, a % b);
        }
    }

    // find gcd: binary euclidean algorithm: O(log(a + b)) but performs better for large number
    public static long gcdByEuclidean(long a, long b, long res) {
        if (a == b) {
            return a * res;
        } else if (a % 2 == 0 && b % 2 == 0) {
            return gcdByEuclidean(a / 2, b / 2, res * 2);
        } else if (a % 2 == 0) {
            return gcdByEuclidean(a / 2, b, res);
        } else if (b % 2 == 0) {
            return gcdByEuclidean(a, b / 2, res);
        } else if (a > b) {
            return gcdByEuclidean(a - b, b, res);
        } else {
            return gcdByEuclidean(a, b - a, res);
        }
    }

    // find least common multiple (LCM): the smallest positive integer that is divisible by both a and b
    public static long lcm(long a, long b) {
        long gcd = gcdByEuclidean(a, b, 1);
        return a * b / gcd;
    }

    ////////////////////////////
    // find fibonacci number - n >= 1
    // recursively
    public static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    // dynamically
    public static long fibonacciDyn(int n) {
        long[] fib = new long[n + 1]; // so we can count from 1
        fib[1] = 1;

        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
            System.out.println("fib " + i + ": " + fib[i]);
        }

        return fib[n];
    }

    ///////////////////
    // caterpillar method
    public static boolean caterpillarMethod(int[] A, int s) {
        int front = 0;
        int total = 0;

        for (int back = 0; back < A.length; back++) {
            while (front < A.length && total + A[front] <= s) {
                total += A[front];
                front++;
            }

            // found the solution
            if (total == s) {
                return true;
            }

            // remove the current back value
            total -= A[back];
        }

        return false;
    }

    // problem:
//    You are given n sticks (of lengths 1 � a0 � a1 � . . . � an−1 � 109). The goal is
//    to count the number of triangles that can be constructed using these sticks. More precisely,
//    we have to count the number of triplets at indices x < y < z, such that ax + ay > az
    public static int triangles(int[] A) {
        int result = 0;

        for (int x = 0; x < A.length; x++) {
            int z = x + 2; // store the z value as next z can only be equal or greater than the current z value
            for (int y = x + 1; y < A.length; y++) {
                while (z < A.length && A[x] + A[y] > A[z]) {
                    z++;
                }

                result += z - y - 1; // -1 as the current z: A[z] >= A[x] + A[y]
            }
        }

        return result;
    }

    private static class ChangeCount {
        public int change;
        public int count;
    }

    // greedy
    public static ArrayList<ChangeCount> greedyCoinChanging(int[] M, int k) {
        // assume that M is an array of change sorted from largest to lowest
        ArrayList<ChangeCount> result = new ArrayList<>();

        for (int i = M.length - 1; i >= 0; i--) {
            ChangeCount currentChange = new ChangeCount();
            currentChange.change = M[i];
            currentChange.count = k / M[i];
            result.add(currentChange);

            k = k % M[i];
        }

        return result;
    }

    // dynamic programming
    // references: https://codility.com/media/train/15-DynamicProgramming.pdf

    // with C = array of unique coins sorted from lowest to highest value, k = the total amount
    public static int[] dynamic_coin_changing(int[] C, int k) {
        int n = C.length;

        // from no coin to all coin (0 -> n)
        // from total number = 0 -> k
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i < dp[0].length; i++) {
            // assign max value to dp when coin set is empty
            dp[0][i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < C[i - 1]; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            for (int j = C[i - 1]; j < k + 1; j++) {
                dp[i][j] = Math.min(dp[i][j - C[i - 1]] + 1, dp[i - 1][j]);
            }
        }

        return dp[n]; // return the whole array value
    }

    // problem:
    // A small frog wants to get from position 0 to k (1 � k � 10 000). The frog can
    //jump over any one of n fixed distances s0, s1, . . . , sn−1 (1 � si � k). The goal is to count the
    //number of different ways in which the frog can jump to position k. To avoid overflow, it is
    //sufficient to return the result modulo q, where q is a given number.
    //We assume that two patterns of jumps are different if, in one pattern, the frog visits
    //a position which is not visited in the other pattern

    public static int frog(int[] S, int k, int q) {
        int n = S.length;

        int[] dp = new int[k + 1];
        dp[0] = 1; // the ways the frog can jump to position 0 is only 1

        for (int i = 1; i < dp.length; i++) {
            for (int m = 0; m < S.length; m++) {
                if (S[m] <= i) {
                    dp[i] = (dp[i] + dp[i - S[m]]) % q;
                }
            }
        }

        return dp[n];
    }
}
