package study.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSelectTest {

    @Nested
    @DisplayName("findKthSmallest - K번째로 작은 값 찾기")
    class FindKthSmallestTest {

        @Test
        @DisplayName("기본 케이스")
        void basicCase() {
            QuickSelect qs = new QuickSelect();
            int[] arr = {3, 9, 1, 8, 2, 7, 5};

            assertEquals(1, qs.findKthSmallest(arr.clone(), 1)); // 1번째 = 1
            assertEquals(2, qs.findKthSmallest(arr.clone(), 2)); // 2번째 = 2
            assertEquals(3, qs.findKthSmallest(arr.clone(), 3)); // 3번째 = 3
            assertEquals(5, qs.findKthSmallest(arr.clone(), 4)); // 4번째 = 5
            assertEquals(9, qs.findKthSmallest(arr.clone(), 7)); // 7번째 = 9
        }

        @Test
        @DisplayName("단일 요소 배열")
        void singleElement() {
            QuickSelect qs = new QuickSelect();
            int[] arr = {42};

            assertEquals(42, qs.findKthSmallest(arr.clone(), 1));
        }

        @Test
        @DisplayName("두 개 요소 배열")
        void twoElements() {
            QuickSelect qs = new QuickSelect();
            int[] arr = {5, 2};

            assertEquals(2, qs.findKthSmallest(arr.clone(), 1));
            assertEquals(5, qs.findKthSmallest(arr.clone(), 2));
        }

        @Test
        @DisplayName("이미 정렬된 배열")
        void alreadySorted() {
            QuickSelect qs = new QuickSelect();
            int[] arr = {1, 2, 3, 4, 5};

            assertEquals(1, qs.findKthSmallest(arr.clone(), 1));
            assertEquals(3, qs.findKthSmallest(arr.clone(), 3));
            assertEquals(5, qs.findKthSmallest(arr.clone(), 5));
        }

        @Test
        @DisplayName("역순 정렬된 배열")
        void reverseSorted() {
            QuickSelect qs = new QuickSelect();
            int[] arr = {5, 4, 3, 2, 1};

            assertEquals(1, qs.findKthSmallest(arr.clone(), 1));
            assertEquals(3, qs.findKthSmallest(arr.clone(), 3));
            assertEquals(5, qs.findKthSmallest(arr.clone(), 5));
        }

        @Test
        @DisplayName("중복 값이 있는 배열")
        void duplicateValues() {
            QuickSelect qs = new QuickSelect();
            int[] arr = {3, 1, 2, 1, 3, 2};

            assertEquals(1, qs.findKthSmallest(arr.clone(), 1));
            assertEquals(1, qs.findKthSmallest(arr.clone(), 2));
            assertEquals(2, qs.findKthSmallest(arr.clone(), 3));
            assertEquals(3, qs.findKthSmallest(arr.clone(), 6));
        }

        @Test
        @DisplayName("모든 값이 같은 배열")
        void allSameValues() {
            QuickSelect qs = new QuickSelect();
            int[] arr = {5, 5, 5, 5, 5};

            assertEquals(5, qs.findKthSmallest(arr.clone(), 1));
            assertEquals(5, qs.findKthSmallest(arr.clone(), 3));
            assertEquals(5, qs.findKthSmallest(arr.clone(), 5));
        }

        @Test
        @DisplayName("음수 포함 배열")
        void negativeNumbers() {
            QuickSelect qs = new QuickSelect();
            int[] arr = {-3, 5, -1, 0, 2};

            assertEquals(-3, qs.findKthSmallest(arr.clone(), 1));
            assertEquals(-1, qs.findKthSmallest(arr.clone(), 2));
            assertEquals(0, qs.findKthSmallest(arr.clone(), 3));
            assertEquals(5, qs.findKthSmallest(arr.clone(), 5));
        }

        @Test
        @DisplayName("큰 배열에서 중간값 찾기")
        void findMedian() {
            QuickSelect qs = new QuickSelect();
            int[] arr = {9, 3, 7, 1, 5, 8, 2, 6, 4};

            assertEquals(5, qs.findKthSmallest(arr.clone(), 5));
        }
    }
}
