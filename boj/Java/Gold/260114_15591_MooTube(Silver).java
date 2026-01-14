import java.util.*;

public class Main {
    
    static class Edge {
        int to;
        int w;
        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 동영상의 수
        int Q = sc.nextInt(); // 질문의 수
        
        List<Edge>[] g = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < N - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();
            g[a].add(new Edge(b, w));
            g[b].add(new Edge(a, w));
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < Q; i++) {
            int k = sc.nextInt();
            int v = sc.nextInt(); // 정점
            
            boolean[] visited = new boolean[N + 1];
            Queue<Integer> q = new LinkedList<>();
            visited[v] = true;
            q.offer(v);
            
            int cnt = 0;
            while(!q.isEmpty()) {
                int curr = q.poll();
                for(Edge e : g[curr]) {
                    if(!visited[e.to] && e.w >= k) {
                        visited[e.to] = true;
                        q.offer(e.to);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }
}
