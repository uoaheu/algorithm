import java.util.*;

public class Main {  
  static int T, N;
  static int[][] arr, sum; // 배열 저장 
  static boolean[][] visited;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) {
    // 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
    Scanner sc = new Scanner(System.in);
    T = sc.nextInt();
    for(int tc = 0; tc < T; tc++) {
      N = sc.nextInt();
      arr = new int[2][N];
      // sum = new int[N][2];
      visited = new boolean[N][2];

      for(int i = 0; i < 2; i++) {
        for(int j = 0; j < N; j++) {
          arr[i][j] = sc.nextInt();
        }
      }
      if(N >= 2) {
        // 2열은 1열의 대각선 값만 저장
        arr[0][1] += arr[1][0]; 
        arr[1][1] += arr[0][0];
      }

      for (int j = 2; j < N; j++) {
        arr[0][j] = Math.max(Math.max(arr[1][j-1], arr[0][j-2]), arr[1][j-2]) + arr[0][j];
        arr[1][j] = Math.max(Math.max(arr[0][j-1], arr[0][j-2]), arr[1][j-2]) + arr[1][j];
      }


      int result = Math.max(arr[1][N-1], arr[0][N-1]);
      System.out.println(result);

    }
  }
}
