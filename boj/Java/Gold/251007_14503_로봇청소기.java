import java.util.*;
 
public class Main {    
    
    static int n, m, r, c, d;
    static int[][] board;
    static int cnt = 1; // 시작 지점 청소
    
    // 북, 동, 남, 서
    static int[] dx = {-1, 0, 1, 0}; 
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        m = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        d = sc.nextInt();
        
        board = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        
        dfs(r, c, d);
        System.out.println(cnt);
    }    
    
    public static void dfs(int x, int y, int dir) {
        board[x][y] = 2; // 청소
        
        for(int i = 0; i < 4; i++) {
            dir -= 1; 
            if(dir == -1) dir = 3;
            
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if(board[nx][ny] == 0) { // 벽 X, 이미 청소한 곳 X -> 청소하러 이동
                    cnt++;
                    dfs(nx, ny, dir);
                    return;
                }
            }
        }
        
        int d = (dir + 2) % 4; // 반대 방향
        int xx = x + dx[d];
        int yy = y + dy[d];
        if(xx >= 0 && yy >= 0 && xx < n && yy < m && board[xx][yy] != 1) {
            dfs(xx, yy, dir); 
        }
    }
}
