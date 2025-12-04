/*
[문제] 불량 사용자 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/64064

[접근]
- banned_id의 각 패턴에 매칭될 수 있는 user_id 후보들을 리스트 형태로 저장
- 이후 각 banned_id마다 하나씩 매칭되는 user_id를 선택해, 같은 user_id는 두 번 사용할 수 없으므로 DFS + 백트래킹
- 최종 선택된 user_id 조합이 동일하면 한 가지 경우로 보기 때문에 visited 배열을 기준으로 인덱스 조합을 문자열 키로 만들어 Set에 저장하여 중복 제거

[성능]
- 시간복잡도: O(B*U*L + U^B)

[배운 점]
- 조합 중복 제거는 Set에 정규화된 키를 넣는 방식으로 해결 가능
- 리스트 안에 리스트를 넣을 때는 내부 리스트를 반드시 미리 생성해야 IndexOutOfBounds를 피할 수 있음
*/

import java.util.*;

class Solution {
    Set<String> result = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        // banned_id 별 해당하는 user_id
        List<List<String>> list = new ArrayList<>();
        
        // banned_id 개수만큼 미리 빈 리스트 생성
        for (int i = 0; i < banned_id.length; i++) {
            list.add(new ArrayList<>());
        }

        int idx = 0;
        for(String b : banned_id) {
            for(String u : user_id) {
                boolean same = true;
                if(u.length() != b.length()) {
                    continue;
                }
                for(int i = 0; i < b.length(); i++) {
                    
                    if(b.charAt(i) == '*') {
                        continue;
                    }
                    if(b.charAt(i) != u.charAt(i)) {
                        same = false;
                        break;
                    }
                }
                if(same) {
                    list.get(idx).add(u);
                }
            }
            idx++;    
        }
        
        // user_id -> index 매핑 (visited 관리를 위해)
        Map<String, Integer> userIndex = new HashMap<>();
        for (int i = 0; i < user_id.length; i++) {
            userIndex.put(user_id[i], i);
        }

        boolean[] visited = new boolean[user_id.length];

        dfs(0, list, visited, userIndex, user_id);

        // 가능한 조합의 개수
        return result.size();
    }
    
    private void dfs(int depth, List<List<String>> list, boolean[] visited, Map<String, Integer> userIndex, String[] user_id) {
        if (depth == list.size()) {
            // 현재 visited를 기반으로 조합을 문자열 키로 변환
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    sb.append(i).append(','); // 인덱스로만 키를 만들면 자동 정렬 효과
                }
            }
            result.add(sb.toString());
            return;
        }

        // 현재 depth에 해당하는 banned 패턴에서 가능한 user 후보들 순회
        for (String user : list.get(depth)) {
            int idx = userIndex.get(user);
            if (visited[idx]) continue; // 이미 다른 banned에 사용된 유저면 건너뛰기
            visited[idx] = true;
            dfs(depth + 1, list, visited, userIndex, user_id);
            visited[idx] = false; // 백트래킹
        }
    }
}
