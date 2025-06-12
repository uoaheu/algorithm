/*
[문제] 폰켓몬 (Lv1)
https://school.programmers.co.kr/learn/courses/30/lessons/1845

[접근]
- 중복 제거를 위해 HashSet 사용
- 선택할 수 있는 최대 개수는 N/2마리 → 중복 제거된 폰켓몬 종류 수와 비교하여 작은 값 반환

[성능]
- 시간복잡도: O(N) (HashSet 삽입 및 size 조회는 평균적으로 O(1))

[배운 점]
- HashSet을 통해 배열의 중복 값을 제거할 수 있음
- 중복 제거된 요소 개수 vs 제한 조건 값을 비교해 정답 도출 가능
*/

import java.util.*;

class Solution {
    public int solution(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num); // 중복 제거
        }

        int maxPick = nums.length / 2; // 최대 선택 가능 수
        return Math.min(set.size(), maxPick); // 중복 제거된 수 vs 제한 수 중 작은 값
    }
}
