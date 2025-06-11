/*
[문제] 년, 월, 성별 별 상품 구매 회원 수 구하기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/131532

[접근]
- USER_INFO : 사용자 정보 테이블 (성별 포함)
- ONLINE_SALE : 온라인 구매 정보 테이블 (구매일 포함)
- 두 테이블을 USER_ID 기준으로 조인하여 구매 이력이 있는 사용자만 조회
- 성별이 NULL이 아닌 사용자만 필터링
- 연도, 월, 성별 기준으로 그룹화 → 고유 사용자 수를 COUNT(DISTINCT)로 계산
- 연도, 월, 성별 기준 오름차순 정렬

[성능]
- USER_ID에 인덱스가 있다면 조인 효율적
- DISTINCT + COUNT는 메모리 사용량에 영향 → 사용자가 많을 경우 고려 필요

[배운 점]
- GROUP BY에서 숫자 인덱스(1,2,3)는 컬럼명 대신 사용할 수 있으나 가독성을 위해 명시적으로 작성하는 것이 좋음
- 구매자 수 집계 시 중복 제거(`DISTINCT`) 중요
*/

SELECT 
    YEAR(o.SALES_DATE) AS YEAR,
    MONTH(o.SALES_DATE) AS MONTH,
    u.GENDER AS GENDER,
    COUNT(DISTINCT u.USER_ID) AS USERS
FROM 
    USER_INFO u
JOIN 
    ONLINE_SALE o
    ON u.USER_ID = o.USER_ID
WHERE 
    u.GENDER IS NOT NULL
GROUP BY 1, 2, 3
ORDER BY 
    YEAR ASC,
    MONTH ASC,
    GENDER ASC;
