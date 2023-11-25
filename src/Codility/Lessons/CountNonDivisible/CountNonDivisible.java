package Codility.Lessons.CountNonDivisible;

import java.util.*;
import java.util.stream.Collectors;

public class CountNonDivisible {
    public static void main(String[] args) {
//        You are given an array A consisting of N integers.
//
//        For each number A[i] such that 0 ≤ i < N, we want to count the number of elements of the array that are not the divisors of A[i]. We say that these elements are non-divisors.
//
//                For example, consider integer N = 5 and array A such that:
//
//        A[0] = 3
//        A[1] = 1
//        A[2] = 2
//        A[3] = 3
//        A[4] = 6
//        For the following elements:
//
//        A[0] = 3, the non-divisors are: 2, 6,
//                A[1] = 1, the non-divisors are: 3, 2, 3, 6,
//                A[2] = 2, the non-divisors are: 3, 3, 6,
//                A[3] = 3, the non-divisors are: 2, 6,
//                A[4] = 6, there aren't any non-divisors.
//        Write a function:
//
//        class Solution { public int[] solution(int[] A); }
//
//        that, given an array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.
//
//                Result array should be returned as an array of integers.
//
//                For example, given:
//
//        A[0] = 3
//        A[1] = 1
//        A[2] = 2
//        A[3] = 3
//        A[4] = 6
//        the function should return [2, 4, 3, 2, 0], as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [1..50,000];
//        each element of array A is an integer within the range [1..2 * N].
        System.out.println(Arrays.toString(solution(new int[]{3, 1, 2, 3, 6})));
        System.out.println(Arrays.toString(solution(new int[]{1, 1, 1, 1, 1})));
        System.out.println(Arrays.toString(solution(new int[]{1, 1, 1, 1, 1, 3, 2})));
        System.out.println(Arrays.toString(solution(new int[]{50000, 50000, 50000, 50000, 100000, 1})));
        System.out.println(Arrays.toString(solution(new int[]{2, 10, 4, 5, 7, 2})));



    }

    private static HashMap<Integer, Integer> generateValueMap(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            if (!map.containsKey(A[i])) {
                map.put(A[i], 1);
            } else {
                map.put(A[i], map.get(A[i]) + 1);
            }
        }

        return map;
    }

    private static HashMap<Integer, HashSet<Integer>> generateDivisorMap(int[] A, int maxValue) {
        HashMap<Integer, HashSet<Integer>> divisors = new HashMap<>();
        // every natural number has divisor of 1
        for (int value: A) {
            divisors.put(value, new HashSet<>(Arrays.asList(1)));
        }

        // find out all divisors < sqrt(A max)
        // using brute force
        for (int divisor = 2; divisor <= Math.sqrt(maxValue) + 1; divisor++) {
            int multiple = divisor;
            while (multiple <= maxValue) {
                if (divisors.containsKey(multiple) && !divisors.get(multiple).contains(divisor)) {
                    divisors.get(multiple).add(divisor);
                }
                multiple += divisor;
            }
        }

        // compute divisors greater than sqrt(maxValue)
        // filter out the duplicate ones and combine them
        for (Map.Entry<Integer, HashSet<Integer>> entry: divisors.entrySet()) {
            Set<Integer> temp = entry.getValue().stream().map((divisor) -> {
                return entry.getKey() / divisor;
            }).collect(Collectors.toSet());

            entry.getValue().addAll(temp);
        }

        return divisors;
    }

    // reference: https://codesays.com/2014/solution-to-count-non-divisible-by-codility/
    public static int[] solution(int[] A) {
        // find max value of A
        int maxValue = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > maxValue) {
                maxValue = A[i];
            }
        }

        int len = A.length;

        // compute frequency of occurence of each element in array A
        HashMap<Integer, Integer> count = generateValueMap(A);

        // compute the divisors of each element in A
        HashMap<Integer, HashSet<Integer>> divisors = generateDivisorMap(A, maxValue);


        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int sum = divisors.get(A[i]).stream().map((divisor) -> {
                return count.getOrDefault(divisor, 0);
            }).mapToInt(Integer::intValue).sum();
            result[i] = len - sum;
        }
        return result;
    }
}
