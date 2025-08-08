/*
[문제] 카펫 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42842

[접근]
- 전체 격자 수 N = brown + yellow로 두고, N의 약수 쌍 (가로, 세로)을 탐색
- 세로 i(≥3)를 고정하면 가로는 N / i
- 테두리 갈색 수 공식: brown = 2*가로 + 2*(세로-2)
- 위 식을 만족하는 (가로, 세로) 쌍을 반환

[성능]
- 시간 복잡도: O(N)

[배운 점]
- 직사각형의 테두리 타일 수를 공식으로 계산: 2W + 2(H-2)
- 약수 쌍(가로, 세로) 탐색으로 경우의 수를 크게 줄일 수 있음
*/

function solution(brown, yellow) {
    const num = brown + yellow; // 총 합
    
    for(let i = 3; i <= num/3; i++) {
        if(num % i === 0) {
            const fair = [num / i, i];
            if(num / i * 2 + (i - 2) * 2 == brown) {
                return fair;
            }
        }
    }
}
