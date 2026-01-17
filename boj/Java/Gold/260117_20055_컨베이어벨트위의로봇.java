import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] A = new int[N * 2]; // 내구도
		boolean[] robot = new boolean[N]; // 위쪽 로봇 상태
		
		for(int i = 0; i < N * 2; i++) {
		    A[i] = sc.nextInt();
		}
		
		int step = 0;
		
		while(true) {
		    step++;
		    
		    // 컨베이어 회전 : 내구도 
		    int last = A[N * 2 - 1];
    		for(int i = N * 2 - 1; i > 0; i--) {
    		    A[i] = A[i - 1];
    		}
    		A[0] = last;
    		
    		// 컨베이어 회전 : 로봇 
    		for(int i = N - 1; i > 0; i--) {
    		    robot[i] = robot[i - 1];
    		}
    		robot[0] = false; // 내리고 다시 온 첫번째 부분 
    		robot[N - 1] = false; // 내리는 위치
    		
    		// 로봇 이동 
    		for(int i = N - 2; i >= 0; i--) {
                if(robot[i] && !robot[i + 1] && A[i + 1] > 0) {
                    robot[i] = false;
                    robot[i + 1] = true;
                    A[i + 1]--;
                }
            }
            robot[N - 1] = false;

            // 로봇 올리기
            if(A[0] > 0 && !robot[0]) {
                robot[0] = true;
                A[0]--;
            }

            // 종료 조건
            int zero = 0;
            for(int x : A) {
                if(x == 0) zero++;
            }
            if(zero >= K) break;
		}

		System.out.println(step);
	}
}
