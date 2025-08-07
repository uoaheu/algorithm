/*
[문제] 가장 먼 노드 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/49189

[접근]
- 무방향 그래프를 인접 리스트로 구성
- BFS를 이용해 1번 노드로부터 각 노드까지의 최단 거리를 계산
- distance 배열 초기화 후, 방문한 노드를 queue에 추가하며 거리 갱신
- BFS 수행 후 최댓값을 찾고, 그 값과 동일한 노드 개수를 반환

[성능]
- 시간 복잡도: O(N + M)

[배운 점]
- BFS는 가중치가 동일한 그래프에서 최단 경로 계산할 때 효과적
- 배열 전개, slice, filter 등의 JS 메서드 활용하여 가독성 좋은 코드 구현법
*/

function solution(n, edge) {
    const graph = Array.from({ length : n + 1 }, () => []);
    for(const [a, b] of edge) {
        graph[a].push(b);
        graph[b].push(a);
    }
    
    const distance = Array( n + 1 ).fill(-1); // 1부터 떨어진 거리 표시
    
    distance[1] = 0;
    const queue = [1];
    
    while(queue.length) {
        const node = queue.shift();
        for(const num of graph[node]) {
            if(distance[num] === -1) {
                distance[num] = distance[node] + 1;
                queue.push(num);
            }
        }
        
    }
    
    const maxDist = Math.max(...distance.slice(1));
    return distance.slice(1).filter(d => d === maxDist).length;
}
