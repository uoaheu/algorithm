/*
[문제] 네트워크 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/43162

[접근]
- 네트워크는 그래프의 연결 요소(Connected Component) 수를 구하는 문제
- 인접 행렬(computers)을 인접 리스트(graph)로 변환 -> 각 노드가 방문되지 않았다면 DFS 수행 & answer++
- DFS 안에서 연결된 모든 노드를 재귀적으로 방문 처리

[성능]
- 시간복잡도: O(N^2)

[배운 점]
- DFS로 그래프의 연결 요소 수를 구할 수 있음
- 인접 행렬 -> 인접 리스트 변환으로 탐색을 더 직관적으로 구현 가능, 원래 데이터 구조 그대로 활용도 가능하긴 함
- Array.from과 fill, push 사용법 숙지
*/

function solution(n, computers) {
    const visited = new Array(n).fill(false);
    const graph = Array.from({ length: n}, () => []);
    
    // 그래프 만들기
    for(let i = 0; i < n; i++) {
        for(let j = 0; j < n; j++) {
            if (computers[i][j] === 1 && i != j) {
                graph[i].push(j);
            }
        }
    }
    
    function dfs(node) {
        visited[node] = true;
        for(const n of graph[node]) {
            if (!visited[n]) {
                dfs(n);
            }
        }
    }
    
    var answer = 0;
    for(let i = 0; i < n; i++) {
        if (!visited[i]) {
            dfs(i);
            answer++;
        }
    }
    return answer;
}

