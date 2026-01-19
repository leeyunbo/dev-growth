package solve.programmers;

import java.util.Arrays;
import java.util.HashSet;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42577
 * 문제명 : 전화번호 목록
 * HashSet의 contains()는 O(1)의 시간복잡도를 가진다.
 * O(n * m^2)
 */

public class Problem42577 {

    public boolean solution(String[] phone_book) {
        HashSet<String> set = new HashSet<>(Arrays.asList(phone_book));
        for (String phone : phone_book) {
            for (int i = 0; i < phone.length(); i++) {
                String prefix = phone.substring(0, i);
                if (set.contains(prefix)) {
                    return false;
                }
            }
        }

        return true;
    }
}
