import java.util.*;

public class Main {  
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr;
    static int n, m;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        int count = 0;
        int max = 0;

        // 각자 방에서 갈 수 있는 값 확인
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(arr[i][j] == 1) {
                    int sum = check(i, j);
                    count++;
                    if(max < sum) {
                        max = sum;
                    }
                }
            }
        }
        System.out.println(count);
        System.out.println(max);
    }
    
    static int check(int x, int y) {
        int xx = x;
        int yy = y;
        arr[xx][yy] = 0; // 방문 체크
        int size = 1;
        for(int i = 0; i < 4; i++) {
            int cx = xx + dx[i];
            int cy = yy + dy[i];
            if(cx >= 0 && cy >= 0 && cx < n && cy < m && arr[cx][cy] != 0) {
                arr[cx][cy] = 0;
                size += check(cx, cy);
            }
        }
        return size;
    }
}
