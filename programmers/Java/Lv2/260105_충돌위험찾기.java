/*
[문제] [PCCP 기출문제] 3번 / 충돌위험 찾기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/340211

[접근]
- 같은 시간 t에 같은 좌표에 로봇이 2대 이상 존재하면 충돌 위험 카운트
- 전역 시간 t=0,1,2... 기준으로 모든 로봇의 위치를 동시에 비교
- 먼저 각 로봇의 routes(방문해야 할 포인트들)를 따라가며 시간별 위치를 미리 생성
- 전체 로봇 중 가장 긴 path 길이를 maxT로 두고 각 시간 t마다 모든 로봇의 t시점 좌표를 카운트, 해당 좌표의 로봇 수가 2 이상인 좌표 개수 더하기

[성능]
- 시간 복잡도: O(ΣLi + n * maxT)
  - 전체 로봇 경로 생성: O(ΣLi)
  - 로봇의 위치 확인: O(n * maxT)
  
[배운 점]
- 단순히 좌표에 방문 기록을 누적하면 시간 축이 섞여서 오답 발생
- 2차원 좌표를 HashMap의 key로 쓰기 위해 (r,c)를 long으로 인코딩하는 방식이 유용함
*/

import java.util.*;
class Solution {
    public int solution(int[][] points, int[][] routes) {
        int n = routes.length; // 로봇의 수
        
        // 1. 로봇별 시간 경로(path) 만들기
        List<List<long[]>> paths = new ArrayList<>();
        int max = 0; // 최대 걸린 시간
        
        for(int i = 0; i < n; i++) {
            List<long[]> path = buildPath(points, routes[i]);
            paths.add(path);
            max = Math.max(max, path.size());
        }
        
        // 2. 시간 t별로 위치 카운트해서 충돌 수 합산
        int answer = 0;
        
        // t시간마다 값 확인
        for(int t = 0; t < max; t++) {
            HashMap<Long, Integer> freq = new HashMap<>();
            
            // 로봇마다의 위치 체크
            for(int i = 0; i < n; i++) {
                List<long[]> path = paths.get(i); // i번째의 로봇 경로 저장
                
                // 마지막 포인트 도착하면 물류센터를 떠남 -> path 길이까지만 존재
                if (t >= path.size()) continue;
                
                // t시간에 위치한 i번째 로봇의 위치
                int r = (int) path.get(t)[0];
                int c = (int) path.get(t)[1];
                long k = key(r, c);
                freq.put(k, freq.getOrDefault(k, 0) + 1);
            }
           
            // 그 시간 t에 2대 이상 모인 좌표 수 더하기
            for (int cnt : freq.values()) {
                if (cnt >= 2) answer++;
            }
        }
        return answer;
    }
    
    // points : 배열 인덱스가 0-based라서 route에서 -1 필요
    static List<long[]> buildPath(int[][] points, int[] route) {
        List<long[]> path = new ArrayList<>();
        
        // 시작점
        int startIdx = route[0] - 1;
        int r = points[startIdx][0];
        int c = points[startIdx][1];
        
        path.add(new long[] {r, c});
        
        for(int i = 0; i < route.length - 1; i++) {
            int from = route[i] - 1;
            int to = route[i + 1] - 1;
            
            // 가야할 지점
            int tr = points[to][0];
            int tc = points[to][1];
            
            // r 먼저 이동
            while (r != tr) {
                r += (r < tr ? 1 : -1);
                path.add(new long[] {r, c});
            }
            // 그 다음 c 이동
            while(c != tc) {
                c += (c < tc ? 1 : -1);
                path.add(new long[] {r, c});
            }  
        }
        return path;
    }
    
    // (r, c)를 하나의 long 키로 합치기 (r,c는 1~100)
    static long key(int r, int c) {
        return (long) r * 1000 + c;
    }
}
