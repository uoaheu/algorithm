/*
[문제] 즐겨찾기가 가장 많은 식당 정보 출력하기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/131123

[접근]
- FOOD_TYPE별로 즐겨찾기 수(FAVORITES)의 최대값 구한 뒤 원본 테이블과 JOIN하여 해당 최대 즐겨찾기를 갖는 식당의 상세 정보 출력
- GROUP BY + MAX()로 서브쿼리 구성 -> ON절에서 FOOD_TYPE과 FAVORITES 동시 비교

[성능]
- 시간복잡도: O(N) 그룹화 + O(N) 조인 → 전체적으로 O(N)

[배운 점]
- GROUP BY + MAX()로 그룹 내 최대값 추출 후 JOIN으로 전체 행 정보 복원 가능
- WHERE절에서 튜플 IN 사용하려고 했으나 복잡해지는 문제 발견, JOIN 방식이 훨씬 안전
*/

SELECT r.FOOD_TYPE, r.REST_ID, r.REST_NAME, m.FAVORITES
FROM REST_INFO r
JOIN (
    SELECT FOOD_TYPE, MAX(FAVORITES) AS FAVORITES
    FROM REST_INFO
    GROUP BY FOOD_TYPE
) m
on r.FAVORITES = m.FAVORITES AND r.FOOD_TYPE = m.FOOD_TYPE
ORDER BY FOOD_TYPE DESC;
