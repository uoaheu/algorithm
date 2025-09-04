import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    Queue<Integer> q = new LinkedList<>();
    
    // 덱에 N까지 값 넣기
    for(int i = 1; i <= N; i++) {
      q.add(i);
    }

    // 1장 남을때까지
    while(q.size() > 1) {
      int a = q.poll();
      q.add(q.poll());
    }

    System.out.print(q.poll());
  }
}
