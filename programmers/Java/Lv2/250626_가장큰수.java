/*
[문제] 가장 큰 수 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/42746

[접근]
- int 배열을 문자열로 변환하여 정렬해야 함
- 정렬 기준은 (a + b) vs (b + a)를 비교해 더 큰 쪽이 앞에 오도록
- ex) 3, 30 -> 330 vs 303 -> 330이 더 크므로 3이 앞
- 정렬 후 이어붙인 결과가 0000... 이면 0 하나만 반환

[성능]
- 시간복잡도: O(N log N) (정렬)

[배운 점]
- 문자열 정렬 시 사용자 정의 Comparator로 복합 기준 설정 가능
- 숫자 배열을 문자열 배열로 바꿔야 하는 이유: 이어붙이기 위함
- (b+a).compareTo(a+b)로 내림차순 정렬이 가능
*/

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        if (arr[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }

        return sb.toString();
    }
}
