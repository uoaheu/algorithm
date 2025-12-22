/*
[문제] 입국심사 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/43238

[접근]
- 모든 사람이 심사를 마치는 데 걸리는 최소 시간을 구하는 문제
- 특정 시간(mid)이 주어졌을 때, 각 심사관이 mid 시간 동안 처리할 수 있는 사람 수를 계산해서 n명 이상 처리 가능한지 여부 판단
- 처리 가능하면 시간을 줄이고(right 감소) 불가능하면 시간을 늘리는(left 증가) 방식으로 최소 시간 탐색

[성능]
- 시간 복잡도: O(m log (n * maxTime))

[배운 점]
- JS에서는 sort()가 문자열 기준이므로 숫자 정렬 시 비교 함수 필수
- 이분 탐색에서 mid 값과 누적 계산은 반드시 정수 처리
*/

function solution(n, times) {
    var answer = 0;
    // (1) times 정렬
    times.sort((a, b) => a - b);

    // (2) 0 ~ 가장 긴 시간
    var left = 0;
    var right = times[times.length - 1] * n;

    
    var answer = 0;
    
    while(left <= right) {
        var mid = Math.floor((left + right) / 2);
        var person = 0;
        for(const t of times) {
            person += Math.floor(mid / t);
            if(person >= n) break;
        }
        
        if(person >= n) {
            answer = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
        
    }
    return answer;
}
