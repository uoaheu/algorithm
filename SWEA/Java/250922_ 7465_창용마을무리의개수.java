import java.util.*;

public class Solution {
	static int N, M;
	static int[] parents;
	
	public static void make() {
		for(int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	public static int find(int v) {
		if(parents[v] == v) return v;
		
		return find(parents[v]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[aRoot] = bRoot;
		return true;
	}
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            parents = new int[N];
            make();

            for(int i = 0; i < M; i++) {
                int a = sc.nextInt() - 1;
                int b = sc.nextInt() - 1;
                union(a, b);
            }

            int cnt = 0;
            for(int i = 0; i < N; i++) {
                if(parents[i] == i) {
                    cnt++;
                }
            }
            System.out.println("#" + tc + " " + cnt);
        }
	}
}
