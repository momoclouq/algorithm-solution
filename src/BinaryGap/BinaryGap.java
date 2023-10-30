package BinaryGap;

public class BinaryGap {
    public static void main(String[] args) {
        System.out.println(solution(51712));
    }

    public static int solution(int N) {
        // Implement your solution here
        boolean start = false;
        int currentCount = 0;
        int binaryGap = 0;

        String binaryString = "";

        while(N != 0) {
            int remainder = N % 2;
            N = N / 2;

            binaryString = "" + remainder + binaryString;
        }

        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                if (start) {
                    binaryGap = Math.max(currentCount, binaryGap);
                    currentCount = 0;
                } else {
                    start = true;
                }
            } else {
                currentCount++;
            }
        }

        return binaryGap;
    }
}
