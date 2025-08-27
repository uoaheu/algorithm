import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();
    int n = sc.nextInt(); // 1부터 n까지의 수

    Stack<Integer> stack = new Stack<>();
    int idx = 1; // 넣을 값

    for (int i = 0; i < n; i++) {
      int num = sc.nextInt(); // 입력값

      while (true) {
        // 스택이 비어있거나 스택의 마지막 값이 현재 값보다 작은 경우
        if (stack.isEmpty() || stack.peek() < num) {
          for (int j = idx; j <= num; j++) {
            stack.push(j);
            idx++;
            sb.append("+").append("\n");
          }
          stack.pop();
          sb.append("-").append("\n");
          break;
        } else if (stack.peek() == num) { // 스택 마지막 값 = 현재 값
          stack.pop();
          sb.append("-").append("\n");
          break;
        } else if (stack.peek() > num) { // 스택 마지막 값이 현재 값보다 작으면 수열 발생 X
          System.out.println("NO");
          return;
        }
      }
    }
    System.out.println(sb.toString());
  }
}
