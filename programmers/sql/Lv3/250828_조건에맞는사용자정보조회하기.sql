/*
[문제] 조건에 맞는 사용자 정보 조회하기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/164670

[접근]
- 먼저 중고 거래 게시물 3건 이상 등록한 유저 조회
- 이후 중고 거래 게시판 첨부파일 내 해당 유저 아이디와 동일한 유저 조회
- 조회 시 주소는 CITY, STREET_ADDRESS1, STREET_ADDRESS2를 합쳐 출력
- 전화번호는 SUBSTR로 잘라 '-' 포함해 출력
- 회원 ID 기준 내림차순 정렬

[성능]
- 시간복잡도: O(N + M) 

[배운 점]
- IN 서브쿼리와 JOIN 방식의 차이
- CONCAT과 SUBSTR을 활용한 문자열 출력 방식
*/

SELECT 
  USER_ID, 
  NICKNAME, 
  CONCAT(CITY, " ", STREET_ADDRESS1, " ",STREET_ADDRESS2) AS 전체주소, 
  CONCAT(
    SUBSTR(TLNO, 1, 3), '-', 
    SUBSTR(TLNO, 4, 4), '-', 
    SUBSTR(TLNO, 8, 4)
  ) AS 전화번호 
FROM USED_GOODS_USER
WHERE USER_ID IN 
    (
    SELECT WRITER_ID 
    FROM USED_GOODS_BOARD 
    GROUP BY WRITER_ID 
    HAVING COUNT(TITLE) >= 3
    )
ORDER BY USER_ID DESC;
