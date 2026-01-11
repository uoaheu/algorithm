import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 블로그를 시작하고 지난 일수 
        int X = sc.nextInt(); // X일 동안 들어온 방문자 수 
        int[] people = new int[N];
        for(int i = 0; i < N; i++) {
            people[i] = sc.nextInt();
        }
        
        // 첫 윈도우 합(0 ~ X-1) : 처음 X일 동안 들어온 방문자 수 
        int sum = 0;
        for(int i = 0; i < X; i++) {
            sum += people[i];
        }

        int max = sum;
        int cnt = 1;
        
        // 두번째부터 X일 동안 들어온 방문자 수 체크
        for(int i = X; i < N; i++) {
            sum += people[i] - people[i - X];
            if(sum > max) {
                max = sum;
                cnt = 1;
            } else if(sum == max) {
                cnt++;
            }
        }
        
        if(max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(cnt);
        }
		
	}
}
