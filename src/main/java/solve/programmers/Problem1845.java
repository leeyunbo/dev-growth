package solve.programmers;

import java.util.HashSet;
import java.util.Set;

/**
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/1845
 * 문제명 : 폰켓몬
 * 풀이방법 : HashSet을 이용하여 중복된 폰켓몬을 제거하면, 폰켓몬의 종류 개수를 구할 수 있다.
 * 폰켓몬의 종류 개수와 가져갈 수 있는 폰켓몬의 수(n) 중 작은 값을 반환하면 최대로 가져갈 수 있는 폰켓몬의 종류 개수를 구할 수 있다.
 */

public class Problem1845 {

    public int solution(int[] nums) {
        int n = nums.length / 2;
        Set<Integer> pokemonSet = new HashSet<>(nums.length);
        for (int num : nums) {
            pokemonSet.add(num);
        }

        return Math.min(pokemonSet.size(), n);
    }
}
