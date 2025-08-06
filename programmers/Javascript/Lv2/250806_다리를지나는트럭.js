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

function solution(bridge_length, weight, truck_weights) {
    const queue = Array(bridge_length).fill(0);
    let time = 0;
    let w = 0;
    let index = 0;
    
    while(index < truck_weights.length) {
        time++;
        w -= queue.shift();
        
        if(w + truck_weights[index] <= weight) {
            queue.push(truck_weights[index]);
            w += truck_weights[index];
            index++;
        } else {
            queue.push(0);
        }
    }
    return time + bridge_length;
}
