/*
[문제] 조건에 맞는 아이템들의 가격의 총합 구하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/273709

[접근]
- ITEM_INFO 테이블에서 RARITY가 'LEGEND'인 아이템들의 PRICE 합산
- 방법1: WHERE로 먼저 LEGEND만 필터링한 후 SUM(PRICE) 집계 가능
- 방법2: GROUP BY + HAVING을 사용한 방식
- RARITY 기준으로 그룹핑하고 LEGEND인 그룹만 필터링하고 가격 합계를 구함

[성능]
- 시간복잡도: O(N) (한 번의 GROUP BY 집계 연산)

[배운 점]
- WHERE은 데이터 필터링, HAVING은 집계 이후 조건 필터링에 사용
- 단일 그룹 조건이라면 WHERE로 더 간결하게 처리 가능하다는 점을 배움
- SUM(컬럼)은 NULL 값을 무시하므로 안정적인 집계가 가능함
*/

SELECT SUM(PRICE) AS TOTAL_PRICE 
FROM ITEM_INFO
GROUP BY RARITY
HAVING RARITY = 'LEGEND';
