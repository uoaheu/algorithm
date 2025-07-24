/*
[문제] H-Index (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42747

[접근]
- 논문 인용 횟수를 오름차순 정렬하고, 전체 논문 수(n)에서 현재 인덱스(i)를 뺀 값을 h-index 후보로 설정
- citations[i]가 h 이상이면, h는 조건을 만족하므로 h를 반환

[성능]
- 시간복잡도: O(N log N) (정렬)

[배운 점]
- H-Index는 인용 횟수 >= H인 논문이 H편 이상일 때 성립
- 정렬 후 반복을 통해 h-index를 직관적으로 계산 가능
*/

import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations); // 인용 횟수 오름차순 정렬
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            int h = n - i; // 현재 인덱스를 기준으로 남은 논문 수
            if (citations[i] >= h) {
                return h; // h-index 조건 만족 시 반환
            }
        }
        return 0; // 만족하는 h가 없으면 0 반환
    }
}
