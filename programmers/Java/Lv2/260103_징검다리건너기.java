/*
[문제] 징검다리 건너기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/64062

[접근]
- x명이 건널 수 있는가라는 이분 탐색으로 접근
- x명이 건넌 뒤 0 이하가 되는 돌이 연속으로 k개 이상 존재하면 더 이상 건널 수 없다고 판단
- 건널 수 있으면 더 많은 사람 수를 탐색하고 건널 수 없으면 범위를 줄인다

[성능]
- 시간 복잡도: O(N log M)

[배운 점]
- 사람 수를 1명씩 증가시키며 돌의 내구도를 직접 감소시키는 방식은 최대 내구도가 매우 커 시간 초과 발생
- 조건 비교에서 s <= x 와 s < x 의 차이처럼 경계 조건이 결과에 영향을 미침
*/

import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int left = 0;
        int right = 0;
        for(int s : stones) {
            right = Math.max(right, s);
        }
        int answer = 0;
        
        while(left <= right) {
            // 최대 넘어갈 수 있는 친구 수
            int mid = (left + right) / 2;
            
            if(isPass(stones, k, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    // x명이 건널 수 있는지 판별
    private boolean isPass(int[] stones, int k, int x) {
        int cnt = 0;
        for(int s : stones) {
            if(s < x) {
                cnt++;
                if(cnt >= k) {
                    return false;
                }
            } else {
                cnt = 0;
            }
        }
        return true;
    }
}
