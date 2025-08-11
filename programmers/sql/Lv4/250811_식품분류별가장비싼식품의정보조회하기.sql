/*
[문제] 식품분류별 가장 비싼 식품의 정보 조회하기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/131116

[접근]
- 카테고리별 최대 가격을 서브쿼리로 구한 뒤 (CATEGORY, PRICE) 튜플 매칭으로 원본 테이블과 연결
- 관심 카테고리(과자, 국, 김치, 식용유)만 필터링 후, 최대 가격 내림차순 정렬

[성능]
- 시간복잡도: O(N) ~ O(N log N)

[배운 점]
- GROUP BY 후 비집계 컬럼(PRODUCT_NAME)을 직접 SELECT하면 불명확/오류 -> 집계값과 행 매칭 필요
- (컬럼1, 컬럼2) IN (서브쿼리) 형태로 "최댓값을 가진 행"을 정확히 선택 가능
*/

SELECT CATEGORY, PRICE AS MAX_PRICE, PRODUCT_NAME 
FROM FOOD_PRODUCT 
WHERE (CATEGORY, PRICE) IN (
    SELECT CATEGORY, MAX(PRICE) 
    FROM FOOD_PRODUCT
    GROUP BY CATEGORY
    )
    AND CATEGORY IN ('과자', '국', '김치', '식용유')
ORDER BY MAX_PRICE DESC
