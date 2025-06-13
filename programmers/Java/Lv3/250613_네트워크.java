/*
[문제] 네트워크 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/43162

[접근]
- 컴퓨터들의 연결 상태를 인접 리스트 형태의 그래프로 변환
- 방문하지 않은 노드에 대해 DFS를 수행하며 네트워크(연결된 컴포넌트) 수를 카운트
- 간선 정보가 주어지는 computers를 기반으로 무방향 그래프 구성

[성능]
- 시간복잡도: O(N^2) (모든 노드 간 연결을 탐색함)
- 공간복잡도: O(N + E) (그래프 및 방문 배열)

[배운 점]
- 인접 행렬을 인접 리스트로 변환하는 방법
- DFS로 연결 요소 개수 세는 방식
- Java에서 List<Integer>[] 배열 선언 및 초기화 방식
*/

import java.util.*;

class Solution {
    static boolean[] visited;
    static List<Integer>[] graph;

    public int solution(int n, int[][] computers) {
        graph = new ArrayList[n];
        visited = new boolean[n];

        // 인접 리스트 초기화
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 인접 행렬을 인접 리스트로 변환
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if (computers[i][j] == 1 && i != j) {
                    graph[i].add(j);
                }
            }
        }

        int answer = 0;
        // 방문하지 않은 노드에 대해 DFS 수행
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                answer++; // 하나의 네트워크(연결 요소) 완성
            }
        }

        return answer;
    }

    static void dfs(int node) {
        visited[node] = true;
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}
