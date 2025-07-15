/*
[문제] 가장 큰 수 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42746

[접근]
- 정수 배열을 문자열로 변환한 뒤 정렬 기준을 직접 정의
- 두 숫자 a, b에 대해 (a + b)와 (b + a)를 비교해서 더 큰 쪽이 먼저 오도록 정렬
- 정렬된 문자열들을 모두 이어붙이면 가장 큰 수가 됨
- 모든 값이 0으로 시작하는 경우 "0"만 반환

[성능]
- 시간복잡도: O(N log N)
- 공간복잡도: O(N)

[배운 점]
- 숫자 배열을 문자열로 변환하면 문자열 결합 기반 비교 정렬 가능
- JavaScript의 sort()에서 문자열 비교는 localeCompare()를 활용
- 배열의 join('')을 사용해서 문자열 붙이기 가능
*/

function solution(numbers) {
    // 숫자 배열을 문자열 배열로 변환
    let arr = numbers.map(String);

    // 정렬: (b + a) > (a + b) 이면 b가 앞에 오도록
    arr.sort((a, b) => (b + a).localeCompare(a + b));

    // 가장 앞이 0이면 전부 0이므로 "0" 반환
    if (arr[0] === "0") return "0";

    // 문자열 합치기
    return arr.join('');
}
