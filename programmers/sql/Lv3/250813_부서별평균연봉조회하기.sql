/*
[문제] 부서별 평균 연봉 조회하기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/284529

[접근]
- HR_DEPARTMENT 테이블과 HR_EMPLOYEES 테이블을 DEPT_ID 기준으로 JOIN
- 부서별(DEPT_ID)로 그룹화하여 평균 연봉(AVG(SAL))을 계산
- 평균 연봉은 소수점 반올림(ROUND)하여 표시
- 평균 연봉이 높은 순(내림차순)으로 정렬


[성능]
- 시간복잡도: O(N) ~ O(N log N)

[배운 점]
- JOIN 후 GROUP BY를 통해 두 테이블의 정보를 결합하여 집계 가능
*/

SELECT D.DEPT_ID, D.DEPT_NAME_EN, ROUND(AVG(SAL)) AS AVG_SAL
FROM HR_DEPARTMENT D JOIN HR_EMPLOYEES E ON D.DEPT_ID = E.DEPT_ID
GROUP BY D.DEPT_ID
ORDER BY AVG_SAL DESC
