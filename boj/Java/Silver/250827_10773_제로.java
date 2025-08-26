import java.util.*;

public class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    Integer n = sc.nextInt(); // 반복할 횟수
    Stack<Integer> stack = new Stack<>(); // 값 저장할 스택
    int result = 0;

    for (int i = 0; i < n; i++) {
      Integer num = sc.nextInt();
      // 0이 아닌 경우 스택에 저장
      if (num != 0) {
        stack.push(num);
        result += num; // 결과도 함께 저장
      } else {
        int wrongNum = stack.pop(); // 잘못된 값 빼내기
        result -= wrongNum;
      }
    }
    System.out.println(result);

  }
}
