/*
[문제] 프로세스 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42587

[접근]
- 각 문서를 {i, p} 형태의 객체로 변환하여, 원래 위치(i)와 중요도(p)를 함께 저장
- 현재 문서보다 우선순위가 더 높은 문서가 큐에 남아 있다면 뒤로 보냄
- 그렇지 않으면 출력 처리 -> answer 증가
- 현재 출력한 문서가 내가 요청한 문서라면 그때의 answer를 반환

[성능]
- 시간복잡도: O(N^2)

[배운 점]
- 큐를 사용할 때 객체 형태로 인덱스와 값을 함께 저장하면 추적이 쉬움
- 배열에서 조건에 맞는 요소가 존재하는지 확인할 때 some() 메서드를 효과적으로 활용할 수 있음
- shift()를 활용해서 배열의 맨 앞 요소 꺼내는 방식 습득
*/

function solution(priorities, location) {
    var answer = 0;
    
    const queue = priorities.map((p, i) => ({i, p}));
    
    while (queue.length > 0) {
        const curr = queue.shift();
        const high = queue.some(doc => doc.p > curr.p);
        
        if (high) {
            queue.push(curr);
        } else {
            answer++;
            if (curr.i === location) {
                return answer;
            }
        }
    }
}
