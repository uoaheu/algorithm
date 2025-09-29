import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, V; // 정점 수, 간선 수, 시작 정점 번호
	static int[][] arr;
	static boolean[] check;
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		arr = new int[N + 1][N + 1];
		check = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a][b] = arr[b][a] = 1; // 양방향
		}

		dfs(V);
        trimLastSpace(sb); // 마지막 공백 제거
		sb.append("\n");
		check = new boolean[N + 1]; // 방문 배열 재설정
		bfs(V);
        trimLastSpace(sb); // 마지막 공백 제거
		System.out.println(sb);

	}

	static void dfs(int start) {
		check[start] = true;
		sb.append(start + " ");

		for (int i = 1; i <= N; i++) {
			if (arr[start][i] == 1 && !check[i])
				dfs(i);
		}

	}

	static void bfs(int start) {
		q.add(start);
		check[start] = true;

		while (!q.isEmpty()) {

			start = q.poll();
			sb.append(start + " ");

			for (int i = 1; i <= N; i++) {
				if (arr[start][i] == 1 && !check[i]) {
					q.add(i);
					check[i] = true;
				}
			}
		} 
	}
    
    static void trimLastSpace(StringBuilder s) {
        int len = s.length();
        if (len > 0 && s.charAt(len - 1) == ' ') {
            s.setLength(len - 1); // 마지막 공백 제거
        }
    }
} 
