package solve.programmers;

import java.util.*;

/**
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42579
 * 문제명 : 베스트앨범
 * 풀이방법 : 해시맵과 정렬을 이용한 풀이
 * 1. 장르별 재생 횟수를 해시맵에 저장
 * 2. 장르별 재생 횟수를 기준으로 정렬
 * 3. 장르별로 노래를 재생 횟수 기준으로 정렬하여 상위 2곡 선택
 */

public class Problem42579 {

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genrePlayCount = new HashMap<>();
        Map<String, TreeSet<Music>> genreMusicMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            genrePlayCount.merge(genre, play, Integer::sum);
            genreMusicMap.computeIfAbsent(genre, k -> new TreeSet<>())
                    .add(new Music(i, play));
        }

        List<String> sortedGenres = new ArrayList<>(genrePlayCount.keySet());
        sortedGenres.sort((a, b) -> genrePlayCount.get(b) - genrePlayCount.get(a));

        List<Integer> result = new ArrayList<>();
        for (String genre : sortedGenres) {
            int count = 0;
            for (Music music : genreMusicMap.get(genre)) {
                result.add(music.id);
                if (++count == 2) {
                    break;
                }
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private static class Music implements Comparable<Music> {
        private final Integer id;
        private final Integer play;

        private Music(Integer id, Integer play) {
            this.id = id;
            this.play = play;
        }

        @Override
        public int compareTo(Music o) {
            if (this.play > o.play) {
                return -1;
            }
            if (this.play < o.play) {
                return 1;
            }
            return this.id.compareTo(o.id);
        }
    }
}
