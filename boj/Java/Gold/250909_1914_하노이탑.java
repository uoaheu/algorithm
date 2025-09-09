import java.math.BigInteger;
import java.util.*;

public class Main {
  static int N;
  static StringBuilder sb = new StringBuilder();

  static void func(int n, int start, int end, int temp) {
    if( n == 1) {
      // System.out.println(start + " " + end);
      sb.append(start).append(" ").append(end).append("\n");
      return;
    }
    func(n-1, start, temp, end);
    // System.out.println(start + " " + end);
    sb.append(start).append(" ").append(end).append("\n");

    func(n-1, temp, end, start);

  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    // sb.append((int)Math.pow(2, N)- 1).append("\n");
    BigInteger moves = BigInteger.valueOf(2).pow(N).subtract(BigInteger.ONE);
    System.out.println(moves); // 이동 횟수

    if(N <= 20) {
      func(N, 1, 3, 2);
      System.out.println(sb.toString());
    }
  }
}
