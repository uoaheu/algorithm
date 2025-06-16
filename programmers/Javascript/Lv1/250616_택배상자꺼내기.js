/*
[문제] 택배 상자 꺼내기 (Lv1)
https://school.programmers.co.kr/learn/courses/30/lessons/389478

[접근]
- 총 상자 수 n개를 w개씩 한 층에 쌓으며, 지그재그로 쌓임 (짝수층 : 왼 -> 오, 홀수층 : 오 -> 왼)
- 주어진 상자 번호(num)의 위치를 층/열 기준으로 계산
- 그 열(col)에서 위로 올라가며 상자가 실제로 존재하는 층만 count하여 총 꺼내야 할 수를 구함

[성능]
- 시간복잡도: O(n / w) → 최대 층 수만큼 반복 (최대 약 100회)

[배운 점]
- 지그재그 구조에서 열 번호를 계산할 때 홀짝 여부에 따라 index 반전 필요
- 마지막 층은 상자 수가 w개 미만일 수 있어 층마다 실제 상자 수를 구해야 함
- 열(column)에 상자가 실제 존재하는지 확인할 때 방향(짝/홀)별 범위 조건이 다름
*/

function solution(n, w, num) {
  const layer = Math.floor((num - 1) / w); // num이 위치한 층 번호 (0부터 시작)
  const pos = (num - 1) % w;   // 해당 층 내에서 몇 번째 위치인지 (왼쪽부터 순서)

  const col = (layer % 2 === 0)   // 실제 열 번호 (지그재그 구조) : 짝수층 (왼 -> 오), 홀수층 (오 -> 왼)
    ? pos               // 짝수층 : 그대로 
    : (w - 1 - pos);    // 홀수층 : 반대 방향

  const lastLayer = Math.floor((n - 1) / w); // 가장 위층 번호 계산 (전체 상자 기준)
  let count = 0; // 꺼내야 하는 상자 수

  for (let i = layer; i <= lastLayer; i++) {
    const boxInLayer = Math.min(w, n - i * w); // 해당 층에 실제 들어있는 상자 수 (마지막 층은 w개보다 적을 수 있음)
    if (boxInLayer <= 0) continue; // 상자가 없다면 건너뜀
      
    // 짝수층 (왼 -> 오 방향) : 열 번호가 box 수보다 작아야 해당 위치에 상자가 있음
    if (i % 2 === 0) {
      if (col < boxInLayer) count++; // 상자 있으면 꺼내기
    } 
    // 홀수층 (오 -> 왼 방향) : 오른쪽부터 상자가 채워짐
    else {
      if (col >= w - boxInLayer) count++; // 상자 있으면 꺼내기
    }
  }

  return count; // 꺼내야 하는 상자 개수
}

