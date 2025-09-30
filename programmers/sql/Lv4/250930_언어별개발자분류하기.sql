/*
[문제] 언어별 개발자 분류하기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/276036

[접근]
- SKILLCODES.CODE는 비트마스크, DEVELOPERS.SKILL_CODE와의 보유 여부 판정은 비트 AND(&) 수행
- A(Front End ∧ Python) -> B(C#) -> C(Front End) 그 외는 등급 없음(NULL)으로 두고 바깥 WHERE에서 제거

[성능]
- 시간복잡도: O(D + S)

[배운 점]
- 비트마스크 모델에서는 포함 여부를 =가 아닌 & > 0로 판정 - 여러 플래그의 OR 마스크는 SUM(2의 거듭제곱)으로 생성 가능
- CASE는 위에서 아래로 첫 매칭 분기가 결정되므로 우선순위 의도대로 배치해야 함
- FROM절 서브쿼리은 반드시 별칭 필요
*/

SELECT  T.GRADE, T.ID, T.EMAIL
FROM (
  SELECT
    CASE
      WHEN (D.SKILL_CODE & (SELECT SUM(CODE) FROM SKILLCODES WHERE CATEGORY = 'Front End')) > 0
       AND (D.SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'Python')) > 0 THEN 'A'
      WHEN (D.SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'C#')) > 0 THEN 'B'
      WHEN (D.SKILL_CODE & (SELECT SUM(CODE) FROM SKILLCODES WHERE CATEGORY = 'Front End')) > 0 THEN 'C'
    END AS GRADE,
    D.ID,
    D.EMAIL
  FROM DEVELOPERS D
) AS T
WHERE T.GRADE IS NOT NULL
ORDER BY T.GRADE ASC, T.ID ASC;
