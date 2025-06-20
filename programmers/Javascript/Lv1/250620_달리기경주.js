/*
[문제] 달리기 경주 (Lv1)
https://school.programmers.co.kr/learn/courses/30/lessons/178871

[접근]
- players 배열: 현재 선수 순서
- callings 배열: 앞지른 선수 이름 순서
- 선수 이름을 빠르게 찾기 위해 이름 -> 순위 인덱스를 저장하는 객체 생성
- 추월 시 해당 선수와 앞선 선수의 위치를 서로 바꿈
- 배열과 순위 객체를 동시에 업데이트

[성능]
- 시간복잡도: O(n + m) -> n: players 길이 (초기 맵 생성), m: callings 길이 (한 번의 순위 업데이트당 O(1))

[배운 점]
- 배열 요소 위치를 자주 바꿔야 할 때, 요소의 인덱스를 기록하는 별도 객체를 활용하면 효율적임
- 객체와 배열을 동시에 유지하면서 빠른 업데이트가 가능
*/

function solution(players, callings) {
  const positions = {}; // 이름 -> 현재 인덱스
  players.forEach((name, idx) => {
    positions[name] = idx;
  });

  for (let name of callings) {
    const current = positions[name];
    const front = current - 1;

    // 위치 바꾸기
    const temp = players[front];
    players[front] = players[current];
    players[current] = temp;

    // 맵도 업데이트
    positions[players[current]] = current;
    positions[players[front]] = front;
  }

  return players;
}
