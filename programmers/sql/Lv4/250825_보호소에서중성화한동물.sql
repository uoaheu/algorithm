/*
[문제] 보호소에서 중성화한 동물 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/59045

[접근]
- 먼저 보호소에 들어올 당시에는 중성화되지 않은 동물 아이디 조회
- 이후 해당 동물 중 나갈 당시 중성화된 동물 조회
- 아이디 기준으로 정렬

[성능]
- 시간복잡도: O(N+M+KlogK)
  - 서브쿼리: O(N)
  - 메인쿼리 필터링: O(M)
  - 정렬: O(K log K)

[배운 점]
- 상태 문자열을 = 으로 비교하기 보다 like를 활용해서 2가지 경우(male, female) 한번에 체크
- IN 대신 EXISTS/JOIN으로도 동일 문제 해결 가능
*/

SELECT ANIMAL_ID, ANIMAL_TYPE, NAME FROM ANIMAL_OUTS
WHERE ANIMAL_ID IN (SELECT ANIMAL_ID FROM ANIMAL_INS WHERE SEX_UPON_INTAKE LIKE '%Intact%') 
AND (SEX_UPON_OUTCOME LIKE '%Spayed%' OR SEX_UPON_OUTCOME like '%Neutered%')
ORDER BY ANIMAL_ID
