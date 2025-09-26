/*
[문제] FrontEnd 개발자 찾기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/276035

[접근]
- 개발자의 스킬 코드에 맞게 개발 스킬 코드를 조인
- 조인 시 비트 연산자를 이용해서 개발자의 스킬 코드에 특정 스킬이 포함되는지 확인
- 카테고리가 Front End인 경우만 필터링
- 동일 개발자가 여러번 출력될 수 있으니 중복 제거

[성능]
- 시간복잡도: O(N * M)

[배운 점]
- 비트마스크와 비트 AND(&) 연산을 이용하면 다중 스킬을 하나의 정수로 관리 가능
- JOIN에서 발생하는 중복 행은 DISTINCT나 GROUP BY로 제거해야 함
- LIKE 조건은 문자열 비교에서 정확한 일치(=) 대신 패턴 매칭이 가능함을 확인
*/

SELECT DISTINCT d.ID, d.EMAIL, d.FIRST_NAME, d.LAST_NAME FROM DEVELOPERS d
JOIN SKILLCODES s
ON s.CODE = d.SKILL_CODE&s.CODE
WHERE s.CATEGORY LIKE 'Front End' -- == 대신 LIKE 사용
ORDER BY d.ID;
