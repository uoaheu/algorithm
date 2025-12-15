/*
[문제] 징검다리 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/43236

[접근]
- 바위를 n개 제거했을 때 각 구간의 최소 거리의 최댓값을 구하는 문제
- 최소 거리를 직접 구간별로 계산 X, 최소 거리를 이분탐색으로 가정하고 가능한지 여부 판단
- 가능한 경우 최소 거리를 더 키우고 불가능한 경우 줄이는 방식으로 탐색

[성능]
- 시간복잡도: O(m log m + m log distance)

[배운 점]
- 최대/최소 값을 구하는 문제에서 값 자체를 이분탐색하는 방식
*/

import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int left = 1;
        int right = distance;
        int answer = 0;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if (check(distance, rocks, n, mid)) {
                answer = mid; // 더 큰 최소 거리 도전
                left = mid + 1;
            } else {
                right = mid - 1; // 최소 거리 낮추기
            }
        }
        return answer;
    }
    
    static boolean check(int distance, int[] rocks, int n, int minDist) {
        int removed = 0;
        int prev = 0; // 시작점 = 0

        for (int r : rocks) {
            if (r - prev < minDist) {
                removed++; // 현재 바위 제거
                if (removed > n) {
                    return false;
                }
            } else {
                prev = r; // 현재 바위 남김
            }
        }

        // 마지막 구간 체크
        if (distance - prev < minDist) {
            removed++;
        }

        return removed <= n;
    }
}
