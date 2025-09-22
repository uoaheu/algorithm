import java.util.*;

public class Solution {  
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr;
    static int N;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            
            N = sc.nextInt();
            arr = new int[N][N];
            int max = 0;
            int num = N*N;
            
            // 방 번호 입력받기 
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            // 각자 방에서 갈 수 있는 값 확인
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    int count = check(i, j);
                    if(count > max) {
                        max = count;
                        num = arr[i][j];
                    } else if(count == max && num > arr[i][j]) {
                        num = arr[i][j];
                    }
                }
            }
            System.out.println("#" + tc + " " + num + " "+ max);
        }

    }
    static int check(int x, int y) {
        int xx = x;
        int yy = y;
        for(int i = 0; i < 4; i++) {
            int cx = xx + dx[i];
            int cy = yy + dy[i];
            if(cx >= 0 && cy >= 0 && cx < N && cy < N && arr[cx][cy] == arr[xx][yy] + 1) {
                return 1 + check(cx, cy);
            }
        }
        return 1;
    }
}
