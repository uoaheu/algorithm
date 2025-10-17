import java.util.*;

public class Main {

	static int N, M;
	static int[][] arr;
	static boolean[][] check;
	static int max = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[N][M];
		check = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		int cnt = 0;
		dfs(0, 0, cnt); // DFS를 통한 벽 세우기 -> 벽을 세울 수 있는 모든 경우 탐색
		System.out.println(max);

	}

	private static void dfs(int r, int c, int cnt) {
		int[] dr = { 1, -1, 0, 0 };
		int[] dc = { 0, 0, 1, -1 };

		// 벽을 3개 세운 경우
		if (cnt == 3) {
			// 벽을 세운 상태에서 바이러스 퍼뜨리기
			int[][] arr2 = new int[N][M]; // 원래 맵 복사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					arr2[i][j] = arr[i][j];
				}
			}

			// 바이러스 위치를 저장할 큐
			Queue<int[]> q = new LinkedList<>();
			boolean[][] virusCheck = new boolean[N][M]; 

			// 초기 바이러스 위치 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr2[i][j] == 2 && !virusCheck[i][j]) {
						q.add(new int[] {i, j}); // 바이러스 노드 추가
						virusCheck[i][j] = true;
					}
				}
			}

			// BFS를 통해 바이러스 퍼뜨리기
			while (!q.isEmpty()) {
				int[] buf = q.poll();
				for (int d = 0; d < 4; d++) {
					int cr = buf[0] + dr[d];
					int cc = buf[1] + dc[d];

					if (cr < 0 || cc < 0 || cr >= N || cc >= M || arr2[cr][cc] != 0 || virusCheck[cr][cc]) {
						continue;						
					}
					virusCheck[cr][cc] = true;
					arr2[cr][cc] = 2;
					q.add(new int[] {cr, cc});
				}
			}

			// 안전 구역 크기 계산
			int safeArea = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr2[i][j] == 0) {
						safeArea++;
					}
				}
			}

			max = Math.max(max, safeArea); // 최대 안전 구역 크기 갱신
			return; // DFS 종료
			
		}
		
		// 모든 위치에서 벽을 세우는 경우 탐색
		for(int i = r; i < N; i++) {
			for(int j = (i == r ? c : 0); j < M; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 1; // 벽 세우기 
					dfs(i, j, cnt + 1); 
					arr[i][j] = 0; // 벽 제거 
				}
			}
		}

	}
} 
