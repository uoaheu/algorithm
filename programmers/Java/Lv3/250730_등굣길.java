/*
[문제] 등굣길 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/42898

[접근]
- dp[i][j]는 (1,1)부터 (j,i)까지 도달할 수 있는 경로의 수를 의미
- 오른쪽과 아래로만 이동 가능 -> dp[i][j] = dp[i-1][j] + dp[i][j-1]
- 물웅덩이인 칸은 경로가 막혀 있으므로 건너뛰기
- 시작점은 항상 1로 초기화, 물웅덩이는 -1로 마킹 후 계산 시 무시
- 모듈로 연산(1,000,000,007)을 통해 숫자 범위 초과 방지

[성능]
- 시간복잡도: O(n * m) — 모든 칸을 한 번씩 순회
- 공간복잡도: O(n * m) — DP 테이블 사용

[배운 점]
- 물웅덩이(-1)는 계산 전에 걸러내거나, 경로 수를 0으로 처리해야 함
- 인덱스를 1-based로 잡으면 (0일 때 처리) 분기 조건을 줄여 코드가 깔끔해짐
*/

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int num = 1_000_000_007;
        int[][] dp = new int[n + 1][m + 1];

        // 물웅덩이
        for (int[] p : puddles) {
            dp[p[1]][p[0]] = -1;
        }
        
        // 시작점
        dp[1][1] = 1; 
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 시작점이나 물웅덩이는 스킵
                if (dp[i][j] == -1 || (i == 1 && j == 1)) continue;

                int top = (dp[i-1][j] == -1) ? 0 : dp[i - 1][j];
                int left = (dp[i][j - 1] == -1) ? 0 : dp[i][j - 1];

                dp[i][j] = (top + left) % num;
            }
        }

        return dp[n][m];
    }
}
