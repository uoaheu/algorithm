/*
[문제] 방문 길이 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/49994

[접근]
- 시작 좌표를 (0, 0)으로 두고 dirs 문자열 순서대로 이동
- 각 이동마다 현재 좌표에서 다음 좌표 계산, 범위를 벗어나는 이동은 무시
- 서로 다른 길의 수를 세는 문제이기 때문에 이동 경로를 문자열로 만들어 Set에 저장 (양방향 저장)
- 처음 지나가는 경로일 때만 answer 증가

[성능]
- 시간 복잡도: O(N) 

[배운 점]
- 양방향 이동을 하나의 경로로 처리해야 할 때는 Set을 활용해서 문자열로 표현하면 간단해짐
*/

import java.util.*;
class Solution {
    public int solution(String dirs) {
        int x = 0;
        int y = 0;
        int answer = 0;
        Set<String> way = new HashSet<>();
        
        for(int i = 0; i < dirs.length(); i++) {
            char c = dirs.charAt(i);
            int nx = x;
            int ny = y;
            
            if(c == 'U') ny--;
            else if(c == 'D') ny++;
            else if(c == 'L') nx--;
            else nx++;
            
            // 경계 체크
            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                continue;
            }
            
            // 경로를 문자열로 저장 (양방향)
            String path1 = x + "," + y + "," + nx + "," + ny;
            String path2 = nx + "," + ny + "," + x + "," + y;
            
            if(!way.contains(path1)) {
                way.add(path1);
                way.add(path2);
                answer++;
            }
            
            x = nx;
            y = ny;
        }
        
        return answer;
    }
}
