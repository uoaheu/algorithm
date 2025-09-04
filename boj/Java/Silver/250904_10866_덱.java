import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    sc.nextLine(); 

    Deque<Integer> d = new ArrayDeque<>();
    StringBuilder sb = new StringBuilder();

    for(int i = 0; i < N; i++) {
      String str = sc.nextLine();
      // 공백을 기준으로 나누기
      String[] parts = str.split(" ");
      if(parts[0].equals("push_front")) { // 정수 X, 덱 앞에 넣기
        d.addFirst(Integer.parseInt(parts[1]));
      } else if(parts[0].equals("push_back")) { // 정수 X, 덱 뒤에 넣기
        d.addLast(Integer.parseInt(parts[1]));
      } else if(parts[0].equals("pop_front")) { // 맨 앞 빼서 출력
        if(!d.isEmpty()) {
          sb.append(d.removeFirst()).append('\n');
        } else {
          sb.append("-1").append('\n');
        }
      } else if(parts[0].equals("pop_back")) { // 맨 뒤 빼서 출력
        if(!d.isEmpty()) {
          sb.append(d.removeLast()).append('\n');
        } else {
          sb.append("-1").append('\n');
        }
      } else if(parts[0].equals("size")) { // 덱 길이
        sb.append(d.size()).append('\n');
      } else if(parts[0].equals("empty")) { // 덱 비었는지 여부
        if(!d.isEmpty()) {
          sb.append(0).append('\n');
        } else {
          sb.append(1).append('\n');
        }
      } else if(parts[0].equals("front")) { // 맨 앞 값 출력
        if(!d.isEmpty()) {
          sb.append(d.peekFirst()).append('\n');
        } else {
          sb.append("-1").append('\n');
        }
      } else {
        if(!d.isEmpty()) {
          sb.append(d.peekLast()).append('\n'); // 맨 뒤 값 출력
        } else {
          sb.append("-1").append('\n');
        }
      }
    }
    System.out.println(sb.toString());
  }
}
