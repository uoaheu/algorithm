/*
[문제] 타겟 넘버 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/43165

[접근]
- 각 숫자마다 + 또는 -를 붙여서 모든 조합을 시도해야 하므로 DFS 방식으로 탐색
- 현재 인덱스의 숫자를 더하거나 빼고 다음 숫자로 이동하는 재귀 구조 구성
- 마지막 인덱스까지 도달했을 때 누적합이 target과 같다면 경우의 수 +1

[성능]
- 시간복잡도: O(2^N) (각 숫자마다 + 또는 - 두 가지 선택)

[배운 점]
- DFS를 사용하여 완전탐색 문제를 해결할 수 있음
- 재귀 함수의 종료 조건과 누적값 관리가 중요
*/

public class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    // dfs 메서드: 현재 인덱스의 숫자를 더하거나 뺀 후 다음 숫자로 진행
    public int dfs(int[] numbers, int target, int index, int sum) {
        // 모든 숫자를 다 사용한 경우
        if(index == numbers.length) {
            if(sum == target) {
                return 1;
            } else {
                return 0;
            }
        }
        
        // 현재 숫자를 더하는 경우와 빼는 경우 모두 재귀 호출
        return dfs(numbers, target, index + 1, sum + numbers[index])
                + dfs(numbers, target, index + 1, sum - numbers[index]); 
    }

}
