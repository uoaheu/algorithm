/*
[문제] 할인 행사 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/131127

[접근]
- want와 number를 이용해 상품명, 필요한 개수 형태의 Map 구성
- discount 배열에서 연속된 10일 구간을 하나의 윈도우로 보고 각 구간마다 할인 상품의 개수를 Map 저장
- 해당 10일 동안의 할인 상품 구성이 원하는 상품 구성과 일치하는지 비교, 일치한다면 회원가입이 가능한 날이므로 answer 1 증가
- 모든 가능한 시작 날짜 확인

[성능]
- 시간 복잡도: O(N * 10)

[배운 점]
- 고정 길이의 연속 구간을 검사할 때는 슬라이딩 윈도우 방식 사용
*/

import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        // 원하는 상품 수량을 Map에 저장
        HashMap<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        // 슬라이딩 윈도우로 연속 10일 가격 확인
        for (int i = 0; i <= discount.length - 10; i++) {
            HashMap<String, Integer> discountMap = new HashMap<>();
            for (int j = 0; j < 10; j++) {
                String item = discount[i + j];
                discountMap.put(item, discountMap.getOrDefault(item, 0) + 1);
            }
            // 같은지 비교
            if (discountMap.equals(wantMap)) {
                answer++;
            }
        }
        return answer;
    }
}
