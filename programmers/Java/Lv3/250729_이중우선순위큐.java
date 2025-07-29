/*
[문제] 이중우선순위큐 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/42628

[접근]
- 최소값과 최대값을 모두 빠르게 제거하기 위해 최소 힙(minQ)과 최대 힙(maxQ)을 함께 사용
- 삽입 시 두 큐에 모두 넣고, 삭제 시 해당 힙에서 값을 제거한 후 반대쪽 큐에서도 해당 값을 제거
- 마지막에 두 큐 중 하나라도 비어 있으면 [0, 0], 아니면 각각 최대/최소값을 꺼내어 반환

[성능]
- 시간 복잡도: 삽입 O(log N), 삭제 O(N) (remove 연산이 O(N)이기 때문)

[배운 점]
- PriorityQueue는 한 쪽 방향 정렬만 제공하므로, 최대값/최소값을 동시에 처리하려면 두 개의 큐를 활용해야 함
- PriorityQueue의 remove는 O(N)이므로 TreeMap와 같은 최적화 방식도 고려할 수 있음
*/

import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        
        PriorityQueue<Integer> minQ = new PriorityQueue<>(); // 최소
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder()); // 최대
        for(String s : operations) {
            if(s.substring(0,1).equals("I")) {
                minQ.add(Integer.parseInt(s.substring(2)));
                maxQ.add(Integer.parseInt(s.substring(2)));
            } else {
                if(minQ.size() == 0 || maxQ.size() == 0)  {
                    continue;
                } else {
                    if(s.substring(2).equals("1")) {
                        // 최대값 빼내기
                        int max = maxQ.poll();
                        minQ.remove(max); // O(n)
                    } else {
                        // 최소값 빼내기
                        int min = minQ.poll();
                        maxQ.remove(min); // O(n)
                    }   
                }
            }
        }
        int maxN = 0;
        int minN = 0;
        if(maxQ.size() != 0) {
            maxN = maxQ.poll();
        }
        if(minQ.size() != 0) {
            minN = minQ.poll();
        }
        
        int[] answer = {maxN, minN};
        
        
        return answer;
    }
}
