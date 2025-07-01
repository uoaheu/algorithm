/*
[문제] 대장균들의 자식의 수 구하기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/299305

[접근]
- 서브쿼리: 동일 테이블에서 PARENT_ID가 현재 ID와 일치하는 행의 개수를 COUNT하여 자식 노드 수 산출
- 메인쿼리: ECOLI_DATA를 기준으로 각 ID별로 서브쿼리 결과를 CHILD_COUNT로 함께 출력
- ORDER BY를 통해 결과를 ID 오름차순으로 정렬

[성능]
- PARENT_ID 칼럼에 인덱스가 있다면 서브쿼리 내 COUNT 연산이 빠르게 처리됨

[배운 점]
- 자기참조 테이블에서 서브쿼리를 사용해 부모-자식 관계 집계 가능
- 서브쿼리를 활용한 카운팅은 간결하지만, 대용량에서는 JOIN과 GROUP BY를 활용한 방법과 성능 차이가 있을 수 있음
*/

SELECT E1.ID,
       (SELECT COUNT(*)
        FROM ECOLI_DATA E2
        WHERE E1.ID = E2.PARENT_ID) AS CHILD_COUNT
FROM ECOLI_DATA E1
ORDER BY E1.ID;
