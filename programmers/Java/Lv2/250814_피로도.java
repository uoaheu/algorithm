/*
[문제] 피로도 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/87946

[접근]
- 완전탐색을 활용해 모든 던전 탐험 순서 확인
- 현재 남은 피로도(k)와 각 던전의 필요/소모 피로도를 비교해 입장 여부 결정
- 방문 여부를 visited[] 배열로 관리, 모든 가능한 경로에서 최대 탐험 수 갱신
- 전역 변수 best를 사용해 현재까지의 최대 탐험 수를 저장

[성능]
- 시간 복잡도: O(N!) -> N: 던전 개수

[배운 점]
- 완전탐색 문제에서는 순서를 전부 고려하는 DFS+백트래킹 구조가 유용
- 재귀 종료 조건 없이도 탐색 범위를 자동 제한 가능
*/

class Solution {
    static boolean[] visited;
    static int best;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        best = 0;
        check(k, dungeons, 0); // 아무것도 고르지 않고 시작
        return best;
    }
    
    // k : 남은 피로도, cnt : 현재까지 탐험 수 
    static void check(int k, int[][] dungeons, int cnt) {
        best = Math.max(best, cnt);
        
        for(int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;
            
            int need = dungeons[i][0]; // 필요 피로도
            int cost = dungeons[i][1]; // 소모 피로도
            
            // 최소 필요 피로도 충족할 때만 가능
            if(k >= need) {
                visited[i] = true;
                check(k - cost, dungeons, cnt + 1);
                visited[i] = false;
            }
        }
        
    }
}
