/*
[문제] 타겟 넘버 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/43165

[접근]
- 각 숫자마다 + 또는 -를 선택할 수 있으므로, DFS로 모든 조합을 탐색
- 재귀 함수 dfs(index, sum)을 통해 현재 인덱스까지의 누적합을 관리
- 배열의 끝까지 탐색한 후, 누적합이 target과 같으면 answer++

[성능]
- 시간복잡도: O(2^N)

[배운 점]
- DFS를 활용해 완전탐색 문제를 해결하는 방법
- 인덱스와 누적합을 함께 관리하면 불필요한 상태 저장 없이 탐색 가능
*/

function solution(numbers, target) {
    let answer = 0;
    function dfs(index, sum) {
        if (index === numbers.length) {
            if (sum === target) {
                answer++;
            }
            return;
        }
        
        dfs(index + 1, sum + numbers[index]);
        dfs(index + 1, sum - numbers[index]);
    }
    dfs(0, 0);
    return answer;
}
