/*
[문제] 아이템 줍기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/87694

[접근]
- 사각형의 테두리만 따라 움직일 수 있으므로, 좌표를 2배 스케일링하여 정밀하게 처리
- 모든 사각형 영역을 먼저 1로 칠한 후, 내부 영역을 0으로 덮어 테두리만 남긴다
- 캐릭터 시작점에서 아이템까지 BFS로 최단 경로를 탐색

[성능]
- 시간 복잡도: O(N * M) (N: 좌표 범위 102x102, M: BFS 탐색 시간)

[배운 점]
- 스케일링을 통해 테두리/내부 구분을 명확히 할 수 있다는 점
- 2차원 평면 위에서 경계만 따라 움직이는 문제는 내부 제거 + 테두리 유지로 해결 가능
- BFS 탐색 시 visited 체크와 조건 순서가 중요
*/

import java.util.*;

class Solution {
    static int[][] map = new int[102][102]; // 2배 스케일링
    static boolean[][] visited = new boolean[102][102];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 1. 전체 채우기
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2, y1 = rect[1] * 2;
            int x2 = rect[2] * 2, y2 = rect[3] * 2;

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    map[i][j] = 1;
                }
            }
        }

        // 2. 내부 제거
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2 + 1, y1 = rect[1] * 2 + 1;
            int x2 = rect[2] * 2 - 1, y2 = rect[3] * 2 - 1;

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    map[i][j] = 0;
                }
            }
        }

        // 3. BFS
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
    }

    private int bfs(int sx, int sy, int ex, int ey) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dist = cur[2];

            if (x == ex && y == ey) return dist;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= 102 || ny >= 102) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] != 1) continue;

                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny, dist + 1});
            }
        }

        return -1;
    }
}
