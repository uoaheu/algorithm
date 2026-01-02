/*
[문제] 가장 큰 정사각형 찾기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/12905

[접근]
- 각 칸을 오른쪽 아래 꼭짓점으로 하는 가장 큰 정사각형의 한 변 길이를 DP로 관리
- dp[i][j] = (i, j)를 오른쪽 아래 꼭짓점으로 하는 최대 정사각형의 한 변 길이
- board[i][j]가 1일 때만 정사각형을 만들 수 있음
- (i, j)를 기준으로 위, 왼쪽, 왼쪽 위 대각선 중 가장 작은 값에 +1을 하면 내부까지 모두 1로 채워진 정사각형이 보장됨
- 첫 행 또는 첫 열은 확장할 이전 칸이 없으므로 값이 1이면 최대 정사각형의 크기는 1
- 전체 순회하며 가장 큰 정사각형의 한 변 길이를 max로 갱신

[성능]
- 시간 복잡도: O(N × M)

[배운 점]
- 정사각형 여부는 가로, 세로 연속 길이만으로 판단할 수 없고 내부가 모두 채워졌는지를 보장하는 조건이 필요함
*/

import java.util.*;
class Solution{
    public int solution(int [][]board) {
        int n = board.length;
        int m = board[0].length;

        int[][] dp = new int[n][m];
        int max = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    if(i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                    }
                    max = Math.max(max, dp[i][j]);
                    
                }
            }
        }
        return max * max;
    }
}
