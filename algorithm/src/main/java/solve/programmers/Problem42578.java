package solve.programmers;

import java.util.HashMap;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42578
 * 문제명 : 의상
 * 조합의 수 = (각 의상 종류별 선택 가능한 개수 + 1(안 입는 경우))의 곱 - 1(모두 안 입는 경우)
 * HashMap을 활용하여 O(n)의 시간복잡도로 해결
 */

public class Problem42578 {

    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String[] cloth : clothes) {
            String type = cloth[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }

        int result = 1;
        for (String type : map.keySet()) {
            result *= (map.get(type) + 1);
        }
        return result - 1;
    }
}
