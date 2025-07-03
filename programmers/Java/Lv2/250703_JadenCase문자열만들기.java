/*
[문제] JadenCase 문자열 만들기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/12951

[접근]
- 전체 문자열을 한 글자씩 순회
- 공백 뒤의 첫 문자는 대문자로, 나머지는 소문자로 변환
- 가장 맨 앞은 무조건 대문자로 설정

[성능]
- 시간 복잡도: O(N), 여기서 N은 입력 문자열 s의 길이
- 문자열을 한 번만 순회하며, 각 문자 처리(확인, 추가, 대소문자 변환)는 상수 시간(O(1))이 걸리기 때문

[배운 점]
- 상태 관리를 위한 플래그 변수를 사용한다면 더 로직을 단순화할 수 있다는 걸 배움
- +연산자 대신, StringBuilder 사용하는 것이 효율적
*/

import java.util.*;
class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(s.charAt(0)).toUpperCase());
        
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i-1) == ' ') {
                sb.append(String.valueOf(s.charAt(i)).toUpperCase());
            } else if (s.charAt(i) == ' ') {
                sb.append(' ');
            } else {
                sb.append(String.valueOf(s.charAt(i)).toLowerCase());
            }
        }

        String str = sb.toString();
        return str;
    }
}
