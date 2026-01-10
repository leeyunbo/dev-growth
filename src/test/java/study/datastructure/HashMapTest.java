package study.datastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    private HashMap<String, Integer> map;

    @BeforeEach
    void setUp() {
        map = new HashMap<>();
    }

    @Nested
    @DisplayName("put / get")
    class PutGetTest {

        @Test
        @DisplayName("기본 put/get")
        void basicPutGet() {
            map.put("one", 1);
            map.put("two", 2);

            assertEquals(1, map.get("one"));
            assertEquals(2, map.get("two"));
        }

        @Test
        @DisplayName("없는 key 조회시 null 반환")
        void getNonExistentKey() {
            assertNull(map.get("notExist"));
        }

        @Test
        @DisplayName("중복 key는 값 업데이트")
        void duplicateKeyUpdate() {
            map.put("key", 1);
            map.put("key", 100);

            assertEquals(100, map.get("key"));
        }

        @Test
        @DisplayName("해시 충돌 시에도 정상 동작")
        void hashCollision() {
            // 같은 인덱스에 여러 개 저장되는 상황
            for (int i = 0; i < 20; i++) {
                map.put("key" + i, i);
            }

            for (int i = 0; i < 20; i++) {
                assertEquals(i, map.get("key" + i));
            }
        }
    }

    @Nested
    @DisplayName("remove")
    class RemoveTest {

        @Test
        @DisplayName("기본 삭제")
        void basicRemove() {
            map.put("one", 1);
            map.put("two", 2);

            assertEquals(1, map.remove("one"));
            assertNull(map.get("one"));
            assertEquals(2, map.get("two"));
        }

        @Test
        @DisplayName("없는 key 삭제시 null 반환")
        void removeNonExistent() {
            assertNull(map.remove("notExist"));
        }

        @Test
        @DisplayName("첫 번째 노드 삭제")
        void removeFirstNode() {
            map.put("one", 1);

            assertEquals(1, map.remove("one"));
            assertNull(map.get("one"));
        }

        @Test
        @DisplayName("중간 노드 삭제")
        void removeMiddleNode() {
            // 여러 개 넣어서 연결 리스트 만들기
            for (int i = 0; i < 20; i++) {
                map.put("key" + i, i);
            }

            assertEquals(10, map.remove("key10"));
            assertNull(map.get("key10"));

            // 다른 값들은 여전히 존재
            assertEquals(5, map.get("key5"));
            assertEquals(15, map.get("key15"));
        }
    }

    @Nested
    @DisplayName("containsKey")
    class ContainsKeyTest {

        @Test
        @DisplayName("존재하는 key")
        void existingKey() {
            map.put("one", 1);

            assertTrue(map.containsKey("one"));
        }

        @Test
        @DisplayName("존재하지 않는 key")
        void nonExistingKey() {
            assertFalse(map.containsKey("notExist"));
        }
    }

    @Nested
    @DisplayName("size")
    class SizeTest {

        @Test
        @DisplayName("빈 map의 size는 0")
        void emptyMapSize() {
            assertEquals(0, map.size());
        }

        @Test
        @DisplayName("추가 후 size 증가")
        void sizeAfterPut() {
            map.put("one", 1);
            map.put("two", 2);

            assertEquals(2, map.size());
        }

        @Test
        @DisplayName("중복 key는 size 증가 안함")
        void sizeWithDuplicateKey() {
            map.put("one", 1);
            map.put("one", 100);

            assertEquals(1, map.size());
        }

        @Test
        @DisplayName("삭제 후 size 감소")
        void sizeAfterRemove() {
            map.put("one", 1);
            map.put("two", 2);
            map.remove("one");

            assertEquals(1, map.size());
        }
    }

    @Nested
    @DisplayName("resize")
    class ResizeTest {

        @Test
        @DisplayName("resize 후에도 모든 값 유지")
        void valuesPreservedAfterResize() {
            for (int i = 0; i < 30; i++) {
                map.put("key" + i, i);
            }

            int sizeBefore = map.size();
            map.resize();

            assertEquals(sizeBefore, map.size());
            for (int i = 0; i < 30; i++) {
                assertEquals(i, map.get("key" + i));
            }
        }
    }
}
