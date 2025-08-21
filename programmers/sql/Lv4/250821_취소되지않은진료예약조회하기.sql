/*
[문제] 취소되지 않은 진료 예약 조회하기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/132204

[접근]
- DOCTOR 테이블과 JOIN하여 담당 의사 이름을 가져옴
- 예약일이 '2022-04-13'이고, 취소되지 않았으며(CNCL_YN = 'N'), 진료과 코드가 CS인 경우 필터링
- PATIENT 테이블과 다시 JOIN하여 환자 이름을 가져옴
- 예약일(APNT_YMD) 기준으로 오름차순 정렬

[성능]
- 시간복잡도: O(N)

[배운 점]
- 서브쿼리로 풀었지만 JOIN으로도 풀 수 있음
- DATE() 함수는 편리하지만 인덱스를 타지 않는다는 점 확인
- 문제 조건에 맞게 필요한 컬럼만 SELECT하는 습관
*/

SELECT n.APNT_NO, p.PT_NAME, n.PT_NO, n.MCDP_CD, n.DR_NAME, n.APNT_YMD
FROM (SELECT a.APNT_NO, a.PT_NO, a.MCDP_CD, a.APNT_YMD, d.DR_NAME
FROM APPOINTMENT a JOIN DOCTOR d ON a.MDDR_ID = d.DR_ID
WHERE DATE(a.APNT_YMD) = '2022-04-13' AND a.APNT_CNCL_YN = 'N' AND a.MCDP_CD = 'CS') n
JOIN PATIENT p ON n.PT_NO = p.PT_NO
ORDER BY n.APNT_YMD ASC
