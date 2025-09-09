import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long A = sc.nextLong();
    long B = sc.nextLong();

    int steps = 1; // 시작 상태 포함

    while (A < B) {
      if (B % 10 == 1) { // 끝자리가 1이면 1 제거
        B /= 10;
        steps++;
      } else if (B % 2 == 0) { // 짝수면 2로 나눔
        B /= 2;
        steps++;
      } else { // 둘 다 아니면 불가능
        System.out.println(-1);
        return;
      }
    }

    System.out.println(A == B ? steps : -1);
  }
}
