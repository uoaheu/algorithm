/*
[문제] 야근 지수 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/12927

[접근]
- 남은 작업량이 큰 일부터 줄여야 야근 지수를 최소화할 수 있으므로 우선순위큐 사용
- n시간 동안 매번 가장 큰 작업량을 1 감소시키는 과정 반복

[성능]
- 시간복잡도: O(n log N)

[배운 점]
- 가장 큰 값을 반복적으로 선택해서 줄이는 문제는 최대힙 우선순위큐 사용
*/

import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int w : works) {
            pq.offer(w);
        }
        
        while(n > 0) {
            if(pq.isEmpty()) {
                return 0;
            }
            
            int work = pq.poll();
            if(work != 1) {
                pq.offer(work - 1);
            }
            
            n--;
        }
        
        while(!pq.isEmpty()) {
            int num = pq.poll();
            answer += num * num;
        }
        return answer;
    }
}
