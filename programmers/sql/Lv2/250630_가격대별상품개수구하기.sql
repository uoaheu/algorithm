/*
[문제] 가격대 별 상품 개수 구하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/131530

[접근]
- TRUNCATE(price, -4)를 사용하면 가격을 10,000 단위로 내림 처리할 수 있음
  ex) 12345 -> 10000, 98765 -> 90000
- 이 값을 기준으로 GROUP BY를 하면 같은 가격대끼리 묶을 수 있음
- 정렬 기준은 가격대 오름차순이므로 ORDER BY 1 사용

[성능]
- TRUNCATE(price, -4)는 인덱스를 무시하고 계산이 필요하므로 성능상 이슈가 있을 수 있음 (하지만 문제 범위가 작다면 부담은 크지 않음)
- GROUP BY, COUNT 연산은 전체 스캔 필요

[배운 점]
- 숫자 범위별 그룹화는 TRUNCATE 또는 CASE WHEN 등 다양한 방식으로 구현 가능
- SQL에서도 수치 연산을 활용해 범주화가 가능함을 배움
*/

SELECT
    TRUNCATE(PRICE, -4) AS PRICE_GROUP, -- 10,000 단위로 가격대 그룹화
    COUNT(PRODUCT_ID) AS PRODUCTS -- 각 그룹별 상품 개수 계산
FROM
    PRODUCT
GROUP BY
    1 -- price_group으로 그룹화
ORDER BY
    1; -- price_group 오름차순 정렬
