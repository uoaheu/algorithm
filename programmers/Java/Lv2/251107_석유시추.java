/*
[문제] [PCCP 기출문제] 2번 / 석유 시추 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/250136

[접근]
- 4방향으로 연결된 1들을 하나의 덩어리로 보고 BFS로 탐색
- 각 덩어리 탐색 중 해당 덩어리가 닿은 열들을 집합으로 모으고, 덩어리 크기 계산
- 탐색이 끝나면 그 덩어리가 닿은 모든 열에 대해 열 합계에 총합을 한 번씩 더하기

[성능]
- 시간 복잡도: O(N*M)

[배운 점]
- 덩어리 단위로 '닿은 열 집합'을 이용해 중복 누적 방지
- 큐에 넣을 때 즉시 visited 표시로 중복 방문 막기
*/

import java.util.*;
class Solution {
    public int solution(int[][] land) {
        int n = land.length; // 세로
        int m = land[0].length; // 가로
        int[] arr = new int[m];
        boolean[][] visited = new boolean[n][m];
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        
        for(int j = 0; j < m; j++) {
            for(int i = 0; i < n; i++) {
                if(land[i][j] == 0 || visited[i][j]) continue;
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[] {i, j});
                visited[i][j] = true;
                int sum = 0; // 누적합
                HashSet<Integer> touchedCols = new HashSet<>();
                
                while(!q.isEmpty()) {
                    int[] curr = q.poll();
                    int x = curr[0];
                    int y = curr[1];
                    sum++;
                    touchedCols.add(y);
                    
                    for(int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || land[nx][ny] == 0) continue;
                        visited[nx][ny] = true;
                        q.offer(new int[] {nx, ny});
                    }
                }
                
                for (int col : touchedCols) {
                    arr[col] += sum;
                }
                
            }
        }
        int answer = 0;
        for(int a : arr) {
            answer = Math.max(answer, a);
        }
        return answer;
    }
}
