/*
[문제] 주차 요금 계산 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/92341

[접근]
- 차량 번호별 누적 주차 시간을 구한 뒤 요금 계산하는 문제
- IN/OUT 기록을 순회하면서
  1) IN이면 해당 차량의 입차 시각을 map(car -> inTime)에 저장
  2) OUT이면 outTime - inTime을 누적 시간에 더하고 map에서 제거
- 모든 기록 처리 후에도 map에 남아있는 차량은 출차 기록이 없는 것이므로 23:59에 출차한 것으로 처리하여 누적 시간에 더함
- 마지막으로 차량 번호 오름차순 정렬 후 정렬된 순서대로 누적 시간으로 요금을 계산해 int[]에 담아 반환

[성능]
- 시간 복잡도: O(R + N log N)
  - records 순회: O(R)
  - 차량 번호 정렬: O(N log N) (N = 차량 수)
  - 최종 계산 순회: O(N)

[배운 점]
- HashMap은 순서가 없으므로 차량 번호 오름차순 요구사항은 keySet()을 List로 변환 후 정렬하거나 TreeMap을 사용해야 함
- 정수 나눗셈에서 올림이 필요한 요금/시간 단위 계산은 (a + b - 1) / b 공식을 쓰면 double 없이 안전하게 처리 가능
*/

import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int originT = fees[0];
        int originF = fees[1];
        int T = fees[2];
        int F = fees[3];
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> newmap = new HashMap<>();
        
        for(String r : records) {
            int h = Integer.parseInt(r.substring(0, 2));
            int m = Integer.parseInt(r.substring(3, 5));
            int time = h * 60 + m;
            int car = Integer.parseInt(r.substring(6, 10));
            String type = r.substring(11, 13);
            
            if(type.equals("IN")) {
                map.put(car, time);
            } else {
                int saveTime = time - map.get(car);
                newmap.put(car, newmap.getOrDefault(car, 0) + saveTime);
                // map에서 빼기
                map.remove(car);
            }
        }
        
        // map에 남아있는 자동차는 23:59에 출차
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            
            int saveTime = (23 * 60 + 59) - value;
            newmap.put(key, newmap.getOrDefault(key, 0) + saveTime);
        }
        
        // newmap을 key 기준으로 정렬 -> key 순서대로 value를 꺼내서 int[]에 저장
        // (1) key만 뽑아서 List로 변환
        List<Integer> keys = new ArrayList<>(newmap.keySet());

        // (2) key 정렬
        Collections.sort(keys);

        // (3) 정렬된 key 순서대로 value를 int[]에 저장
        int[] values = new int[keys.size()];
        int idx = 0;
        for (int key : keys) {
            int time = newmap.get(key);
            if(time > originT) {
                values[idx++] = originF + ((time - originT + (T - 1)) / T * F ); // + (T - 1) : 올림
            } else {
                values[idx++] = originF;
            }
        }

        return values;
    }
}
