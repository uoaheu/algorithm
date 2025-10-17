/*
[문제] 미로 탈출 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/159993

[접근]
- 레버를 당겨야 출구로 나갈 수 있음
- (1) S -> L 최단거리 + (2) L -> E 최단거리
- 둘 중 하나라도 도달 불가면 -1

[성능]
- 시간 복잡도: O(N*M) 

[배운 점]
- 상태 전환(레버 전/후)을 간단히 처리하려면 상태를 분리(S-L, L-E)해서 bfs 2번 돌리는 방식이 깔끔함
*/

import java.util.*;
class Solution {
    static int n, m, startX, startY, endX, endY, leverX, leverY;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    
    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        arr = new int[n][m];
        startX = 0;
        startY = 0;
        endX = 0;
        endY = 0;
        leverX = 0;
        leverY = 0;
        for(int i = 0; i < n; i++) { // 행 
            for(int j = 0; j < m; j++) { // 열 : 글자 길이 
                char c = maps[i].charAt(j);
                if(c == 'S') {
                    arr[i][j] = 0; 
                    startX = i;
                    startY = j;
                } else if(c == 'E') {
                    arr[i][j] = 0; // 출구
                    endX = i;
                    endY = j;
                } else if(c == 'L') {
                    arr[i][j] = 0; // 레버
                    leverX = i;
                    leverY = j;
                } else if(c == 'O') {
                    arr[i][j] = 0; // 통로
                } else {
                    arr[i][j] = 1; // 벽
                }
            }
        }
                
        // 1. S -> L 최단거리
        int d1 = bfs(startX, startY, leverX, leverY);
        if (d1 == -1) return -1;

        // 2. L -> E 최단거리
        int d2 = bfs(leverX, leverY, endX, endY);
        if (d2 == -1) return -1;

        return d1 + d2;
    }
    
    static int bfs(int x, int y, int xx, int yy) {
        visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y, 0}); // 위치 + 거리 
        visited[x][y] = true;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];
            int dist = curr[2];

            if(cx == xx && cy == yy) {
                return dist;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || arr[nx][ny] == 1) continue;
                
                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny, dist + 1});
            }    
        }
        return -1;
    }
}
