import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // StringBuilder sb = new StringBuilder();

    int N = sc.nextInt(); // 삼각형의 크기 n
    int result = 0;
    int[][] T = new int[N][N]; // 삼각형 배열

    int first = sc.nextInt(); // 맨 꼭대기 값
    T[0][0] = first;

    for (int i = 1; i < N; i++) {
      // 각 층마다 존재하는 값만큼 입력받기
      for (int j = 0; j < i + 1; j++) {
        int num = sc.nextInt();
        // 맨 처음 값인 경우
        if (j == 0) {
          T[i][j] = T[i - 1][j] + num;
        } else if (j == i) { // 맨 끝 값인 경우
          T[i][j] = T[i - 1][j - 1] + num;
        } else {
          T[i][j] = Math.max(T[i - 1][j - 1], T[i - 1][j]) + num;
        }
      }
    }
    // 가장 큰 값 구하기
    for (int i = 0; i < N; i++) {
      result = Math.max(result, T[N - 1][i]);
    }

    System.out.println(result);
  }
}
