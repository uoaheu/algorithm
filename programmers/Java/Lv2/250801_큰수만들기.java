/*
[문제] 큰 수 만들기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42883

[접근]
- 현재 위치에서 k + 현재까지 선택한 숫자 수 + 1까지 탐색하면서 최댓값을 고름
- 최댓값을 찾으면 sb에 추가하고, 그 위치 다음부터 다시 반복
- StringBuilder를 사용해 문자열 결합 시 시간복잡도 줄임

[성능]
- 시간 복잡도: O(N * k)보다 더 나은 방식으로, 사실상 O(N)

[배운 점]
- StringBuilder를 사용하지 않고 문자열 덧붙임을 반복할 경우 시간 초과 발생
- 선택 가능한 범위 내에서 최댓값을 선택하는 그리디 전략은 문제 해결에 효과적
*/

import java.util.*;
class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int len = number.length() - k;
        int startIdx = 0;
        
        while(startIdx < number.length() && sb.length() != len) {
            int leftNum = k + sb.length() + 1;
            int max = 0;
            for (int j = startIdx; j < leftNum; j++) {
                if (max < number.charAt(j) - '0') {
                    max = number.charAt(j) - '0';
                    startIdx = j + 1;
                }
            }
            sb.append(max);
        }
        String answer = sb.toString();
        return answer;
    }
}
