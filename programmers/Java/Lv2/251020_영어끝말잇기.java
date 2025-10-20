/*
[문제] 영어 끝말잇기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/12981

[접근]
- 단어를 차례대로 순회하며 규칙 위반 여부를 즉시 검사
- 1.이전 단어의 마지막 문자와 현재 단어의 첫 문자가 같은지 확인
- 2. 이미 등장한 단어인지 확인 (HashSet 사용)
- 두 조건 중 하나라도 위반되면 해당 단어를 말한 사람의 번호와 차례 반환
- 모든 단어를 통과하면 규칙 위반이 없으므로 [0, 0] 반환

[성능]
- 시간 복잡도: O(N)

[배운 점]
- 중복 체크에는 List보다 Set이 효율적
*/

import java.util.*;
public class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> check = new HashSet<>();
        char LastChar = 0;

        for (int i = 0; i < words.length; i++) {
            String target = words[i];
            int person = (i % n) + 1;
            int turn = (i / n) + 1;

            if (i > 0 && target.charAt(0) != LastChar) {
                return new int[] { person, turn };
            }
            
            // 중복 단어 체크
            if (check.contains(target)) {
                return new int[] { person, turn };
            }
            
            // 제대로 진행했다면
            check.add(target);
            LastChar = target.charAt(target.length() - 1);
        }

        return new int[] { 0, 0 };
    }
}
