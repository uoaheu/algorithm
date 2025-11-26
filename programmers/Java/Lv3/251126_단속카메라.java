/*
[문제] 단속카메라 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/42884

[접근]
- 차량이 나간 지점 기준으로 정렬
- 가장 빨리 도로를 빠져나가는 지점에 카메라 1대 설치
- 이후 차량을 앞에서부터 돌면서 해당 차량의 진입 지점이 현재 카메라 위치보다 크면 기존 카메라로는 촬영 불가능하므로 새로운 카메라 설치

[성능]
- 시간복잡도: O(N log N)

[배운 점]
- 시작점이 아니라 끝나는 지점 기준 정렬이 핵심
*/

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        // 나간 지점 기준으로 오름차순 정렬
        Arrays.sort(routes, (a, b) -> (a[1] - b[1]));
        
        // 첫 번째 차량이 나간 지점에 카메라 하나 설치했다고 가정
        int result = 1;
        int camera = routes[0][1]; // 현재 카메라 위치

        for (int i = 1; i < routes.length; i++) {
            int start = routes[i][0];

            // 현재 차량의 진입 지점이 카메라 위치보다 크면 이 카메라는 이 차량을 못 찍음 -> 새 카메라 필요
            if (start > camera) {
                result++;
                camera = routes[i][1]; // 이 차량이 나가는 지점에 새 카메라 설치
            }
        }

        return result;
    }
}
