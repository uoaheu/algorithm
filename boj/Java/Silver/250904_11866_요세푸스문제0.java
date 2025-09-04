import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int K = sc.nextInt();
    Queue<Integer> q = new LinkedList<>();
    StringBuilder sb = new StringBuilder();
    sb.append('<');

    // 큐에 N까지 값 넣기
    for(int i = 1; i <= N; i++) {
      q.add(i);
    }
    int idx = 0; // 현재 위치
    while(!q.isEmpty()) {
      idx++;

      if(idx == K) {
        int num = q.poll();
        sb.append(num);
        if(!q.isEmpty()) {
          sb.append(", ");
        }
        idx = 0;
      } else {
        q.add(q.poll());
      }
    }
    
    sb.append('>');
    System.out.println(sb.toString());
  }
}
