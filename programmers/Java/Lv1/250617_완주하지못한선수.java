/*
[문제] 완주하지 못한 선수 (Lv1)  
https://school.programmers.co.kr/learn/courses/30/lessons/42576

[접근]  
- 참가자 이름을 HashMap에 이름:개수 형태로 저장  
- 완주자 이름을 순회하며 해당 이름의 개수를 1씩 감소  
- 남은 개수가 1인 참가자가 완주하지 못한 선수  

[성능]  
- 시간복잡도: O(N)  
  -> 참가자/완주자 배열 순회 각각 O(N), map 접근/삽입 O(1)  

[배운 점]  
- HashMap의 getOrDefault()로 null 처리 없이 값 접근 가능  
- 동일한 이름이 여러 명일 수 있어 이름당 출현 횟수를 세야 함  
- Map 순회를 통해 특정 조건을 만족하는 key를 찾을 수 있음
*/

import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();

        // 참가자 명단을 map에 추가 (동명이인 고려하여 개수 증가)
        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        // 완주자 명단을 순회하면서 개수 감소
        for (String c : completion) {
            map.put(c, map.getOrDefault(c, 0) - 1);
        }

        // 남아있는 개수가 1인 참가자 = 완주하지 못한 선수
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return ""; // 문제 조건상 무조건 한 명 존재
    }
}
