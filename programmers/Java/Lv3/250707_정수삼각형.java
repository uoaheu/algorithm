/*
[문제] 정수 삼각형 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/43105

[접근]
- DP 사용
- 각 위치에서의 최대 누적 합을 저장하며, 아래층으로 내려가며 값을 업데이트
- triangle[i][j] = 이전 단계의 가능한 두 경로 중 더 큰 값을 선택 + 자신의 값
- bottom-up 방식으로 triangle 자체를 갱신하며 사용

[성능]
- 시간 복잡도: O(n^2), n은 삼각형의 높이 (최대 500)
- 공간 복잡도: O(1), triangle 배열을 그대로 재사용해서 메모리 추가 사용 없음

[배운 점]
- 2차원 배열에서 DP로 상태를 갱신할 때, 입력 배열 자체를 재사용하면 공간 최적화 가능
- 삼각형 구조에서도 DP가 적용될 수 있으며, 문제의 조건을 활용한 경로 최적화가 핵심
*/
class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length; // triangle 높이

        // triangle 배열 자체를 dp처럼 사용 
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // 왼쪽 끝 : 위에서만 내려옴
                    triangle[i][j] += triangle[i - 1][j];
                } else if (j == i) {
                    // 오른쪽 끝 : 왼쪽 위에서만 내려옴
                    triangle[i][j] += triangle[i - 1][j - 1];
                } else {
                    // 가운데 : 왼쪽 위, 오른쪽 위 중 큰 값
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                }
            }
        }

        // 마지막 줄에서 최대값 찾기
        int max = 0;
        for (int num : triangle[n - 1]) {
            max = Math.max(max, num);
        }

        return max;
    }
}


