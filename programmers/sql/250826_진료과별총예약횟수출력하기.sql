/*
[문제] 진료과별 총 예약 횟수 출력하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/132202

[접근]
- 2022년 5월에 예약한 환자 수를 진료과코드 별로 조회
- 진료과별 예약한 환자 수를 기준 오름차순, 진료과 코드를 기준 오름차순 정렬

[성능]
- 시간복잡도: O(S) + O(G log G)

[배운 점]
- 특정 날짜 조건을 확인할 때 BETWEEN 외에도 이용할 수 있음
*/

SELECT MCDP_CD AS 진료과코드, COUNT(PT_NO) AS 5월예약건수
FROM APPOINTMENT
WHERE APNT_YMD BETWEEN '2022-05-01' AND '2022-05-31'
GROUP BY MCDP_CD
ORDER BY COUNT(PT_NO) ASC, MCDP_CD ASC
