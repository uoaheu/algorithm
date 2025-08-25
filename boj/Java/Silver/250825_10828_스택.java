import java.util.*;

public class Main {
  public static void main(String[] args) {

    Stack<Integer> stack = new Stack<>();
    Scanner sc = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();

    int n = sc.nextInt();

    for (int i = 0; i < n; i++) {
      String type = sc.next();
      if (type.equals("push")) {
        int x = sc.nextInt();
        stack.push(x);
      } else if (type.equals("top")) {
        sb.append(stack.isEmpty() ? -1 : stack.peek()).append('\n');
      } else if (type.equals("pop")) {
        sb.append(stack.isEmpty() ? -1 : stack.pop()).append('\n');
      } else if (type.equals("size")) {
        sb.append(stack.size()).append('\n');
      } else if (type.equals("empty")) {
        sb.append(stack.isEmpty() ? 1 : 0).append('\n');
      }
    }
    System.out.print(sb.toString());
  }
}
