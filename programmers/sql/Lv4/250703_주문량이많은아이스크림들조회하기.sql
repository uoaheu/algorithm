/*
[문제] 주문량이 많은 아이스크림들 조회하기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/133027

[접근]
- 두 테이블(FIRST_HALF, JULY)에서 아이스크림 별 총 주문량을 각각 구한 뒤 flavor를 기준으로 JOIN하여 각 맛의 총 주문량을 합산
- 주문량을 기준으로 내림차순 정렬하고 상위 3개 선택

[성능]
- 각각의 테이블에서 GROUP BY flavor를 통해 미리 합산 처리
- JOIN 시 불필요한 중복 계산을 방지하여 성능 최적화

[배운 점]
- SQL에서의 합산 정렬 처리 방식
- LIMIT로 Top N 추출 시 ORDER BY와의 결합 중요성 체감
*/

SELECT f.flavor
FROM (
  SELECT flavor, SUM(total_order) AS total
  FROM first_half
  GROUP BY flavor
) AS f
JOIN (
  SELECT flavor, SUM(total_order) AS total
  FROM july
  GROUP BY flavor
) AS j
ON f.flavor = j.flavor
ORDER BY (f.total + j.total) DESC
LIMIT 3;
