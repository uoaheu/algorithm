/*
[문제] 조건에 맞는 사원 정보 조회하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/284527

[접근]
- HR_GRADE와 HR_EMPLOYEES를 사번(EMP_NO)으로 조인해 사원 기본정보 출력
- 사원별 총점(SUM(G.SCORE))을 집계
- 총점이 가장 높은 사원 1명 출력

[성능]
- 시간복잡도: O(m + n)

[배운 점]
- 집계 결과에 대해 상위 1건만 필요할 때는 ORDER BY 조건 DESC LIMIT 1가 가장 간단
*/

SELECT SUM(G.SCORE) AS SCORE, G.EMP_NO AS EMP_NO, E.EMP_NAME AS EMP_NAME, E.POSITION AS POSITION, E.EMAIL AS EMAIL
FROM HR_GRADE G JOIN HR_EMPLOYEES E ON G.EMP_NO = E.EMP_NO
GROUP BY G.EMP_NO 
ORDER BY 1 DESC LIMIT 1
