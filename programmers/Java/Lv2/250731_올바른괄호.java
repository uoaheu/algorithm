/*
[문제] 올바른 괄호 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/12909

[접근]
- 여는 괄호 '('는 스택에 추가
- 닫는 괄호 ')'가 나올 때마다 스택에서 하나씩 pop
- 만약 스택이 비어 있는 상태에서 ')'가 나오면 false 반환
- 문자열을 끝까지 순회한 후, 스택이 비어 있으면 올바른 괄호 문자열

[성능]
- 시간 복잡도: O(N) — 문자열 전체를 한 번 순회
- 공간 복잡도: O(N) — 최악의 경우 모든 '('가 스택에 쌓임

[배운 점]
- 괄호 짝을 맞추는 문제는 Stack 자료구조로 해결할 수 있음
- Stack을 직접 쓰지 않고도 가능한 문제
*/

import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        System.out.println(s.charAt(0));
        for(int i = 0; i < len; i++) {
            if(s.charAt(i) == ('(')) {
                stack.push(s.charAt(i));
            } else if(s.charAt(i) == (')')) {
                if(stack.size() == 0) {
                    answer = false;
                    break;
                } else {
                    stack.pop();
                }
            }                
        } 
        if(stack.size() != 0) {
            answer = false;
        }
        return answer;
    }
}
