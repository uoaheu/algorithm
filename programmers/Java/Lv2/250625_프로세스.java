/*
[문제] 프로세스 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42587

[접근]
- 문서들을 (인덱스, 중요도) 형태로 Queue에 저장
- 가장 높은 중요도를 PriorityQueue로 관리
- Queue에서 문서를 꺼낼 때마다 현재 중요도가 가장 높은지 확인
    - 맞으면 인쇄 (순서 증가)
    - 틀리면 다시 Queue 뒤로 이동
- 내가 요청한 문서(location)가 몇 번째로 인쇄되는지 반환

[성능]
- 시간복잡도: O(N^2) -> 각 문서마다 우선순위 확인
- 하지만 N ≤ 100 이므로 충분히 빠름
- PriorityQueue를 통해 최대값 비교 효율화

[배운 점]
- Queue와 PriorityQueue를 함께 쓰면 순서 + 우선순위 관리가 깔끔하게 가능
- int[]를 Queue에 넣으면 index, value를 함께 관리할 수 있어 유용
*/

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> queue = new LinkedList<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        // (인덱스, 중요도) 형태로 큐에 삽입
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new int[] { i, priorities[i] });
            priorityQueue.offer(priorities[i]);
        }

        int order = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            // 현재 문서의 중요도가 가장 높다면 인쇄
            if (current[1] == priorityQueue.peek()) {
                order++;
                priorityQueue.poll(); // 중요도 하나 소진

                if (current[0] == location) {
                    return order;
                }
            } else {
                // 다시 큐 뒤로
                queue.offer(current);
            }
        }

        return -1; // 이론상 도달 불가
    }
}
