package programmers;

import java.util.HashMap;
import java.util.Map;

/**
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42576
 * 문제명 : 완주하지 못한 선수
 * 풀이방법 : 해시, 정렬을 이용한 풀이 방법은 O(n log n)의 시간복잡도를 가지나, 해시맵을 이용한 방법은 O(n)의 시간복잡도를 가진다.
 * 두 개의 배열에 대한 차집합을 구하는 문제로, 동명이인이 존재하기 때문에 이름과 개수를 함께 저장할 수 있는 해시맵이 필요하다.
 *
 */

public class Problem42576 {

    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> countMap = new HashMap<>(participant.length);

        for (String p : participant) {
            countMap.put(p, countMap.merge(p, 1, Integer::sum));
        }

        for (String c : completion) {
            int count = countMap.get(c) - 1;
            if (count == 0) countMap.remove(c);
            else countMap.put(c, count);
        }

        return countMap.keySet().iterator().next();
    }
}
