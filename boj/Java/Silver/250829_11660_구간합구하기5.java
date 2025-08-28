import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();

    // 1-based 인덱싱을 위해 N+1
    int[][] S = new int[N + 1][N + 1];

    // 누적합 전처리: S[i][j] = (1,1) ~ (i,j) 합
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        int val = sc.nextInt();
        S[i][j] = S[i - 1][j] + S[i][j - 1] - S[i - 1][j - 1] + val;
      }
    }

    // M번 합 구하기
    for (int i = 0; i < M; i++) {
      int x1 = sc.nextInt();
      int y1 = sc.nextInt();
      int x2 = sc.nextInt();
      int y2 = sc.nextInt();
      int result = S[x2][y2] - S[x1 - 1][y2] - S[x2][y1 - 1] + S[x1 - 1][y1 - 1];
      System.out.println(result);
    }
  }
}
