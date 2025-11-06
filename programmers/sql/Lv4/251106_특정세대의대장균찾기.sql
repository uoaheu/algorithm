/*
[문제] 특정 세대의 대장균 찾기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/301650

[접근]
- 자기참조 테이블을 별칭(A,B,C)으로 2번 조인해 '조상(A) → 부모(B) → 자식(C)' 3단계 계보 만들기
- A는 루트(최상위)만 남기기 위해 A.PARENT_ID IS NULL 조건을 걸고, A가 반드시 존재해야 함

[성능]
- 시간복잡도: O(N³)

[배운 점]
- 자기참조(Self-Join)에서는 세대(깊이)를 조인 횟수로 표현 가능
- LEFT JOIN 뒤 우변 컬럼을 WHERE로 필터(A.ID IS NOT NULL)하면 사실상 INNER JOIN과 동일
*/

SELECT C.ID -- C : 자식 
FROM ECOLI_DATA C
LEFT JOIN ECOLI_DATA B ON C.PARENT_ID = B.ID -- B : 부모
LEFT JOIN ECOLI_DATA A ON B.PARENT_ID = A.ID -- A : 조상
WHERE A.ID IS NOT NULL AND A.PARENT_ID IS NULL
