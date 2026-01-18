import java.util.*;
public class Main {
	  public static void main(String[] args) {
		    Scanner sc = new Scanner(System.in);
		    int N = sc.nextInt(); // 물건의 개수 
		    int M = sc.nextInt(); // 미리 측정된 물건 쌍의 개수
		
	    	int[][] arrB = new int[N][N]; // big -> small
        int[][] arrS = new int[N][N]; // small -> big (역방향)

        for(int i = 0; i < M; i++) {
            int big = sc.nextInt() - 1;
            int small = sc.nextInt() - 1;
            arrB[big][small] = 1;
            arrS[small][big] = 1;
        }
        
        // 경유해서 알 수 있는 관계 전파
        for(int a = 0; a < N; a++) {
            for(int b = 0; b < N; b++) {
                if(a == b) continue;
                for(int c = 0; c < N; c++) {
                    if(arrB[b][a] == 1 && arrB[a][c] == 1) arrB[b][c] = 1;
                    if(arrS[b][a] == 1 && arrS[a][c] == 1) arrS[b][c] = 1;
                }
            }
        }
        
        // i 기준으로 비교 불가능한 물건 개수 출력
    		for(int i = 0; i < N; i++) {
    		    int cnt = 0;
    		    for(int j = 0; j < N; j++) {
    		        if(i == j) continue;
    		        if(arrB[i][j] == 0 && arrS[i][j] == 0) cnt++;
    		    }
    		    System.out.println(cnt);
    		}
  	}
}

