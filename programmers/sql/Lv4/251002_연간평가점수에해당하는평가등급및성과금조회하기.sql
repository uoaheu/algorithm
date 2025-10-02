/*
[문제] 연간 평가점수에 해당하는 평가 등급 및 성과금 조회하기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/284528

[접근]
- HR_GRADE 테이블 내 사원의 반기별 평가 점수 -> 한 사원당 2행 존재
- 사원별 평균 점수를 구한 뒤, 평균 점수에 따라 등급(GRADE)과 성과금(BONUS) 계산
- HR_EMPLOYEES 테이블과 EMP_NO 기준으로 조인
- GROUP BY로 사원별 집계 후, EMP_NO 순 정렬

[성능]
- 시간복잡도: O(N) 

[배운 점]
- 동일 사원이 여러 행으로 존재할 때 중복 출력을 막으려면 GROUP BY로 집계 필요
- 단순 조인만 하면 사원당 여러 반기 점수가 있어 중복 행 발생
*/

SELECT G.EMP_NO, 
    E.EMP_NAME,
    CASE 
        WHEN AVG(G.SCORE) >= 96 THEN 'S'
        WHEN AVG(G.SCORE) >= 90 THEN 'A'
        WHEN AVG(G.SCORE) >= 80 THEN 'B'
        ELSE 'C' END AS GRADE,
    CASE 
        WHEN AVG(G.SCORE) >= 96 THEN E.SAL * 0.20
        WHEN AVG(G.SCORE) >= 90 THEN E.SAL * 0.15
        WHEN AVG(G.SCORE) >= 80 THEN E.SAL * 0.10
        ELSE 0 END AS BONUS
FROM HR_GRADE G JOIN HR_EMPLOYEES E ON E.EMP_NO = G.EMP_NO
GROUP BY E.EMP_NO, E.EMP_NAME, E.SAL
ORDER BY 1 ASC;
