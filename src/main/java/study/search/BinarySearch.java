package study.search;

import java.util.Arrays;

public class BinarySearch {

    private final int[] arr;

    public BinarySearch(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        this.arr = arr.clone();
        Arrays.sort(this.arr);
    }

    public int search(int target) {
        int length = arr.length;
        int low = 0;
        int high = length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * target 이상인 수가 처음으로 나오는 위치를 반환한다.
     */
    public int lowerBound(int target) {
        int length = arr.length;
        int low = 0;
        int high = length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target && (mid == 0 || arr[mid - 1] < target)) {
                // arr[mid]가 target보다 이상인 경우
                // - mid == 0 이면, arr[mid]가 최초의 이상인 값
                // - arr[mid - 1] < target 이면, arr[mid]가 최초의 이상인 값
                return mid;
            } else if (arr[mid] >= target) {
                // 만약에 arr[mid]가 target보다 이상이지만, 첫번째 이상인 값이 아닌 경우
                high = mid - 1;
            } else {
                // arr[mid]가 target보다 작은 경우, 이상인 값을 탐색해야하기 때문에 low = mid + 1
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * target 초과의 수가 처음 나오는 위치를 반환한다.
     */
    public int upperBound(int target) {
        int length = arr.length;
        int low = 0;
        int high = length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > target && (mid == 0 || arr[mid - 1] <= target)) {
                // arr[mid]가 target보다 초과인 경우
                // - mid == 0 이면, arr[mid]가 최초로 초과인 값
                // - arr[mid - 1] <= target 이면, arr[mid]가 최초로 초과인 값
                return mid;
            } else if (arr[mid] > target) {
                // 만약에 arr[mid]가 target보다 초과이지만, 첫번째 초과인 값이 아닌 경우
                high = mid - 1;
            } else {
                // arr[mid]가 target보다 작은 경우, 초과인 값을 탐색해야하기 때문에 low = mid + 1
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * target 의 개수를 반환한다.
     */
    public int count(int target) {
        int lower = lowerBound(target);
        if (lower == -1) {
            return 0;
        }
        int upper = upperBound(target);
        if (upper == -1) {
            return arr.length - lower;
        }

        return upper - lower;
    }


    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch(new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 17, 17, 17, 19});
        System.out.println(binarySearch.search(15));
        System.out.println(binarySearch.lowerBound(17));
        System.out.println(binarySearch.upperBound(17));
        System.out.println("17의 개수 : " + (binarySearch.upperBound(17) - binarySearch.lowerBound(17)));
    }
}
