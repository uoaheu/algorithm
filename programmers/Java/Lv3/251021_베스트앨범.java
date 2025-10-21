/*
[문제] 베스트앨범 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/42579

[접근]
- 장르별로 총 재생 수를 합산하고, 각 장르 안에 속한 곡들의 (재생 수, 인덱스)를 함께 저장
- 장르 전체는 총 재생 수 기준으로 내림차순 정렬
- 각 장르 내부는 (재생 수 내림차순, 재생 수 같으면 인덱스 오름차순)으로 정렬하여 상위 2곡만 선택

[성능]
- 시간복잡도: O(N log N)

[배운 점]
- HashMap을 사용해 그룹별 누적 합과 세부 리스트를 동시 관리
- Comparator를 이용해 복수 조건(재생 수, 인덱스 순) 간단히 정의
*/

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {

        // 장르별 총 재생 수, 곡 리스트 저장
        Map<String, Integer> totalByGenre = new HashMap<>();
        Map<String, List<int[]>> songsByGenre = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String g = genres[i];
            int p = plays[i];

            // 장르별 총 재생 수 누적
            totalByGenre.put(g, totalByGenre.getOrDefault(g, 0) + p);

            // 장르별 곡 리스트 초기화 및 추가
            if (!songsByGenre.containsKey(g)) {
                songsByGenre.put(g, new ArrayList<>());
            }
            songsByGenre.get(g).add(new int[]{p, i}); // [재생수, 인덱스]
        }

        // 장르를 총 재생 수 내림차순으로 정렬
        List<String> sortedGenres = new ArrayList<>(totalByGenre.keySet());        
        Collections.sort(sortedGenres, (a, b) -> totalByGenre.get(b) - totalByGenre.get(a));

        // 각 장르에서 상위 2곡 뽑기
        List<Integer> answerList = new ArrayList<>();

        for (String g : sortedGenres) {
            List<int[]> list = songsByGenre.get(g);

            // 재생수 내림차순, 같으면 인덱스 오름차순
            Collections.sort(list, (x, y) -> {
                if (y[0] != x[0]) return y[0] - x[0];
                return x[1] - y[1];
            });

            // 상위 2곡만 추가
            for (int i = 0; i < list.size() && i < 2; i++) {
                answerList.add(list.get(i)[1]); // 인덱스만 저장
            }
        }

        // 리스트 -> 배열 변환
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}
