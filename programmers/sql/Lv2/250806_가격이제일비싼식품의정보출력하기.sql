/*
[문제] 가격이 제일 비싼 식품의 정보 출력하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/131115

[접근]
- FOOD_PRODUCT 테이블에서 PRICE가 가장 높은 식품을 조회해야 함
- 서브쿼리로 MAX(PRICE)를 추출한 후, 메인쿼리에서 PRICE = MAX(PRICE) 조건으로 필터링
- 동일한 최고 가격을 가진 식품이 여러 개일 수 있으므로 IN 사용

[성능]
- 시간복잡도: O(N)

[배운 점]
- SELECT MAX(컬럼) 서브쿼리로 최댓값 조건 필터링 가능
- 여러 행이 반환될 수 있는 경우 대비해 IN 사용 (여기선 = 도 가능)
*/

SELECT * FROM FOOD_PRODUCT
WHERE PRICE IN (SELECT MAX(PRICE) FROM FOOD_PRODUCT);
