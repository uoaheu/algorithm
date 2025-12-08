/*
[문제] 게임 맵 최단거리 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/1844

[접근]
- 2차원 격자에서 시작점(0,0)부터 도착점(n - 1,m - 1)까지의 최단 거리를 구하는 문제
- 최단 경로는 BFS활용, 방문 배열을 사용해서 같은 칸을 중복 방문하지 않고 불필요한 탐색 방지

[성능]
- 시간 복잡도: O(N * M) 

[배운 점]
- 처음에 shift()를 활용했지만 시간 초과 발생
- head 포인터 기반 큐 구현 방식으로 성능 향상
*/

function solution(maps) {
    const n = maps.length;
    const m = maps[0].length;
    const dict = [[-1, 0], [1, 0], [0, -1], [0, 1]];
    
    // 큐 + head 포인터
    const q = [];
    let head = 0;
    q.push([0, 0, 1]); // 시작점(x, y), 거리
    
    const visited = Array.from({ length: n}, () => Array(m).fill(false));

    visited[0][0] = true;
    
    while (head < q.length) { // 기존 : while(q.length) {
        const [x, y, d] = q[head++]; // 기존 : const [x, y, d] = q.shift();

        if(x === n - 1 && y === m - 1) {
            return d;
        }
        
        for(const [dx, dy] of dict) {
            const nx = x + dx;
            const ny = y + dy;
            if(nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx][ny] === 1 && !visited[nx][ny]) {
                visited[nx][ny] = true;
                q.push([nx, ny, d + 1]);
            }
        }
    }
    return -1;
}
