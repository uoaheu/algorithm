/*
[문제] 둘만의 암호 (Lv1)
https://school.programmers.co.kr/learn/courses/30/lessons/155652

[접근]
- 문자열 s의 각 알파벳을 index만큼 뒤로 민다.
- skip에 포함된 문자는 건너뛰어야 하고 z를 넘어가면 다시 a부터 시작한다.
- 알파벳을 숫자로 변환하여 밀고, skip은 Set으로 빠르게 확인한다.

[성능]
- 시간복잡도: O(s.length * index)
  → 각 문자마다 최대 index번의 이동을 하며, Set 확인은 O(1)

[배운 점]
- 아스키 코드로 알파벳을 순회하며 z에서 a로의 순환 처리 방법
- Set을 이용해 skip 문자를 빠르게 판단하는 최적화 방식
*/

function solution(s, skip, index) {
  const skipSet = new Set(skip); // skip 문자 Set으로 저장해서 확인
  let answer = '';

  for (let char of s) {
    let count = 0;
    let code = char.charCodeAt(0); // 현재 문자의 아스키 코드

    // index만큼 건뛰, skip에 포함되지 않은 알파벳만 카운트
    while (count < index) {
      code++; // 다음 알파벳으로 이동
      if (code > 122) code = 97; // z를 넘어가면 a로

      const nextChar = String.fromCharCode(code);
      if (!skipSet.has(nextChar)) {
        count++; // skip되지 않는 경우에만 카운트 증가
      }
    }

    answer += String.fromCharCode(code); // 최종 이동한 문자 추가
  }

  return answer;
}
