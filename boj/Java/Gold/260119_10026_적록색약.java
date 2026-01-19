import java.util.*;
public class Main {
    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static boolean[][] visited2;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
  	public static void main(String[] args) {
    		Scanner sc = new Scanner(System.in);
    		N = sc.nextInt();
    		arr = new int[N][N];
    		visited = new boolean[N][N];
    		visited2 = new boolean[N][N];
    		
    		// R(빨강) - 1, G(초록) - 2, B(파랑) - 0
    		for(int i = 0; i < N; i++) {
    		    String str = sc.next();
    		    for(int j = 0; j < N; j++) {
    		        char c = str.charAt(j);
    		        if(c == 'R') {
    		            arr[i][j] = 1;
    		        } else if(c == 'G') {
    		            arr[i][j] = 2;
    		        } else {
                    arr[i][j] = 0;
                }
     		    }
    		}
    		
    		// 적록색약인 사람 : 빨&초 / 파
    		int cntY = 0; // 적록색약인 사람 
    		int cntN = 0; // 아닌 사람 
    		for(int i = 0; i < N; i++) {
    		    for(int j = 0; j < N; j++) {
    		        // 적록색약인 사람 
    		        if(!visited[i][j]) {
    		            check(i, j, arr[i][j]);
    		            cntY++;
    		        } 
    		        if(!visited2[i][j]) {
    		            check2(i, j, arr[i][j]);
    		            cntN++;
    		        }
    		    }
    		}
      
    		System.out.println(cntY + " " + cntN);
  	}
  	
  	static void check(int x, int y, int col) {
  	    Queue<int[]> q = new LinkedList<>();
  	    q.offer(new int[] {x, y, col});
  	    visited[x][y] = true;
  	    
  	    while(!q.isEmpty()) {
  	        int[] cur = q.poll();
              int cx = cur[0];
              int cy = cur[1];
              int c = cur[2];
  
  	        for(int i = 0; i < 4; i++) {
  	            int nx = cx + dx[i];
  	            int ny = cy + dy[i];
  	            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
  	            if(arr[nx][ny] != c) continue;
  	            if(visited[nx][ny]) continue;
  	            visited[nx][ny] = true;
  	            q.offer(new int[] {nx, ny, c});
  	        }
  	    }
  	}
  	
  	static void check2(int x, int y, int col) {
  	    Queue<int[]> q = new LinkedList<>();
  	    q.offer(new int[] {x, y, col});
  	    visited2[x][y] = true;
  	    
  	    while(!q.isEmpty()) {
  	        int[] cur = q.poll();
              int cx = cur[0];
              int cy = cur[1];
              int c = cur[2];
  
  	        for(int i = 0; i < 4; i++) {
  	            int nx = cx + dx[i];
  	            int ny = cy + dy[i];
  	            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
  	            if ((c == 0 && arr[nx][ny] != 0) || (c != 0 && arr[nx][ny] == 0)) continue;
  	            if(visited2[nx][ny]) continue;
  	            visited2[nx][ny] = true;
  	            q.offer(new int[] {nx, ny, c});
  	        }
  	    }
  	}
}

