import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[][] arr;
    static int[][] check;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, -0, 1, -1};
    static int N, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N][M];
        check = new int[N][M];
        
        // 미로 입력 받기
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        
        System.out.println(bfs(0, 0));
    }
    
    public static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        
        check[x][y] = 1;
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];
            
            // 목적지 도달
            if (cx == N - 1 && cy == M - 1) break;
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                // 미로 범위 내에 있는지 확인
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (arr[nx][ny] == 1 && check[nx][ny] == 0) {
                        check[nx][ny] = check[cx][cy] + 1;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
        
        return check[N-1][M-1];
    }
}
