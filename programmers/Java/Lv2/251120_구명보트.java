/*
[문제] 구명보트 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42885

[접근]
- 최대 2명 탑승 가능, 가장 무거운 사람과 가장 가벼운 사람이 함께 탈 수 있는지 확인
- 정렬 후 양쪽 투포인터(first, last) 사용 : 두 사람의 합이 limit 이하인 경우, 함께 탑승 / 초과인 경우, 무거운 사람만 혼자
- 어떤 경우든 보트는 1대 사용

[성능]
- 시간 복잡도: O(N log N)

[배운 점]
- 2명 조합 + 최소 보트의 경우, 정렬 + 투포인터 패턴으로 해결 가능
*/

import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int first = 0;
        int last = people.length - 1;
        int result = 0;
        
        while (first <= last) {
            if (people[first] + people[last] <= limit) {
                // 둘이 같이 탈 수 있으면 같이 태움
                first++;
                last--;
            } else {
                // 아니면 가장 무거운 사람 혼자 태움
                last--;
            }
            // 보트는 어쨌든 1대 사용
            result++;
        }
        
        return result;
    }
}
