package solve.programmers;

/**
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42586
 * 문제명: 기능개발
 * 풀이방법: 큐를 이용한 시뮬레이션 풀이 O(n)
 * 1. 각 작업의 남은 작업 일수를 계산하여 큐에 저장
 * 2. 큐에서 작업을 하나씩 꺼내어 첫 번째 작업의 남은 작업 일수를 기준으로
 *    뒤따르는 작업들의 남은 작업 일수가 더 작거나 같은 경우 함께 배포
 * 3. 각 배포마다 배포된 작업 수를 리스트에 저장 후 배열로 변환하여 반환
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem42586 {

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> deployCountList = new ArrayList<>();
        Queue<Task> tasks = IntStream.range(0, progresses.length)
                .mapToObj(i -> new Task(progresses[i], speeds[i]))
                .collect(Collectors.toCollection(ArrayDeque::new));

        while (!tasks.isEmpty()) {
            int deployCount = 1;
            int firstDaysToComplete = tasks.poll().getDaysToComplete();

            while (!tasks.isEmpty() &&
                    tasks.peek().getDaysToComplete() <= firstDaysToComplete) {
                tasks.poll();
                deployCount++;
            }
            deployCountList.add(deployCount);
        }

        return deployCountList.stream().mapToInt(Integer::intValue).toArray();
    }

    private static class Task {

        private final Integer progress;
        private final Integer speed;

        public Task(Integer progress, Integer speed) {
            this.progress = progress;
            this.speed = speed;
        }

        public int getDaysToComplete() {
            return (int) Math.ceil((100.0 - progress) / speed);
        }
    }
}
