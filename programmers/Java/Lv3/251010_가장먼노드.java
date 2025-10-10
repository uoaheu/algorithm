/*
[문제] 가장 먼 노드 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/49189

[접근]
- bfs를 이용해서 1번 노드에서 모든 노드까지의 최단 거리 구하기
- 최단 거리 배열(dist)에서 가장 먼 거리 구하고, 그 거리와 같은 노드 수 계산

[성능]
- 시간복잡도: O(N + M)

[배운 점]
- 무가중치 그래프의 최단 거리는 BFS 한번으로 구하기 가능
- 거리 배열에서 최대값이 가장 먼 거리
*/

import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        List<Integer>[] g = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        
        for(int[] e : edge) {
            int a = e[0];
            int b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        dist[1] = 0;
        q.offer(1);
        
        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int nx : g[curr]) {
                if(dist[nx] != -1) {
                    continue;
                }
                dist[nx] = dist[curr] + 1;
                q.offer(nx);
            }
        }
        
        int max = 0;
        for(int i = 1; i <= n; i++) {
            max = Math.max(max, dist[i]);
        }
        
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(dist[i] == max) {
                cnt++;
            }
        }
        
        return cnt;
    }
}
