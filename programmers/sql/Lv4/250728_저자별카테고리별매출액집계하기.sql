/*
[문제] 저자 별 카테고리 별 매출액 집계하기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/144856

[접근]
- 먼저 2022년 1월 한 달 동안의 판매 기록을 BOOK_SALES 테이블에서 추출
- 해당 판매 기록과 BOOK 테이블을 JOIN하여 저자와 가격 정보를 함께 가져옴
- 저자 ID와 카테고리별로 그룹화하여 매출액(판매량 x 가격)의 합계를 계산
- AUTHOR 테이블과 JOIN하여 저자명을 함께 표시
- 결과는 저자 ID 오름차순, 카테고리 내림차순으로 정렬

[성능]
- 시간복잡도: O(N log N) 수준 (JOIN 및 GROUP BY + 정렬)

[배운 점]
- 중첩 서브쿼리를 사용해 집계 결과를 바깥 쿼리에서 JOIN하여 추가 정보(AUTHOR_NAME 등)를 붙일 수 있음
- SALES * PRICE와 같이 계산을 먼저한 이후 SUM()하는 패턴에 대해 배웠고, 실제 매출 집계에서 자주 사용된다는 것을 알게 됨
*/

SELECT 
    n.AUTHOR_ID, 
    a.AUTHOR_NAME, 
    n.CATEGORY, 
    n.TOTAL_SALES
FROM AUTHOR a
JOIN (
    SELECT 
        b.AUTHOR_ID, 
        b.CATEGORY, 
        SUM(s.SALES * b.PRICE) AS TOTAL_SALES
    FROM BOOK_SALES s
    JOIN BOOK b ON s.BOOK_ID = b.BOOK_ID
    WHERE s.SALES_DATE BETWEEN '2022-01-01' AND '2022-01-31'
    GROUP BY b.AUTHOR_ID, b.CATEGORY
) n ON a.AUTHOR_ID = n.AUTHOR_ID
ORDER BY n.AUTHOR_ID ASC, n.CATEGORY DESC;
