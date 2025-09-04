import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    sc.nextLine(); // 버퍼에 남아있는 개행 문자 제거 : 이 부분 없이 진행하면 오류 발생 ,, 

    Queue<Integer> q = new LinkedList<>();
    StringBuilder sb = new StringBuilder();

    // 큐에 N까지 값 넣기
    for(int i = 0; i < N; i++) {
      String str = sc.nextLine();
      // 공백을 기준으로 나누기
      String[] parts = str.split(" ");
      if(parts[0].equals("push")) {
        q.add(Integer.parseInt(parts[1]));
      } else if(parts[0].equals("pop")) {
        if(!q.isEmpty()) {
          sb.append(q.poll()).append('\n');
        } else {
          sb.append("-1").append('\n');
        }
      } else if(parts[0].equals("size")) {
        sb.append(q.size()).append('\n');
      } else if(parts[0].equals("empty")) {
        if(!q.isEmpty()) {
          sb.append(0).append('\n');
        } else {
          sb.append(1).append('\n');
        }
      } else if(parts[0].equals("front")) {
        if(!q.isEmpty()) {
          sb.append(q.peek()).append('\n');
        } else {
          sb.append("-1").append('\n');
        }
      } else {
        if(!q.isEmpty()) {
          sb.append(((LinkedList<Integer>) q).getLast()).append('\n'); // linkedlist에서 q의 맨 마지막값 확인하는 함수 !!
        } else {
          sb.append("-1").append('\n');
        }
      }
    }
    System.out.println(sb.toString());
  }
}
