/*
[문제] 단어 변환 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/43163

[접근]
- 최단 경로를 찾는 문제로 BFS 사용
- 각 단어를 그래프의 노드로 보고, 한 글자만 다른 단어들을 연결된 간선으로 간주
- BFS를 통해 begin에서 target까지 갈 수 있는 최소 단계를 찾음
- 큐에 단어와 함께 해당 단어까지 도달하는 데 걸린 변환 횟수(단계)를 함께 저장해서 관리

[성능]
- 시간 복잡도: O(V+E) -> V는 words 배열의 길이(노드의 개수), E는 간선의 개수
- 공간복잡도: O(N)

[배운 점]
- BFS 탐색 시 노드(단어)뿐만 아니라 해당 노드까지의 추가적인 정보(단계)를 함께 관리해야 할 때, 커스텀 클래스(WordInfo)를 정의하여 큐에 담는 방법이 유용함을 느낌
- visited 배열을 사용하여 무한 루프 방지 및 최단 경로 보장
*/

import java.util.*;

class Solution {

    // BFS 시 (단어/현재까지의 변환 횟수) 함께 저장할 클래스
    static class WordInfo {
        String word;
        int step;

        public WordInfo(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }

    public int solution(String begin, String target, String[] words) {
        // 1. target 단어가 words 배열에 없는 경우 변환 불가능
        boolean targetExists = false;
        for (String w : words) {
            if (w.equals(target)) {
                targetExists = true;
                break;
            }
        }
        
        if (!targetExists) {
            return 0;
        }

        // 2. words 배열의 인덱스와 매칭
        Queue<WordInfo> q = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        q.offer(new WordInfo(begin, 0)); // (시작 단어, 0단계)

        while (!q.isEmpty()) {
            WordInfo current = q.poll(); // 큐에서 현재 단어 정보 꺼내기

            // 현재 단어가 target과 같다면 변환 횟수 반환
            if (current.word.equals(target)) {
                return current.step;
            }

            // words 배열의 모든 단어 탐색
            for (int i = 0; i < words.length; i++) {
                // 이미 방문한 단어 건너뛰기
                if (visited[i]) {
                    continue;
                }

                String nextWord = words[i];
                int diffCount = 0; // 현재 단어와 다음 단어의 다른 글자 수

                // 두 단어의 글자 수를 비교하여 다른 글자 개수 세기
                for (int j = 0; j < current.word.length(); j++) {
                    if (current.word.charAt(j) != nextWord.charAt(j)) {
                        diffCount++;
                    }
                }

                // 한 글자만 다르고 아직 방문하지 않은 단어라면 큐에 추가
                if (diffCount == 1) {
                    visited[i] = true; // 방문 처리
                    q.offer(new WordInfo(nextWord, current.step + 1)); // 다음 단계로 큐에 추가
                }
            }
        }

        // 큐가 비었는데도 target에 도달하지 못했다면 변환 불가능
        return 0;
    }
}
