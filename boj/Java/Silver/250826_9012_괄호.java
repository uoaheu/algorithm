import java.util.*;

public class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    Integer n = sc.nextInt();

    for (int i = 0; i < n; i++) {
      String str = sc.next();
      String result = "YES";
      Stack<Character> stack = new Stack<>(); // n번 스택 만들어주기

      for (int j = 0; j < str.length(); j++) {
        Character curr = str.charAt(j);

        if (curr == '(') {
          stack.push(curr);
        } else {
          if (stack.isEmpty()) {
            result = "NO";
            break;
          } else {
            stack.pop();
          }
        }
      }
      
      // 스택이 비어있지 않다면 NO
      if (!stack.isEmpty()) {
        result = "NO";
      }
      System.out.println(result);

    }
  }
}
