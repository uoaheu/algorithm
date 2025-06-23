/*
[문제] 숫자 짝꿍 (Lv1)
https://school.programmers.co.kr/learn/courses/30/lessons/131128

[접근]
- 두 문자열 X, Y의 숫자 출현 횟수를 기록한 배열을 만든다
- 0~9까지 숫자에 대해, 두 배열에서 공통으로 등장하는 최소 횟수만큼 문자열에 추가
- 9부터 0까지 역순으로 숫자를 조합하면 가장 큰 수가 만들어짐
- 예외 처리: 공통 숫자가 없으면 -1, 결과가 전부 0이면 0

[성능]
- 시간복잡도: O(N)
- 공간복잡도: O(1) (숫자 0~9에 대한 고정 크기 배열)

[배운 점]
- 문자열 비교 시 특정 문자들의 빈도를 세는 방식은 해시 또는 고정 크기 배열로 효율화할 수 있음
- 정렬 대신 숫자 크기 기준 반복 조합으로 성능 개선 가능
*/

function solution(X, Y) {
  const countX = Array(10).fill(0);
  const countY = Array(10).fill(0);

  // 각 숫자 등장 횟수 카운트
  for (let ch of X) countX[+ch]++;
  for (let ch of Y) countY[+ch]++;

  let result = '';

  // 큰 수부터 조합
  for (let d = 9; d >= 0; d--) {
    const minCount = Math.min(countX[d], countY[d]);
    result += d.toString().repeat(minCount);
  }

  // 예외 처리
  if (result === '') return "-1";
  if (result[0] === '0') return "0";
  return result;
}
