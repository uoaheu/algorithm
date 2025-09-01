/*
[문제] H-Index (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42747

[접근]
- H-Index 정의: h번 이상 인용된 논문이 h편 이상
- citations 배열을 내림차순 정렬 이후 i번째 논문이 (i+1)번째 논문까지 h-index 조건을 만족하는지 확인
- citations[i] >= i+1 이면 h = i+1로 갱신, 더 이상 조건이 깨지면 break
[성능]
- 시간 복잡도: O(N log N)

[배운 점]
- H-Index의 두 번째 조건(나머지 논문이 h 이하)은 내림차순 순회에서 자동으로 보장됨
*/

function solution(citations) {
    // 정렬 후 큰 수부터 돌면서 해당 값(h)보다 큰 값이 h개 이상인지
    citations.sort((a, b) => b - a);
    let h = 0;
    for (let i = 0; i < citations.length; i++) {
        if (citations[i] >= i + 1) {
            h = i + 1; // i+1편이 각각 (최소) i+1회 이상 인용됨
        } else {
            break; // 이후는 더 작으므로 더 볼 필요 없음
        }
    }
    return h;
}
