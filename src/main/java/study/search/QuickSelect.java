package study.search;

public class QuickSelect {

    /**
     * 배열에서 k번째로 작은 값을 찾는다. (1-based index)
     * 예: k=1이면 가장 작은 값, k=3이면 3번째로 작은 값
     */
    public int findKthSmallest(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    /**
     * 실제 QuickSelect 로직 (재귀)
     *
     * @param left  탐색 시작 인덱스
     * @param right 탐색 끝 인덱스
     * @param k     찾고자 하는 인덱스 (0-based)
     */
    private int quickSelect(int[] arr, int left, int right, int k) {
        int pivotIdx = partition(arr, left, right);
        if (pivotIdx == k) {
            return arr[pivotIdx];
        } else if (pivotIdx < k) {
            return quickSelect(arr, pivotIdx + 1, right, k);
        } else {
            return quickSelect(arr, left, pivotIdx - 1, k);
        }
    }

    /**
     * Partition: pivot 기준으로 작은 값은 왼쪽, 큰 값은 오른쪽으로 분리
     * - i의 좌측은 무조건 pivot보다 작은 값
     * - i와 j 사이의 값은 무조건 pivot보다 큰 값
     * - j에 위치한 값을 i에 위치한 값과 Swap하면 무조건 작은값이 i의 좌측으로, 큰값이 i와 j의 사이에 위치하게 된다.
     *
     * @return pivot의 최종 위치
     */
    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        int j = left;
        while (j < right) {
            if (arr[j] > pivot) {
                j++;
            } else {
                swap(arr, i, j);
                i++;
                j++;
            }
        }

        swap(arr, i, right);

        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        QuickSelect quickSelect = new QuickSelect();
        int[] arr = {3, 9, 1, 8, 2, 7, 5};

        System.out.println("1번째로 작은 값: " + quickSelect.findKthSmallest(arr.clone(), 1)); // 예상: 1
        System.out.println("3번째로 작은 값: " + quickSelect.findKthSmallest(arr.clone(), 3)); // 예상: 3
        System.out.println("7번째로 작은 값: " + quickSelect.findKthSmallest(arr.clone(), 7)); // 예상: 9
    }
}
