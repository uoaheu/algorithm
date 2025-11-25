/*
[문제] 도둑질 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/42897

[접근]
- 집이 원형으로 배치되어 있어 첫 번째 집과 마지막 집은 동시에 털 수 없음
- 첫 번째 집을 턴 경우, 마지막 집은 절대 털 수 없으므로 [0 ~ n-2] 구간만 고려
- 첫 번째 집을 안 턴 경우, [1 ~ n-1] 구간만 고려
- 두 경우에서 나온 최대값 중 더 큰 값을 정답으로 선택

[성능]
- 시간복잡도: O(n)

[배운 점]
- 인접한 원소를 동시에 선택할 수 없는 조건은 dp[i] = max(dp[i-1], dp[i-2] + value[i]) 형태로 DP 점화식을 세울 수 있음
*/

import java.util.*;

class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        // 1. 첫 번째 집을 턴 경우 -> 마지막 집은 절대 못 턴다 (0 ~ n-2)
        int[] dp1 = new int[n];
        dp1[0] = money[0];
        dp1[1] = Math.max(money[0], money[1]); // 0번, 1번 중 더 큰 값
        for (int i = 2; i < n - 1; i++) { // n-2까지 계산
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]);
        }
        
        // 2. 첫 번째 집을 안 턴 경우 -> 마지막 집은 털 수도 있다 (1 ~ n-1)
        int[] dp2 = new int[n];
        dp2[0] = 0; // 0번 집은 안 털기로 가정
        dp2[1] = money[1]; // 1번 집만 털었을 때
        for (int i = 2; i < n; i++) { // n-1까지 계산
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
        }
        
        return Math.max(dp1[n - 2], dp2[n - 1]);
    }
}
