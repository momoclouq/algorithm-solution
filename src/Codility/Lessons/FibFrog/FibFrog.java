package Codility.Lessons.FibFrog;

import java.util.Arrays;
import java.util.LinkedList;

class Status {
    private int position;
    private int moves;

    public Status(int position, int moves) {
        this.position = position;
        this.moves = moves;
    }

    public int getPosition() {
        return this.position;
    }

    public int getMoves() {
        return this.moves;
    }
}

public class FibFrog {
    public static void main(String[] args) {
//        The Fibonacci sequence is defined using the following recursive formula:
//
//        F(0) = 0
//        F(1) = 1
//        F(M) = F(M - 1) + F(M - 2) if M >= 2
//        A small frog wants to get to the other side of a river. The frog is initially located at one bank of the river (position −1) and wants to get to the other bank (position N). The frog can jump over any distance F(K), where F(K) is the K-th Fibonacci number. Luckily, there are many leaves on the river, and the frog can jump between the leaves, but only in the direction of the bank at position N.
//
//        The leaves on the river are represented in an array A consisting of N integers. Consecutive elements of array A represent consecutive positions from 0 to N − 1 on the river. Array A contains only 0s and/or 1s:
//
//        0 represents a position without a leaf;
//        1 represents a position containing a leaf.
//        The goal is to count the minimum number of jumps in which the frog can get to the other side of the river (from position −1 to position N). The frog can jump between positions −1 and N (the banks of the river) and every position containing a leaf.
//
//        For example, consider array A such that:
//
//        A[0] = 0
//        A[1] = 0
//        A[2] = 0
//        A[3] = 1
//        A[4] = 1
//        A[5] = 0
//        A[6] = 1
//        A[7] = 0
//        A[8] = 0
//        A[9] = 0
//        A[10] = 0
//        The frog can make three jumps of length F(5) = 5, F(3) = 2 and F(5) = 5.
//
//        Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//        that, given an array A consisting of N integers, returns the minimum number of jumps by which the frog can get to the other side of the river. If the frog cannot reach the other side of the river, the function should return −1.
//
//        For example, given:
//
//        A[0] = 0
//        A[1] = 0
//        A[2] = 0
//        A[3] = 1
//        A[4] = 1
//        A[5] = 0
//        A[6] = 1
//        A[7] = 0
//        A[8] = 0
//        A[9] = 0
//        A[10] = 0
//        the function should return 3, as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [0..100,000];
//        each element of array A is an integer that can have one of the following values: 0, 1.
        System.out.println(solution(new int[]{0,0,0,1,1,0,1,0,0,0,0}));
        System.out.println(solution(new int[]{1}));
    }

    private static void reverseArray(int[] A) {
        for (int i = 0; i < A.length / 2; i++) {
            int temp = A[i];
            A[i] = A[A.length - 1 - i];
            A[A.length - 1 - i] = temp;
        }
    }

    private static int[] fibonacciDynamic(int n) {
        // return all fibonacci numbers
        // less than or equal to n, in descending order
        // n must be equal to larger than 1
        int[] fib = new int[n + 2];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
            if (fib[i] > n) {
                reverseArray(fib);
                return Arrays.copyOfRange(fib, fib.length - i, fib.length - 1); // ignore the first 0 value
            } else if (fib[i] == n) {
                reverseArray(fib);
                return Arrays.copyOfRange(fib, fib.length - 1 - i, fib.length - 1); // ignore the first 0 value
            }
        }

        return fib;
    }

    public static int solution(int[] A) {
        int len = A.length;
        int[] fibonacciReversed = fibonacciDynamic(A.length + 1); // A.length + 1 is the goal
        LinkedList<Status> statusLinkedList = new LinkedList<>();
        statusLinkedList.add(new Status(-1, 0)); // initially we have position = -1 and moves = 0

        int nextTry = 0;
        boolean[] accessed = new boolean[len]; //check were we in this position before

        while (true) {
            if (nextTry == statusLinkedList.size()) {
                // no unprocessed attempts and we did not find any path yet -> no path exists
                return -1;
            }

            // obtain next attempts' status
            Status currentStatus = statusLinkedList.get(nextTry);
            nextTry++;
            int currentPos = currentStatus.getPosition();
            int currentMoves = currentStatus.getMoves();

            for (int length: fibonacciReversed) {
                if (currentPos + length == len) {
                    // found the goal
                    // as we are using breadth first search -> every run is equal/ shorter than the next
                    // if we found the goal -> shortest path will equal the current run
                    return currentMoves + 1;
                } else if (
                        currentPos + length > len
                        || A[currentPos + length] == 0
                        || accessed[currentPos + length]
                ) {
                    // three conditions are:
                    // 1. moving too far
                    // 2. cannot move there (no leaf)
                    // 3. already accessed that position
                    // with BFS, current attempt will never have less moves to foal the previous attemps -> can be pruned
                    continue;
                }

                statusLinkedList.add(new Status(currentPos + length, currentMoves + 1));
                accessed[currentPos + length] = true;
            }
        }
    }
}
