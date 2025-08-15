/*
[문제] 주식가격 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42584

[접근]
- 가격이 떨어지는 순간에 해당 인덱스의 정답을 확정하는 단조 스택 사용
- 스택에는 아직 하락을 겪지 않은 인덱스들을 저장
- 현재 가격이 스택 top 인덱스의 가격보다 낮아지면 pop 하면서 (현재idx - 그idx)로 기간 확정
- 끝까지 하락하지 않은 인덱스들은 (마지막idx - 자기idx)로 일괄 처리

[성능]
- 시간 복잡도: O(N) 

[배운 점]
- 유효 구간이 깨지는 시점에 확정하는 유형은 단조 스택이 효과적
- 가격/길이/높이 등 단조성 유지 -> 조건이 깨질 때까지 pop하며 정답을 채운다
*/

function solution(prices) {
  const n = prices.length;
  const answer = Array(n).fill(0);
  const stack = []; // 아직 하락을 맞지 않은 인덱스들

  for (let i = 0; i < n; i++) {
    // 현재 가격이 더 낮아진 순간, 이전 인덱스들의 기간 확정
    while (stack.length && prices[i] < prices[stack[stack.length - 1]]) {
      const idx = stack.pop();
      answer[idx] = i - idx;
    }
    stack.push(i);
  }

  // 끝까지 하락하지 않은 인덱스들 처리
  while (stack.length) {
    const idx = stack.pop();
    answer[idx] = n - 1 - idx;
  }

  return answer;
}
