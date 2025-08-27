import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt(); // 멜로디 음 수
    int P = sc.nextInt(); // 프렛 수

    List<Stack<Integer>> stacks = new ArrayList<>();
    int result = 0; // 움직인 횟수

    for (int i = 0; i < 6; i++) stacks.add(new Stack<>());

    for (int i = 0; i < N; i++) {
      int n = sc.nextInt(); // 줄 번호(1~6)
      int p = sc.nextInt(); // 프렛 번호

      Stack<Integer> s = stacks.get(n - 1);
      while (true) {
        if (s.empty() || s.peek() < p) { // push
          s.push(p);
          result++;
          break;
        } else if (s.peek() == p) { // 이미 눌린 프렛
          break;
        } else { // s.peek() > p -> pop
          s.pop();
          result++;
        }
      }
    }
    System.out.println(result);
  }
}
