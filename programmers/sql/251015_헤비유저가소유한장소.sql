/*
[문제] 헤비 유저가 소유한 장소 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/77487

[접근]
- 헤비 유저 = 같은 HOST_ID로 2개 이상 장소를 등록한 사용자
- 서브쿼리에서 HOST_ID 그룹핑m HAVING COUNT(*) > 1로 헤비 유저 집합 구한 뒤, 바깥 쿼리에서 HOST_ID IN (집합)으로 필터링
- 모든 정보 출력, 아이디 오름차순 정렬

[성능]
- 시간복잡도: O(N + R)

[배운 점]
- COUNT(*)가 NULL 영향이 없어 COUNT(컬럼)보다 의도에 맞고 최적화에 유리
*/

SELECT * 
FROM PLACES 
WHERE HOST_ID 
IN (
  SELECT HOST_ID 
  FROM PLACES 
  GROUP BY HOST_ID 
  HAVING COUNT(HOST_ID) > 1
)
ORDER BY ID
