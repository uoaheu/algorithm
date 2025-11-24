/*
[문제] 모음사전 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/84512

[접근]
- 각 자릿수마다 해당 글자를 1 올렸을 때 건너뛰는 단어 수를 미리 계산
- 단어를 숫자로 변환해 바로 인덱스를 구하기
- weight[i] = 뒤에 붙을 수 있는 모든 조합 수의 합 ex) 첫번째 자리 : 5^4 + 5^3 + 5^2 + 5^1 + 5^0 = 781

[성능]
- 시간 복잡도: O(L)

[배운 점]
- 조합을 전부 생성하는 대신, 규칙을 찾아 수학적 공식으로 변환하면 효율적으로 문제 풀이 가능
*/

class Solution {
    public int solution(String word) {
        char[] arr = {'A', 'E', 'I', 'O', 'U'};        
        int[] weight = {781, 156, 31, 6, 1}; // 각 자리의 가중치 (뒤에 올 수 있는 모든 경우의 수의 합)
        
        int answer = 0;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            
            // 현재 문자가 모음 배열에서 몇 번째인지 찾기
            int idx = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == c) {
                    idx = j;
                    break;
                }
            }
            
            // 현재 글자 자신도 하나의 단어로 카운트
            answer += idx * weight[i] + 1;
        }
        return answer;
    }
}
