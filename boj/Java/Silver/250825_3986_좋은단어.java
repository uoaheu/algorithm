import java.util.*;

public class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int result = 0;

    for (int i = 0; i < n; i++) {
      String word = sc.next();
      Stack<Character> stack = new Stack<>();

      if (word.length() % 2 != 0) {
        continue;
      }
      for (int j = 0; j < word.length(); j++) {
        char w = word.charAt(j);

        if (stack.isEmpty()) { // 스택에 아무것도 없다면 현재 값 넣기 
          stack.push(w);
        } else {
          if (stack.peek() == w) { // 스택 맨 위의 값과 동일하다면 기존 값 빼내기 
            stack.pop();
          } else { // 스택 맨 위의 값과 다르면 현재 값 넣기
            stack.push(w);
          }
        }
      }

      if (stack.isEmpty()) {
        result++;
      }
    }

    System.out.print(result);
  }
}
