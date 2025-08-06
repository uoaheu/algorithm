/*
[문제] 다리를 지나는 트럭 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42583

[접근]
- 다리를 큐로 표현하고, 다리 길이만큼 큐를 유지
- 매 초마다 큐의 맨 앞 트럭을 꺼내고 다음 트럭이 현재 다리 위 총 무게와 비교해서 올라갈 수 있으면 추가
- 올라갈 수 없으면 0을 추가해 다리 위에 아무것도 못 올라간 상태 유지
- 모든 트럭이 다리에 진입한 후에도 마지막 트럭이 빠져나가는 시간을 위해 bridge_length를 더해 반환

[성능]
- 시간 복잡도: O(N)

[배운 점]
- 트럭 무게를 추적하는 변수(w)를 따로 관리하면 계산이 간단해짐
*/

import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<>();
        int time = 0;
        int w = 0;
        int index = 0;
        
        for(int i = 0; i < bridge_length; i++) {
            q.offer(0);
        }
        
        while(index < truck_weights.length) {
            time++;
            w -= q.poll(); // 맨 앞 트럭 빼기
            
            if(w + truck_weights[index] <= weight) {
                q.offer(truck_weights[index]);
                w += truck_weights[index];
                index++;
            } else {
                q.offer(0);
            }
        }
        
        return time + bridge_length;
    }
}
