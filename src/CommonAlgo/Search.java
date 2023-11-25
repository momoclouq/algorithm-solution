package CommonAlgo;

public class Search {
    public static void main(String[] args) {
        // test binary search
        System.out.println(binarySearch(new int[]{1, 2, 3, 4, 5, 6}, 2));
        System.out.println(binarySearch(new int[]{1, 2}, 2));
        System.out.println(binarySearch(new int[]{2}, 2));
        System.out.println(binarySearch(new int[]{1, 2, 3}, 2));
    }

    public static int binarySearch(int[] A, int x) {
        int result = -1;
        int start = 0;
        int end = A.length - 1;

        while (start <= end) {
            int mid = start + ((end - start) / 2); // avoid int overflow

            if (A[mid] == x) {
                // found x
                result = mid;
                break;
            } else if (A[mid] > x) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return result;
    }
}
