/*
[문제] 전화번호 목록 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42577

[접근]
- 문자열 배열을 사전순으로 정렬하면, 접두어 관계에 있는 문자열들이 인접하게 배치됨
- 따라서 정렬 후 바로 옆 원소끼리만 비교하면 접두어 여부를 확인할 수 있음
- 접두어가 존재하면 false를 반환하고, 끝까지 없으면 true 반환

[성능]
- 시간복잡도: O(n log n) (정렬) + O(n * m) (startsWith에서 평균 m 길이 비교)
  -> n은 전화번호 개수, m은 평균 문자열 길이

[배운 점]
- startsWith()를 활용한 접두어 탐지 방법
- 사전순 정렬로 문자열 간 접두어 관계를 효율적으로 탐지 가능
- 사전순 정렬 후 인접 비교만으로 문제를 간단하게 풀 수 있음
*/

function solution(phone_book) {
  phone_book.sort();

  for (let i = 0; i < phone_book.length - 1; i++) {
    // 앞 번호가 뒷 번호의 접두어인지 확인
    if (phone_book[i + 1].startsWith(phone_book[i])) {
      return false;
    }
  }

  return true;
}
