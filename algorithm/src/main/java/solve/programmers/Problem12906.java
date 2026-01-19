package solve.programmers;

import java.util.ArrayList;
import java.util.List;

/**
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/12906
 * 문제명 : 같은 숫자는 싫어
 * 풀이방법 : 리스트에 이전에 추가한 숫자와 현재 숫자를 비교하여 다를 경우에만 추가하는 방식으로 중복된 숫자를 제거
 */

public class Problem12906 {

    public int[] solution(int[] arr) {
        List<Integer> list = new ArrayList<>();

        for (int num : arr) {
            if (!list.isEmpty() &&
                    list.get(list.size() - 1) == num) {
                continue;
            }

            list.add(num);
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
