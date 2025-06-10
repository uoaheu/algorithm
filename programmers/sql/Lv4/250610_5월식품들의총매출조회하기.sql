/*
[문제] 5월 식품들의 총매출 조회하기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/131117

[접근]
- 5월에 출고된 주문만 필터링 (PRODUCE_DATE가 2022-05-01 ~ 2022-05-31인 경우)
- food_product와 food_order를 PRODUCT_ID 기준으로 JOIN
- 가격 * 수량을 곱한 값들을 상품별로 합산 → 총매출 계산
- GROUP BY로 PRODUCT_ID, PRODUCT_NAME 기준 그룹화
- 총매출 내림차순, PRODUCT_ID 오름차순 정렬

[성능]
- 데이터 양에 따라 JOIN + GROUP BY 성능 영향, 그러나 기본적인 SQL 집계 쿼리 수준

[배운 점]
- GROUP BY에서 쓰이는 1, 2는 컬럼 순서 번호를 의미
- GROUP BY에 SELECT 컬럼이 모두 포함되지 않으면 오류 가능
- ORDER BY에서도 컬럼명과 별칭 혼용 가능
*/

SELECT 
  p.PRODUCT_ID, 
  p.PRODUCT_NAME, 
  SUM(p.PRICE * o.AMOUNT) AS TOTAL_SALES
FROM 
  FOOD_PRODUCT AS p
JOIN 
  FOOD_ORDER AS o
ON 
  p.PRODUCT_ID = o.PRODUCT_ID
WHERE 
  o.PRODUCE_DATE BETWEEN '2022-05-01' AND '2022-05-31'
GROUP BY 
  1, 2
ORDER BY 
  TOTAL_SALES DESC, 
  p.PRODUCT_ID ASC;
