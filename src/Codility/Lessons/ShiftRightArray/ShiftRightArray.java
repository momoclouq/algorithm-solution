package Codility.Lessons.ShiftRightArray;

public class ShiftRightArray {
    public static void main(String[] args) {

    }

    public static int[] solution(int[] arr, int K) {
        int arrLen = arr.length;
        int[] tempArr = new int[arrLen];

        for (int i = 0; i < arrLen; i++) {
            tempArr[(i + K) % arrLen] = arr[i];
        }

        return tempArr;
    }
}
