/*
[문제] 기능개발 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42586

[접근]
- 각 기능마다 배포까지 남은 일수를 계산하여 days 배열로 만듦
- days 배열을 순회하며 앞 기능보다 느리게 끝나는 기능이 나올 때까지 count 증가
- 느리게 끝나는 기능이 나오면 그 전에 count한 수만큼 한번에 배포
- 위 과정을 반복하여 각 배포 시점마다 몇 개의 기능이 배포되는지를 계산

[성능]
- 시간복잡도: O(N) -> N = 기능 수

[배운 점]
- Math.ceil()을 활용해 남은 작업 일수를 정확히 계산하는 방식
- 앞 기능이 끝나야 뒤 기능도 배포된다는 조건을 배포 기준일(maxDay)로 해결
- 문제를 수학적으로 모델링하고 배열로 변환하면 로직이 간결해진다는 점
*/

function solution(progresses, speeds) {
    const days = progresses.map((p, i) => Math.ceil((100 - p) / speeds[i]));
    const answer = [];

    let maxDay = days[0];
    let count = 1;

    for (let i = 1; i < days.length; i++) {
        if (days[i] <= maxDay) {
            count++; // 같은 배포일에 포함
        } else {
            answer.push(count); // 이전까지 묶은 배포
            maxDay = days[i]; // 새로운 기준일
            count = 1;
        }
    }
    answer.push(count); // 마지막 배포 묶음

    return answer;
}
