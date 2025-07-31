/*
[문제] 대장균의 크기에 따라 분류하기 1 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/299307

[접근]
- ECOLI_DATA 테이블에서 ID와 SIZE_OF_COLONY 컬럼을 조회
- SIZE_OF_COLONY 값에 따라 CASE 문을 사용해 범주화 -> (100 이하: 'LOW' / 1000 초과: 'HIGH' / 100 ~ 1000: 'MEDIUM')
- 결과는 ID 기준 오름차순 정렬

[성능]
- 시간복잡도: O(N) — 테이블 전체를 한 번 스캔하면서 분류

[배운 점]
- SQL의 CASE 문을 활용하면 조건에 따라 값을 쉽게 범주형으로 변환 가능
*/

SELECT ID, 
    (CASE
        WHEN SIZE_OF_COLONY <= 100 THEN 'LOW'
        WHEN SIZE_OF_COLONY > 1000 THEN 'HIGH'
        ELSE 'MEDIUM'
    END) AS SIZE 
FROM ECOLI_DATA
ORDER BY ID ASC;
