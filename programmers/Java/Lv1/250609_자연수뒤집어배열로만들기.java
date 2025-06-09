/*
[문제] 자연수 뒤집어 배열로 만들기 (Lv1)
https://school.programmers.co.kr/learn/courses/30/lessons/12932

[접근]
- 숫자를 문자열로 변환 → 문자열을 split("")으로 분해 → 뒤에서부터 정수로 변환해 배열에 저장

[성능]
- 시간복잡도: O(N)

[배운 점]
- split("")으로 문자열 자릿수 분해 가능
- 문자열 → 정수는 Integer.parseInt() 사용
- 불필요한 코드 제거 방법 존재 -> (String str = Long.toString(n);)
*/

class Solution {
    public int[] solution(long n) {
        String str = new String();
        str = Long.toString(n); // 문자열 변환
        String[] newStr = str.split(""); // 자릿수 분해
        int[] answer = new int[newStr.length];
      
        for(int i = 0; i < newStr.length; i++) {
            answer[i] = Integer.parseInt(newStr[newStr.length - 1 - i]);
        }
      
        return answer;
    }
}
