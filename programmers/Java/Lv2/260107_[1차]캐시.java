/*
[문제] [1차] 캐시 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/17680

[접근]
- 캐시에 도시 이름이 존재하면 cache hit로 1초가 소요, 존재하지 않으면 cache miss로 5초가 소요
- 캐시가 가득 찬 상태에서 miss가 발생하면 가장 오래 사용되지 않은 도시 제거
- 도시 이름의 대소문자는 구분하지 않으므로 모두 소문자로 변환해서 처리

[성능]
- 시간 복잡도: O(N × C) (N: cities 배열 길이, C: cacheSize)
  
[배운 점]
- LRU 캐시는 FIFO 큐가 아니라 사용 시점에 따라 순서를 갱신해야 하는 자료구조임을 이해함
- cacheSize가 0인 경우를 예외 처리하지 않으면 불필요한 연산이 발생할 수 있음을 배움
*/

import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length * 5;
        
        List<String> cache = new LinkedList<>();
        int time = 0;
        for(String city : cities) {
            city = city.toLowerCase();

            // city가 이미 존재했다면 지워지고 true
            if(cache.remove(city)) {
                time += 1;
                cache.add(city); // 최신으로 갱신
            } else {
                time += 5;
                if(cache.size() == cacheSize) {
                    cache.remove(0);
                }
                cache.add(city);
            }
        }
        return time;
    }
}
