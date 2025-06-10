/*
[문제] 그룹별 조건에 맞는 식당 목록 출력하기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/131124

[접근]
- REST_REVIEW 테이블에서 회원별 리뷰 개수를 카운트
- 가장 많은 리뷰를 작성한 회원 찾기 (MAX 함수 활용)
- MEMBER_PROFILE과 REST_REVIEW를 MEMBER_ID 기준으로 JOIN
- 해당 회원의 모든 리뷰 정보 조회 (회원명, 리뷰 내용, 리뷰 작성일)
- 리뷰 작성일 오름차순, 리뷰 내용 오름차순 정렬

[성능]
- 서브쿼리가 중첩되어 있어 대용량 데이터에서는 성능 이슈 가능
- 윈도우 함수나 CTE 사용 시 더 효율적일 수 있음

[배운 점]
- HAVING절에서 집계함수와 서브쿼리를 함께 사용하여 조건 설정 가능
- DATE_FORMAT 함수로 날짜 형식 변환 ('%Y-%m-%d' 형태)
- 서브쿼리에서 별칭 사용 필수
*/

SELECT 
 p.MEMBER_NAME, 
 r.REVIEW_TEXT, 
 DATE_FORMAT(r.REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM 
 MEMBER_PROFILE AS p
JOIN 
 REST_REVIEW AS r
ON 
 p.MEMBER_ID = r.MEMBER_ID
WHERE 
 p.MEMBER_ID IN (
   SELECT MEMBER_ID
   FROM REST_REVIEW
   GROUP BY MEMBER_ID
   HAVING COUNT(*) = (
       SELECT MAX(REVIEW_COUNT)
       FROM (
           SELECT MEMBER_ID, COUNT(*) AS REVIEW_COUNT
           FROM REST_REVIEW
           GROUP BY MEMBER_ID
       ) AS REVIEW_COUNTS
   )
 )
ORDER BY 
 REVIEW_DATE ASC, 
 REVIEW_TEXT ASC;
