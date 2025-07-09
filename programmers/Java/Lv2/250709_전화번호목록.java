/*
[문제] 전화번호 목록 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42577

[접근]
- 문자열 배열을 사전순으로 정렬하면, 접두어 관계에 있는 문자열들이 인접
  ex) ["119", "97674223", "1195524421"] → 정렬 → ["119", "1195524421", "97674223"]
- 이후 인접한 두 문자열만 비교하여 앞 문자열이 뒤 문자열의 접두어인지 확인
- 접두어 관계가 존재한다면 false 반환, 끝까지 없으면 true 반환

[성능]
- 시간복잡도: O(N log N) (정렬) -> 문자열 비교는 O(L), L은 평균 전화번호 길이
- 최종 시간복잡도: O(N log N + N × L)

[배운 점]
- 사전순 정렬을 활용하면 접두어 판별을 효율적으로 할 수 있음
- 정렬된 배열에서는 이웃한 원소만 비교해도 충분함
*/

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book); // 사전순 정렬

        for (int i = 0; i < phone_book.length - 1; i++) {
            // 앞 번호가 뒷 번호의 접두어인지 확인
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }
}
