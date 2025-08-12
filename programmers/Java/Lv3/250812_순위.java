/*
[문제] 순위 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/49191

[접근]
- 주어진 경기 결과로부터 각 선수 간의 승패 관계를 2차원 boolean 배열로 표현
- Floyd-Warshall 알고리즘을 활용해서 간접 승리 관계까지 계산 (A가 B를 이기고, B가 C를 이기면 A가 C를 이긴 것으로 처리)
- 최종적으로 각 선수에 대해 (이긴 선수 수 + 진 선수 수)가 n-1이면 순위를 확정할 수 있음

[성능]
- 시간복잡도: O(n³)

[배운 점]
- 플로이드-워셜 알고리즘은 단순 최단 거리 계산뿐 아니라, 관계 전이(승패 관계, 연결 여부) 계산에도 활용 가능
- 간접 관계를 찾을 때는 반드시 중간 노드 k를 최상위 루프에 둬야 올바른 전이 계산이 가능
*/

class Solution {
    public int solution(int n, int[][] results) {
        // 승패 확인 배열
        boolean[][] win = new boolean[n][n];
        
        for(int i = 0; i < results.length; i++) {
            win[results[i][0] - 1][results[i][1] - 1] = true; // 이긴 경우 true
        }
        
        // 모든 승리의 경우 계산 -> Floyd-Warshall: 중간 노드 k가 최상위 루프
        for (int k = 0; k < n; k++) { // 중간 경유 선수
            for (int i = 0; i < n; i++) { // 출발 선수
                if (i == k) continue; // 자기 자신 제외
                for (int j = 0; j < n; j++) { // 도착 선수
                    if (j == k || j == i) continue; // 자기 자신 및 중간 노드 제외
                  
                    // A가 B 이김, B가 C 이김 -> A가 C 이김
                    if (win[i][k] && win[k][j]) {
                        win[i][j] = true;
                    }
                  
                }
            }
        }

        int result = 0;
        for(int i = 0; i < n; i++) {
            int cntW = 0;
            int cntL = 0;
            for(int j = 0; j < n; j++) {
                if(win[i][j]) cntW++;
                if(win[j][i]) cntL++;
            }
            // 전체 n명 중 자기 자신을 제외한 모든 선수와 승패 관계가 있으면 순위 확정 가능
            if(cntW + cntL == n - 1) result++;
        }
        
        return result;
    }
}

    
    
