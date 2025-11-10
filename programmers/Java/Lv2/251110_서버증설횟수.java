/*
[문제] 서버 증설 횟수 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/389479

[접근]
- 각 시간대마다 필요한 서버 수 계산
- 사용자가 m명 늘어날 때마다 서버 1대 필요하므로, 필요 서버 수 = players[i] / m
- 서버는 증설 후 k시간이 지나면 반납
- 증설 시 만료시각을 우선순위큐에 저장하여 자동 관리

[성능]
- 시간 복잡도: O(24 × logS) 

[배운 점]
- 만료 시간을 관리해야 하는 문제는 우선순위큐로 시뮬레이션하면 해결 가능
- 증설 후 k시간 뒤 반납을 그대로 '큐에 만료시각 저장' 형태로 옮기면 됨
*/

import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int added = 0; // 총 증설 횟수
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 각 서버의 만료 시각

        for (int i = 0; i < 24; i++) {
            // 1. 만료된 서버 반납
            while (!pq.isEmpty() && pq.peek() <= i) {
                pq.poll();
            }

            // 2. 이번 시간대에 필요한 추가 서버 수
            int need = players[i] / m; // m명마다 1대, m 미만이면 0
            int active = pq.size();

            // 3. 부족하면 즉시 증설
            if (need > active) {
                int toAdd = need - active;
                added += toAdd;
                for (int j = 0; j < toAdd; j++) {
                    pq.offer(i + k); // k시간 뒤 만료
                }
            }
            
        }
        return added;
    }
}
