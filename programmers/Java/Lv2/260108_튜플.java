/*
[문제] 튜플 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/64065

[접근]
- 문자열 형태로 주어진 튜플 정보를 파싱해야 하는 문제
- 문자열을 분리하여 각 집합을 문자열 배열로 만들고 튜플의 규칙상 원소 개수가 적은 집합부터 새로운 원소가 하나씩 추가되므로 집합 문자열을 길이 기준으로 오름차순 정렬
- 이미 등장한 숫자는 제외하고 처음 등장하는 숫자만 결과 리스트에 추가

[성능]
- 시간 복잡도: O(n log n)

[배운 점]
- 문자열 파싱 문제에서는 먼저 구조를 단순화한 후 로직을 적용하는 것이 중요
*/

import java.util.*;
class Solution {
    public int[] solution(String s) {
        String[] newS = s.split("}");
        Arrays.sort(newS, Comparator.comparingInt(String::length));

        // 튜플 구성
        List<Integer> result = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();

        for(String S : newS) {
            String[] nums = S.substring(2).split(",");
            for(String num : nums) {
                int x = Integer.parseInt(num);
                if (seen.add(x)) { // 처음 나온 숫자면 추가
                    result.add(x);
                }
            }
        }
        
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
