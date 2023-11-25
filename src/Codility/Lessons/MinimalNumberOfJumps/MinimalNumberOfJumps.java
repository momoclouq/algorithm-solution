package Codility.Lessons.MinimalNumberOfJumps;

public class MinimalNumberOfJumps {
    public static void main(String[] args) {
        System.out.println(solution(0, 0, 30));
    }

    public static int solution(int X, int Y, int D) {
        return (int) Math.ceil((double) (Y - X) / D);
    }
}
