import java.util.*;

public class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    String word = sc.next();
    String w = word.replace("()", ".");

    int result = 0;
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < w.length(); i++) {
      Character curr = w.charAt(i);
      if (curr == '(') {
        stack.push(curr);
      } else if (curr == '.') {
        result += stack.size();
      } else if (curr == ')') {
        stack.pop();
        result += 1;
      }
    }
    System.out.print(result);
  }
}
