/*
[문제] 의상 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42578

[접근]
- 의상 배열을 순회하며 종류(type) -> 개수 집계
- 각 종류마다 안 입는 선택지를 포함해 (개수 + 1)을 전부 곱함
- 마지막에 전부 안 입는 경우 1가지를 제외(-1)

[성능]
- 시간 복잡도: O(N + K)

[배운 점]
- 조합의 곱셈 원리: 독립적인 선택지는 곱으로 결합됨
- 전체에서 전부 미선택 1가지를 제외 (마지막 -1)
*/

function solution(clothes) {
    const typeCnt = {};
    
    // 의상 종류별 수
    clothes.forEach(([_, type]) => {
        typeCnt[type] = (typeCnt[type] || 0) + 1;
    });
 
    // 경우의 수
    let result = 1;
    Object.values(typeCnt).forEach(cnt => {
        result *= (cnt + 1);
    })
    
    // 전부 안입는 경우 제외
    return result - 1;
}

