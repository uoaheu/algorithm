/*
[문제] 더 맵게 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42626

[접근]
- 모든 음식을 K 이상으로 만들기 위해 가장 스코빌 지수가 낮은 두 개를 섞는 연산 반복
- 우선순위 큐를 사용하여 가장 작은 값부터 꺼내기
- 섞은 음식은 다시 우선순위 큐에 넣어 반복 처리

[성능]
- 시간복잡도: O(N log N) -> N개의 원소를 모두 힙에 넣고, 최대 N번 섞는 동안 각 연산마다 log N의 시간 소요

[배운 점]
- 우선순위 큐를 활용하면 최소값/최댓값을 빠르게 다룰 수 있어 그리디 문제 시 유용
- 문제 조건을 마지막 1개 남은 경우까지 정확히 확인해야 한다는 점에서 경계 조건 체크 중요
*/

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int s : scoville) {
            pq.add(s);
        }

        int result = 0;

        while (pq.size() > 1) {
            if (pq.peek() >= K) {
                return result;
            }

            int first = pq.poll();
            int second = pq.poll();
            int mix = first + second * 2;
            pq.add(mix);
            result++;
        }

        // 마지막 하나 남았을 때도 K 이상인지 체크
        return pq.peek() >= K ? result : -1;
    }
}
