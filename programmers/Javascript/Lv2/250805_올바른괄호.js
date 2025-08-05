/*
[문제] 올바른 괄호 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/12909

[접근]
- (는 +1, )는 -1로 누적 카운트 관리
- 누적값이 음수가 되는 순간 false 반환
- 누적값이 0이면 올바른 괄호, 아니면 false

[성능]
- 시간복잡도: O(N)

[배운 점]
- 괄호 문제에서 스택 없이 카운터만으로도 효율적으로 풀 수 있다는 점
- 마지막에 answer === 0으로 여닫는 괄호 수가 일치하는지 확인하는 것도 중요
*/

function solution(s){
    var answer = 0;
    
    for (const c of s) {
        if (c === '(') {
            answer++;
        } else {
            answer--;
        }
        
        if (answer < 0) {
            return false;
        }
    }

    return answer === 0;
}
