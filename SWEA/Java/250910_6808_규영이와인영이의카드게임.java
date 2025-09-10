import java.util.Arrays;
import java.util.Scanner;

public class Solution {
  static int N = 9;
  static int[] numbers1, numbers2, input; // 규영 카드, 인영 카드(순열), 인영 후보
  static boolean[] visited, checked; // 순열 방문, 규영이 가진 카드 체크 (1~18 체크)
  static int result, count;
  
  static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		numbers1 = new int[N];
		numbers2 = new int[N];
		input = new int[N];
		
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			// 값 초기화 (테케 진행될 때마다)
			visited = new boolean[N];
			checked = new boolean[19]; // 1~18 사용 (규영이 카드를 통해서 인영이 카드 확인하는 배열)
			result = 0;
			count = 0;

			for(int i = 0; i < N; i++) {
				int n = sc.nextInt();
				checked[n] = true;
				numbers1[i] = n; // 규영 값 
			}
			
			// 인영이 카드 9개 저장  
			int idx = 0;
			for(int i = 1; i <= 18; i++) {
				if(!checked[i]) {
					input[idx++] = i;
				}
			}
			
			permutation(0, 0, 0);
			sb.append("#").append(tc).append(" ").append(result).append(" ").append(362880 - result).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	
	public static void permutation(int depth, int win, int lose) {
		// 9경기 끝났으면 경기 종료 
		if(depth == N) {
			if(win > lose) {
				result++;
			}
			return;
		}
		
		// depth 위치에 N개 중에 1개의 원소를 선택하기 
		for(int i = 0; i < N; i++) { 
			if(visited[i]) continue; // 건너뛰기 			
			
			// 중복되지 않았으면 i번째 원소 뽑기 
			numbers2[depth] = input[i];
			
			int newWin = win;
			int newLose = lose;
			
			// 이겼는지 확인 
			if(numbers1[depth] > numbers2[depth]) {
				newWin += numbers1[depth] + numbers2[depth];
			} else if (numbers1[depth] < numbers2[depth]) {
				newLose += numbers1[depth] + numbers2[depth];
			}
			
			// i번째 원소를 사용했다고 체크 
			visited[i] = true; 
			permutation(depth + 1, newWin, newLose);
			visited[i] = false; 
		}
		
	}
}
