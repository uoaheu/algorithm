/*
[문제] 카테고리 별 상품 개수 구하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/131529

[접근]
- PRODUCT_CODE 앞 2자리를 잘라 카테고리 추출
- 카테고리 기준으로 그룹핑 후 COUNT로 상품 개수 집계
- 결과는 카테고리 오름차순 정렬

[성능]
- 시간복잡도: O(N)

[배운 점]
- 문자열 일부를 기준으로 그룹화할 때 SUBSTRING 활용 가능
- COUNT(컬럼) vs COUNT(*) 차이 (NULL 제외 여부)
- GROUP BY 후 원하는 정렬은 반드시 ORDER BY로 지정
*/
SELECT SUBSTRING(PRODUCT_CODE, 1, 2) AS CATEGORY, COUNT(PRODUCT_ID) AS PRODUCTS FROM PRODUCT
GROUP BY CATEGORY
ORDER BY CATEGORY;
