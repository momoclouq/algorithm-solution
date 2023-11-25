package Codility.Lessons.CountDiv;

public class CountDiv {
    public static void main(String[] args) {
//        Write a function:
//
//        class Solution { public int solution(int A, int B, int K); }
//
//        that, given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K, i.e.:
//
//        { i : A ≤ i ≤ B, i mod K = 0 }
//
//        For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.
//
//        Write an efficient algorithm for the following assumptions:
//
//        A and B are integers within the range [0..2,000,000,000];
//        K is an integer within the range [1..2,000,000,000];
//        A ≤ B.
        System.out.println(solution(6, 11, 2));
        System.out.println(solution(0, 1, 11));

    }

    public static int solution(int A, int B, int K) {
        // brute force
//        int count = 0;
//
//        for (int i = A; i <= B; i++) {
//            if (i % K == 0) count++;
//        }
//
//        return count;

        // O(1) using logic
        // suppose A = 6, B = 11, K = 2
        // the result is: 6, 8, 10 -> 3
        // You can see that we can find the largest limit that is divisible by K in between [A, B] -> the upper limit
        // is: B / K (as "/" operator will return the result without the mod)
        // we can also deduce that the lowest limit of [A, B] that is divisible by K will be
        // is: A / K
        if (A % K == 0) {
            return (B / K - A / K + 1); // have to count A if A mod K == 0
        }

        return B / K - A / K;
    }
}
