/*
[문제] 노선별 평균 역 사이 거리 조회하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/284531

[접근]
- SUBWAY_DISTANCE 테이블에서 ROUTE 별로 그룹화
- 총 누계 거리 : SUM(D_BETWEEN_DIST)을 구해 소수점 첫째 자리까지 반올림, km 단위 붙이기
- 평균 거리 : AVG(D_BETWEEN_DIST)를 구해 소수점 둘째 자리까지 반올림, km 단위 붙이기
- 거리 기준 내림차순 정렬

[성능]
- 시간복잡도: O(N)

[배운 점]
- 별칭(alias)에 단위를 붙이면 ORDER BY에 직접 사용할 수 없어 원식을 활용해야 함
- 평균은 SUM/COUNT 대신 AVG()를 쓰는 것이 정확하고 가독성이 좋음
*/

SELECT
  ROUTE,
  CONCAT(ROUND(SUM(D_BETWEEN_DIST), 1), 'km') AS TOTAL_DISTANCE,
  CONCAT(ROUND(AVG(D_BETWEEN_DIST), 2), 'km') AS AVERAGE_DISTANCE
FROM SUBWAY_DISTANCE
GROUP BY ROUTE
ORDER BY SUM(D_BETWEEN_DIST) DESC;  
