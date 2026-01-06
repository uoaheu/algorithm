/*
[문제] 합승 택시 요금 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/72413

[접근]
- 어떤 지점 i까지 같이 이동한 뒤(i에서 분기) i->a, i->b로 각각 이동으로 계산
- 모든 분기점 i(1..n)에 대해 dist(s,i) + dist(i,a) + dist(i,b) 최솟값

[성능]
- 시간복잡도: O(3 * (m log n)) (m: 간선 수)

[배운 점]
- 같이 가다가 갈라지는 문제는 분기점을 변수로 두고 완전탐색하면 구조가 단순해짐
*/

import java.util.*;
class Solution {
  
    static class Edge{
        int to; // 연결 지점
        int w; // 요금
        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
    
    static final long INF = (long) 1e18;
    static List<Edge>[] graph;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        // 1. 그래프 생성 (무방향)
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] f : fares) {
            int u = f[0]; 
            int v = f[1];
            int w = f[2];
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }
        
        // 2. 출발점(s, a, b)부터 각 지점까지의 최소 요금
        long[] distS = dijkstra(n, s);
        long[] distA = dijkstra(n, a);
        long[] distB = dijkstra(n, b);
        
        // 3. 모든 분기점 i에 대해 최소값 계산
        long answer = INF;
        for(int i = 1; i <= n; i++) {
            long sum = distS[i] + distA[i] + distB[i];
            answer = Math.min(answer, sum);
        }
        return (int) answer;
    }
  
    static long[] dijkstra(int n, int start) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        // 비용, 정점 - 우선순위큐
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[0]));
        pq.offer(new long[] {0, start});
        
        while(!pq.isEmpty()) {
            long[] curr = pq.poll();
            long cost = curr[0];
            int u = (int) curr[1];
            
            // 이미 다른 최소 요금으로 업데이트 되었으므로 pass
            if(cost != dist[u]) continue;
            
            // 현재 지점에서 연결된 지점 체크
            for(Edge e : graph[u]) {
                int v = e.to;
                long savecost = cost + e.w;
                
                if(savecost < dist[v]) {
                    dist[v] = savecost;
                    pq.offer(new long[] {savecost, v});
                }
            }
        }
        return dist;
    }
}
