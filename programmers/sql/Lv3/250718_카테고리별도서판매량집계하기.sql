/*
[문제] 카테고리 별 도서 판매량 집계하기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/144855

[접근]
- BOOK 테이블과 BOOK_SALES 테이블을 BOOK_ID 기준으로 JOIN
- BOOK_SALES에서 2022년 1월 한 달간의 판매 데이터 필터링
- CATEGORY를 기준으로 그룹화하여 카테고리별 총 판매량 집계
- CATEGORY 기준 오름차순 정렬

[성능]
- 시간복잡도: O(N), N은 1월 판매 기록 수

[배운 점]
- 서브쿼리를 활용해 먼저 기간 조건을 걸고 필요한 데이터만 JOIN하면 성능 향상됨
- 날짜 필터 조건과 집계 함수(SUM), GROUP BY 사용법
- ORDER BY에 컬럼명을 숫자(index)로 써도 되지만 가독성을 위해서는 컬럼명을 쓰는게 좋음
*/

SELECT b.CATEGORY, sum(s.SALES) AS TOTAL_SALES
FROM BOOK b
JOIN (
  SELECT BOOK_ID, SALES 
  FROM BOOK_SALES 
  WHERE SALES_DATE BETWEEN '2022-01-01' AND '2022-01-31'
) s ON b.BOOK_ID = s.BOOK_ID
GROUP BY b.CATEGORY
ORDER BY 1;
