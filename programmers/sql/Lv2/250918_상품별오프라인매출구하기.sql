/*
[문제] 상품 별 오프라인 매출 구하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/131533

[접근]
- PRODUCT 테이블과 OFFLINE_SALE 테이블을 PRODUCT_ID 기준으로 JOIN
- 상품 코드별로 그룹핑하여 매출액을 합산
- 매출액 계산은 PRICE * SALES_AMOUNT
- 매출액 합계 기준 내림차순, 매출액이 같으면 상품 코드 오름차순 정렬

[성능]
- 시간복잡도: O(N + M log M)

[배운 점]
- 원하는 결과가 상품별 합계라면 반드시 GROUP BY로 묶어야 함
- ORDER BY에서 컬럼명 대신 컬럼 순번(2, 1)도 사용할 수 있음
*/

SELECT P.PRODUCT_CODE, SUM(P.PRICE * O.SALES_AMOUNT) AS SALES
FROM PRODUCT P
JOIN OFFLINE_SALE O ON P.PRODUCT_ID = O.PRODUCT_ID
GROUP BY P.PRODUCT_CODE
ORDER BY 2 DESC, 1 ASC;
