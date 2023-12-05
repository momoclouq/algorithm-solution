package Leetcode.MinCostClimbingStairs;

public class MinCostClimbingStairs {
    public static void main(String[] args) {
        // 746
//        You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
//
//                You can either start from the step with index 0, or the step with index 1.
//
//        Return the minimum cost to reach the top of the floor.
    }

    public static int solution(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(
                    dp[i - 1] + cost[i],
                    dp[i - 2] + cost[i]
            );
        }

        return Math.min(dp[dp.length - 1], dp[dp.length - 2]);
    }
}
