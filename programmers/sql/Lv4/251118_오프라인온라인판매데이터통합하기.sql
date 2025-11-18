/*
[문제] 오프라인/온라인 판매 데이터 통합하기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/131537

[접근]
- 온라인과 오프라인 테이블 구조가 다르므로 동일하게 컬럼을 맞추고 UNION ALL로 합치기
- OFFLINE_SALE에는 USER_ID가 없기 때문에 NULL AS USER_ID로 통일 및 2022년 3월 데이터만 필터링

[성능]
- 시간복잡도: O(N+M) + O(KlogK)

[배운 점]
- 서로 다른 테이블의 컬럼 개수가 맞지 않아도 별칭과 NULL을 이용해 통일 가능
*/

(
SELECT DATE_FORMAT(SALES_DATE, '%Y-%m-%d') AS SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
FROM ONLINE_SALE
WHERE SALES_DATE BETWEEN '2022-03-01' AND '2022-03-31'
)
UNION ALL
(
SELECT DATE_FORMAT(SALES_DATE, '%Y-%m-%d') AS SALES_DATE, PRODUCT_ID, NULL AS USER_ID, SALES_AMOUNT
FROM OFFLINE_SALE
WHERE SALES_DATE BETWEEN '2022-03-01' AND '2022-03-31'
)
ORDER BY SALES_DATE, PRODUCT_ID, USER_ID;
