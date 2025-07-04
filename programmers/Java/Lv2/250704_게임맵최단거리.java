/*
[문제] 게임 맵 최단거리 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/1844

[접근]
- BFS(너비 우선 탐색)를 사용하여 시작 지점에서부터 최단 거리로 각 칸에 도달하는 경로 탐색
- 큐에 현재 위치(x, y)와 이동 거리를 함께 저장하고, 방문한 칸은 visited 배열로 표시하여 중복 방문 체크
- 목적지에 처음 도달했을 때의 거리가 최단 거리

[성능]
- 시간복잡도: O(N×M)

[배운 점]
- BFS의 최단거리 보장: BFS는 시작점에서부터 거리가 1인 노드들을 먼저 탐색하고, 그다음 거리가 2인 노드들을 탐색하는 식으로 '레벨'별로 확장해 나감 -> 목적지에 가장 먼저 도달하는 경로가 항상 최단 거리임을 보장
- visited 배열을 통해 무한 루프를 방지하고 불필요한 재탐색을 막아 효율성 확보
*/

import java.util.*;

class Solution {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        return bfs(0, 0, maps, n, m);
    }

    static int bfs(int x, int y, int[][] maps, int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        q.add(new int[]{x, y, 1}); // x, y 좌표 + 이동 거리 (시작점 1)
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int xx = curr[0];
            int yy = curr[1];
            int cnt = curr[2]; // 현재까지의 이동 거리

            // 도착점 도달 시 반환
            if (xx == n - 1 && yy == m - 1) {
                return cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = xx + dx[i];
                int ny = yy + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, cnt + 1}); // 이동 거리 증가하여 저장
                }
            }
        }

        return -1; // 도착할 수 없는 경우
    }
}
