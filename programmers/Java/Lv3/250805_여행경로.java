/*
[문제] 여행경로 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/43164

[접근]
- DFS + 백트래킹을 통해 가능한 모든 경로를 탐색
- 모든 티켓을 한 번씩 사용하며, 가능한 경로 중 사전순으로 가장 빠른 경로를 찾아야 함
- tickets 배열을 출발지 - 도착지 기준으로 사전순 정렬하여, DFS에서 가장 먼저 찾은 경로가 정답이 되도록 설계
- 경로는 문자열로 누적, 최종적으로 answer 리스트에 추가하고 split하여 반환

[성능]
- 시간복잡도: O(N!) (최악의 경우 모든 티켓 순열을 탐색)

[배운 점]
- DFS 백트래킹을 이용한 경로 찾기 문제의 패턴 이해
- 티켓처럼 중복 경로가 존재할 수 있는 경우, 방문 배열을 활용해 중복 사용 방지
- 사전순 정렬 후 DFS 탐색 시, 가장 먼저 찾은 경로가 사전순으로 가장 앞선 경로가 된다는 점
*/

import java.util.*;

class Solution {
    List<String> answer = new ArrayList<>();
    boolean[] visited;

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];

        // 티켓을 사전순으로 정렬
        Arrays.sort(tickets, (a, b) -> a[0].equals(b[0]) 
                ? a[1].compareTo(b[1]) 
                : a[0].compareTo(b[0]));

        dfs("ICN", "ICN", tickets, 0);

        return answer.get(0).split(" ");
    }

    void dfs(String current, String route, String[][] tickets, int used) {
        if (used == tickets.length) {
            answer.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, used + 1);
                visited[i] = false; // 백트래킹
            }
        }
    }
}
