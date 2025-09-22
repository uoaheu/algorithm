import java.util.*;

public class Main {  
    static int[][] arr;
    static int M, N, K;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        M = sc.nextInt(); // 행 
        N = sc.nextInt(); // 열 
        K = sc.nextInt();
        arr = new int[M][N];
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 값 저장
        Queue<int[]> q = new LinkedList<>();

        // x : 열, y : 행
        for(int i = 0; i < K; i++) {
            int startX = sc.nextInt();
            int startY = sc.nextInt();
            int endX = sc.nextInt();
            int endY = sc.nextInt();

            for(int y = startY; y < endY; y++) {
                for(int x = startX; x < endX; x++) {
                    arr[y][x] = -1;
                }
            }
        }

        for(int i = 0; i < M; i++) { // 행 
            for(int j = 0; j < N; j++) { // 열 
                
                // 0 이면 빈공간
                if(arr[i][j] == 0) {
                    q.offer(new int[] {i, j});
                    int cnt = 0;
                    arr[i][j] = 1; // 방문 표시 
                    
                    while(!q.isEmpty()) {
                        int[] curr = q.poll();
                        cnt++;
                        int r = curr[0];
                        int c = curr[1];

                        for(int k = 0; k < 4; k++) {
                            int cy = r + dy[k];
                            int cx = c + dx[k];
                            // 다음 조건일 때 공간 확인
                            if(cx >= 0 && cy >= 0 && cx < N && cy < M && arr[cy][cx] == 0) {
                                arr[cy][cx] = 1;
                                q.offer(new int[] {cy, cx});
                            }
                        }
                    }
                    pq.offer(cnt);
                }

            }
        }

        sb.append(pq.size()).append("\n");
        while(!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }
        System.out.println(sb.toString());
    }
}
