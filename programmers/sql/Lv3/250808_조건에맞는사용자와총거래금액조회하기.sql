/*
[문제] 조건에 맞는 사용자와 총 거래금액 조회하기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/164668

[접근]
- 중고거래 게시판(BOARD)과 사용자(USER) 테이블을 조인하여 거래 상태가 DONE인 데이터만 필터링
- 사용자별 총 거래금액을 SUM으로 집계
- 집계 결과가 700000 이상인 사용자만 HAVING 절로 필터링 후 정렬

[성능]
- 시간복잡도: O(N)

[배운 점]
- 집계 조건은 WHERE가 아니라 HAVING 사용해야 함
- GROUP BY 시 SELECT에 포함되는 비집계 컬럼은 모두 그룹에 포함해야 함
*/

SELECT
  U.USER_ID,
  U.NICKNAME,
  SUM(B.PRICE) AS TOTAL_SALES
FROM USED_GOODS_BOARD B JOIN USED_GOODS_USER U ON B.WRITER_ID = U.USER_ID
WHERE B.STATUS = 'DONE'
GROUP BY U.USER_ID, U.NICKNAME
HAVING SUM(B.PRICE) >= 700000
ORDER BY TOTAL_SALES ASC;
