import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();
    int N = sc.nextInt(); //  N * N 값
    int K = sc.nextInt(); // 사과 수
    boolean[][] arr = new boolean[N][N]; // True -> 사과
    Deque<int[]> d = new ArrayDeque<>(); // 뱀 위치 저장

    // 우 하 좌 상 (오른쪽부터 시작)
    int[] dx = {0, 1, 0, -1}; 
    int[] dy = {1, 0, -1, 0};
    
    // 사과 위치
    for(int i = 0; i < K; i++) {
      int appleX = sc.nextInt();
      int appleY = sc.nextInt();
      arr[appleX - 1][appleY - 1] = true;
    }

    // 뱀 시작
    int x = 0;
    int y = 0;
    
    // 뱀 방향
    int idx = 0;

    // 결과
    int result = 1;
    d.add(new int[] {x, y});
    int L = sc.nextInt(); // 뱀 방향 변환 횟수
    
    // 입력 미리 받기
    String[][] arrInfo = new String[L+1][2];
    
    for(int i = 0; i < L; i++) {
      arrInfo[i][0] = sc.next(); // 초
      arrInfo[i][1] = sc.next(); // 방향 정보
    }
    arrInfo[L][0] = String.valueOf(N + arrInfo[L-1][0]); 
    arrInfo[L][1] = "";

    int start = 0;
    out : for(int i = 0; i <= L; i++) {
      int s = Integer.parseInt(arrInfo[i][0]);
      String info = arrInfo[i][1];

      for(int j = start; j < s; j++) {
        int nx = x + dx[idx];
        int ny = y + dy[idx];

        // 끝나는 시점 : 벽인지 / 나 자신 만났는지
        if((nx < 0 || ny < 0 || nx >= N || ny >= N) || (d.stream().anyMatch(dd -> dd[0] == nx && dd[1] == ny))) {
          break out;
        }

        d.add(new int[] {nx, ny});
        result++;

        // 사과 있는지 확인, 없으면 덱에서 길이 줄이기
        if(arr[nx][ny]) {
          arr[nx][ny] = false; // 사과 먹어버리기
        } else {
          d.pollFirst();
        }

        x = nx;
        y = ny;

      }
      start = s;
      
      // 방향 바꾸기
      if(info.equals("D")) {
        if(idx == 3) {
          idx = 0;
        } else {
          idx++;
        }
      } else {
        if(idx == 0) {
          idx = 3;
        } else {
          idx--;
        }
      }
    }

    System.out.print(result);
  }
}
