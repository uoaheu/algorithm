/* 
[문제] 상품을 구매한 회원 비율 구하기 (Lv4) 
https://school.programmers.co.kr/learn/courses/30/lessons/131534

[접근] 
- 2021년에 가입한 전체 회원 수를 서브쿼리
- USER_ID로 JOIN하고 U.JOINED가 2021년인 사용자만 필터링
- 구매 월별로 DISTINCT 사용자 수를 세야 하므로 COUNT(DISTINCT U.USER_ID)를 사용
- (해당 월 구매 회원 수) / (2021 가입 전체 회원 수), 소수 첫째 자리까지 반올림

[성능] 
- 시간복잡도: O(S)~O(U + S log S))

[배운 점] 
- 구매한 회원 수는 거래 건수가 아니라 '해당 월에 결제 기록이 있는 서로 다른 사용자 수' -> COUNT(DISTINCT USER_ID) 이용
- SELECT 별칭을 GROUP BY, ORDER BY에서 활용 가능, 월 집계는 YEAR(), MONTH()로 추출
*/

SELECT
  YEAR(O.SALES_DATE)  AS YEAR,
  MONTH(O.SALES_DATE) AS MONTH,
  COUNT(DISTINCT U.USER_ID) AS PURCHASED_USERS,
  ROUND(COUNT(DISTINCT U.USER_ID) / (SELECT COUNT(*) FROM USER_INFO WHERE YEAR(JOINED) = 2021), 1) AS PURCHASED_RATIO
FROM USER_INFO U JOIN ONLINE_SALE O ON O.USER_ID = U.USER_ID
WHERE YEAR(U.JOINED) = 2021 -- 가입년도 2021년 
GROUP BY YEAR, MONTH
ORDER BY YEAR, MONTH;
