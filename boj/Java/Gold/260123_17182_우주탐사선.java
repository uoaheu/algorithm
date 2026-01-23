import java.util.*;
public class Main {
    static int N, K;
    static int[][] arr;
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;
    
  	public static void main(String[] args) {
  	    Scanner sc = new Scanner(System.in);
  	    N = sc.nextInt();
  	    K = sc.nextInt();
  	    arr = new int[N][N];
  
  	    for(int i = 0; i < N; i++) {
  	        for(int j = 0; j < N; j++) {
  	            arr[i][j] = sc.nextInt();
  	        }
  	    }
  	    
  	    // 1. 모든 쌍의 최단 거리 
  	    for(int m = 0; m < N; m++) {
  	        for(int i = 0; i < N; i++) {
  	            for(int j = 0; j < N; j++) {
  	                if(arr[i][j] > arr[i][m] + arr[m][j]) {
  	                    arr[i][j] = arr[i][m] + arr[m][j];
  	                }
  	            }
  	        }
  	    }
  	    
  	    // 2. DFS 백트래킹
        visited = new boolean[N];
        visited[K] = true;
        dfs(K, 1, 0);
  
        System.out.println(ans);
  	}
  	
  	// curr : 현재 행성, cnt : 방문한 개수, sum : 누적 시간
  	static void dfs(int curr, int cnt, int sum) {
  	    if(sum >= ans) return;
  	    
  	    if(cnt == N) {
  	        ans = Math.min(ans, sum);
  	        return;
  	    }
  	    
  	    for(int i = 0; i < N; i++) {
  	        if(visited[i]) continue;
  	        visited[i] = true;
  	        dfs(i, cnt + 1, sum + arr[curr][i]);
  	        visited[i] = false;
  	    }
  	}
}
