/*
[문제] 과제 진행하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/176962

[접근]
- 각 과제는 (이름, 시작 시각, 소요 시간)으로 구성되므로 "시간 순서대로 처리 + 중단된 과제는 나중에 이어서 하기" 구조로 시뮬레이션
- 우선 모든 과제를 시작 시각 기준으로 정렬
- 인접한 두 과제(cur, next)를 보면서 기존 과제의 종료 시각과 시작 시간의 관계에 따라 조건 처리
- 마지막으로 스택에 남아 있는 과제들을 LIFO 순서로 모두 완료 처리

[성능]
- 시간 복잡도: O(n log n)

[배운 점]
- List<String>을 String[]로 바꿔야 할 때는 finished.toArray(new String[0]) 형태로 변환
*/

import java.util.*;
class Solution {
    static class Plan {
        String name;
        int start; // 시작 시간(분)
        int play; // 남은 시간(분)

        Plan(String name, int start, int play) {
            this.name = name;
            this.start = start;
            this.play = play;
        }
    }
    
    public String[] solution(String[][] plans) {
        // 1. Plan 리스트로 변환
        List<Plan> list = new ArrayList<>();
        for (String[] p : plans) {
            String name = p[0];
            int start = toMinutes(p[1]);
            int play = Integer.parseInt(p[2]);
            list.add(new Plan(name, start, play));
        }
        
        // 2. 시작 시간 기준 정렬
        Collections.sort(list, (a, b) -> a.start - b.start);
        Stack<Plan> stack = new Stack<>();
        List<String> finished = new ArrayList<>();

        // 3. 현재 과제랑 다음 과제 비교
        for (int i = 0; i < list.size() - 1; i++) {
            Plan cur = list.get(i); // 현재 과제
            Plan next = list.get(i + 1); // 다음 과제

            int curEndTime = cur.start + cur.play; // 현재 과제가 다 하면 끝나는 시간
            int nextStart = next.start;

            if (curEndTime <= nextStart) {
                // 현재 과제를 끝낼 수 있는 경우
                finished.add(cur.name);

                int free = nextStart - curEndTime; // 남는 시간

                // 남는 시간 동안 스택의 과제 처리
                while (!stack.isEmpty() && free > 0) {
                    Plan paused = stack.pop();
                    if (paused.play <= free) {
                        // 이 과제를 다 끝낼 수 있음
                        free -= paused.play;
                        finished.add(paused.name);
                    } else {
                        // 일부만 처리하고 다시 스택에 넣기
                        paused.play -= free;
                        free = 0;
                        stack.push(paused);
                    }
                }

            } else {
                // 현재 과제를 다 끝내지 못하고 중단해야 하는 경우
                int remaining = curEndTime - nextStart; // 부족한 시간 = 남은 시간
                cur.play = remaining;
                stack.push(cur);
            }
        }

        // 4. 마지막 과제는 무조건 끝낼 수 있음
        Plan last = list.get(list.size() - 1);
        finished.add(last.name);

        // 5. 스택에 남은 과제들 처리
        while (!stack.isEmpty()) {
            finished.add(stack.pop().name);
        }

        // 6. 결과 반환
        return finished.toArray(new String[0]);
    }
    
    private int toMinutes(String time) {
        String[] t = time.split(":");
        int h = Integer.parseInt(t[0]);
        int m = Integer.parseInt(t[1]);
        return h * 60 + m;
    }
}
