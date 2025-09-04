import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    
    for(int i = 0; i < T; i++) {
      int N = sc.nextInt();
      StringBuilder sb = new StringBuilder();
      Deque<Character> d = new ArrayDeque<>(); // char로 헤서 값 비교하려고
      d.add(sc.next().charAt(0)); // 첫번째 값은 바로 덱에 넣기
        
      for(int j = 1; j < N; j++) { // 두번째 값부터 비교하면서 넣기
        Character c = sc.next().charAt(0);
        if(d.peekFirst() >= c) {
          d.addFirst(c);
        } else {
          d.addLast(c);
        }
      }

      while(!d.isEmpty()) {
        sb.append(d.pollFirst());
      }
      System.out.println(sb.toString());
    }
  }
}
