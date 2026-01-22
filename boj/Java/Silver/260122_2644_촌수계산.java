import java.util.*;
public class Main {
    public static void main(String[] args) {
	      Scanner sc = new Scanner(System.in);
  	    int n = sc.nextInt();
  	    int a = sc.nextInt() - 1; // 0기준으로 변경 
  	    int b = sc.nextInt() - 1;
  	    int m = sc.nextInt();
  	    
  	    // 무방향 그래프
        List<List<Integer>> g = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            // 촌수 : 양방향 이동 가능
            g.get(x).add(y);
            g.get(y).add(x);
        }
        
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        
        Queue<Integer> q = new LinkedList<>();
        dist[a] = 0;
        q.offer(a);
        
        while(!q.isEmpty()) {
            int curr = q.poll();
            if(curr == b) break;
            
            for(int next : g.get(curr)) {
                if(dist[next] != -1) continue;
                dist[next] = dist[curr] + 1;
                q.offer(next);
            }
        }
        System.out.println(dist[b]);
	  }
}
