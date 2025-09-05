import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();
    int N = sc.nextInt();
    Deque<String[]> d = new ArrayDeque<>();
    
    // 풍선에 써있는 종이 숫자 적기
    for(int i = 1; i <= N; i++) {
      // d.add({sc.next(), String.valueOf(i)});
      d.add(new String[]{sc.next(), String.valueOf(i)}); // [0]에는 종이에 적힌 값, [1]에는 풍선 번호
    }

    // 다 빌때까지
    int num = 0;
    String[] str = d.pollFirst();
    sb.append(str[1]).append(" ");
    while(!d.isEmpty()) {
      if(str[0].charAt(0)=='-') { // 음수 : num 만큼 뒤에서 빼서 앞으로 이동하고, num번째 값을 빼기 
        num = Integer.parseInt(str[0].substring(1));
        for(int i = 0; i < num - 1; i++) {
          d.addFirst(d.pollLast());
        }
        str = d.pollLast();
        sb.append(str[1]).append(" ");
      } else { // 양수 : num 만큼 앞에서 빼서 뒤로 이동하고, num번째 값을 빼기 
        num = Integer.parseInt(str[0]);
        for(int i = 0; i < num - 1; i++) {
          d.addLast(d.pollFirst());
        }
        str = d.pollFirst();       
        sb.append(str[1]).append(" ");  
      }
    }
    System.out.print(sb.toString());
  }
}
