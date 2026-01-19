package study.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    @Nested
    @DisplayName("lowerBound - target 이상인 첫 위치")
    class LowerBoundTest {

        @Test
        @DisplayName("기본 케이스: 중복 없는 배열")
        void basicCase() {
            BinarySearch bs = new BinarySearch(new int[]{1, 3, 5, 7, 9});
            assertEquals(2, bs.lowerBound(5));
        }

        @Test
        @DisplayName("중복 있는 배열에서 첫 위치 반환")
        void duplicateValues() {
            BinarySearch bs = new BinarySearch(new int[]{1, 2, 2, 2, 3});
            assertEquals(1, bs.lowerBound(2));
        }

        @Test
        @DisplayName("target이 배열에 없는 경우")
        void targetNotExists() {
            BinarySearch bs = new BinarySearch(new int[]{1, 3, 5, 7, 9});
            assertEquals(2, bs.lowerBound(4)); // 4 이상인 첫 위치 = index 2 (값 5)
        }

        @Test
        @DisplayName("모든 요소가 target보다 큰 경우")
        void allGreaterThanTarget() {
            BinarySearch bs = new BinarySearch(new int[]{10, 20, 30});
            assertEquals(0, bs.lowerBound(5)); // 5 이상인 첫 위치 = index 0
        }

        @Test
        @DisplayName("모든 요소가 target보다 작은 경우")
        void allLessThanTarget() {
            BinarySearch bs = new BinarySearch(new int[]{1, 2, 3});
            assertEquals(-1, bs.lowerBound(100));
        }

        @Test
        @DisplayName("모든 요소가 같은 경우")
        void allSameValues() {
            BinarySearch bs = new BinarySearch(new int[]{5, 5, 5, 5, 5});
            assertEquals(0, bs.lowerBound(5));
        }

        @Test
        @DisplayName("첫 번째 요소가 target인 경우")
        void targetAtFirst() {
            BinarySearch bs = new BinarySearch(new int[]{5, 10, 15, 20});
            assertEquals(0, bs.lowerBound(5));
        }

        @Test
        @DisplayName("마지막 요소가 target인 경우")
        void targetAtLast() {
            BinarySearch bs = new BinarySearch(new int[]{1, 3, 5, 7});
            assertEquals(3, bs.lowerBound(7));
        }

        @Test
        @DisplayName("단일 요소 배열")
        void singleElement() {
            BinarySearch bs = new BinarySearch(new int[]{5});
            assertEquals(0, bs.lowerBound(5));
            assertEquals(0, bs.lowerBound(3));  // 3 이상인 첫 위치 = 0
            assertEquals(-1, bs.lowerBound(10)); // 10 이상인 값 없음
        }
    }

    @Nested
    @DisplayName("upperBound - target 초과인 첫 위치")
    class UpperBoundTest {

        @Test
        @DisplayName("기본 케이스: 중복 없는 배열")
        void basicCase() {
            BinarySearch bs = new BinarySearch(new int[]{1, 3, 5, 7, 9});
            assertEquals(3, bs.upperBound(5)); // 5 초과인 첫 위치 = index 3 (값 7)
        }

        @Test
        @DisplayName("중복 있는 배열에서 초과인 첫 위치 반환")
        void duplicateValues() {
            BinarySearch bs = new BinarySearch(new int[]{1, 2, 2, 2, 3});
            assertEquals(4, bs.upperBound(2)); // 2 초과인 첫 위치 = index 4 (값 3)
        }

        @Test
        @DisplayName("target이 배열에 없는 경우")
        void targetNotExists() {
            BinarySearch bs = new BinarySearch(new int[]{1, 3, 5, 7, 9});
            assertEquals(2, bs.upperBound(4)); // 4 초과인 첫 위치 = index 2 (값 5)
        }

        @Test
        @DisplayName("모든 요소가 target보다 큰 경우")
        void allGreaterThanTarget() {
            BinarySearch bs = new BinarySearch(new int[]{10, 20, 30});
            assertEquals(0, bs.upperBound(5)); // 5 초과인 첫 위치 = index 0
        }

        @Test
        @DisplayName("모든 요소가 target보다 작거나 같은 경우")
        void allLessOrEqualThanTarget() {
            BinarySearch bs = new BinarySearch(new int[]{1, 2, 3});
            assertEquals(-1, bs.upperBound(100));
            assertEquals(-1, bs.upperBound(3)); // 3 초과인 값 없음
        }

        @Test
        @DisplayName("모든 요소가 같은 경우")
        void allSameValues() {
            BinarySearch bs = new BinarySearch(new int[]{5, 5, 5, 5, 5});
            assertEquals(-1, bs.upperBound(5)); // 5 초과인 값 없음
        }

        @Test
        @DisplayName("마지막 요소가 target 초과인 경우")
        void targetAtLast() {
            BinarySearch bs = new BinarySearch(new int[]{1, 3, 5, 7});
            assertEquals(3, bs.upperBound(5)); // 5 초과인 첫 위치 = index 3 (값 7)
        }

        @Test
        @DisplayName("단일 요소 배열")
        void singleElement() {
            BinarySearch bs = new BinarySearch(new int[]{5});
            assertEquals(-1, bs.upperBound(5)); // 5 초과인 값 없음
            assertEquals(0, bs.upperBound(3));   // 3 초과인 첫 위치 = 0
            assertEquals(-1, bs.upperBound(10)); // 10 초과인 값 없음
        }
    }

    @Nested
    @DisplayName("lowerBound + upperBound 조합")
    class CombinationTest {

        @Test
        @DisplayName("target 개수 구하기")
        void countTarget() {
            BinarySearch bs = new BinarySearch(new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 17, 17, 17, 19});
            int lower = bs.lowerBound(17);
            int upper = bs.upperBound(17);
            assertEquals(4, upper - lower); // 17의 개수 = 4
        }

        @Test
        @DisplayName("범위 내 요소 개수 구하기")
        void countInRange() {
            BinarySearch bs = new BinarySearch(new int[]{1, 3, 5, 7, 9, 11, 13, 15});
            int lower = bs.lowerBound(5);   // index 2
            int upper = bs.upperBound(11);  // index 6
            assertEquals(4, upper - lower); // 5, 7, 9, 11 → 4개
        }
    }
}
