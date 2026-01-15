import java.util.*;

public class Main {
    
    static class Piece {
        int r;
        int c;
        int d;
        Piece(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    
    static int N, K;
    static int[][] map;
    static int[] dr = {0, 0, 0, -1, 1};  // 1 ~ 4 : →, ←, ↑, ↓
    static int[] dc = {0, 1, -1, 0, 0}; 
    static ArrayList<Integer>[][] cell;
    static Piece[] pieces;
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 체스판의 크기 N
        K = sc.nextInt(); // 말의 개수 K
        map = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt(); // 0, 1, 2 색깔
            }
        }
        
        cell = new ArrayList[N][N]; // 말 스택
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cell[i][j] = new ArrayList<>();
            }
        }

        pieces = new Piece[K]; // 말 정보

        // 행, 열의 번호, 이동 방향
        for(int i = 0; i < K; i++) {
            int r = sc.nextInt() - 1; // 0-based로 변환
            int c = sc.nextInt() - 1;
            int d = sc.nextInt();
            pieces[i] = new Piece(r, c, d);
            cell[r][c].add(i); // i번 말을 해당 칸 스택에 위에 올림
        }
        
        for(int i = 1; i <= 1000; i++) { // 턴의 수 
            for(int j = 0; j < K; j++) { // 말의 수 
                move(j); // j번 말 이동
                if (check()) { // 4개 이상 쌓이면 종료 
                    System.out.println(i);
                    return;
                }
                
            }
        }
        System.out.println(-1);
	}
	
	static void move(int k) {
	    // 현재 말의 정보 (이동 정보 + 위치에 존재하는 말)
	    Piece p = pieces[k]; // 가야할 행, 열, 이동 방향
	    int x = p.r;
	    int y = p.c;
	    
	    ArrayList<Integer> from = cell[x][y];
        int idx = from.indexOf(k);
        if(idx != 0) return; // 가장 아래 말만 이동
	    
	    // 가야할 방향의 구분
	    int nx = x + dr[p.d];
	    int ny = y + dc[p.d];
	    
	    // 파란색 or 밖인 경우 - 방향 반대로 하고 1번만 재시도
        if(isBlueOrOut(nx, ny)) {
            p.d = reverseDir(p.d);
            nx = x + dr[p.d];
            ny = y + dc[p.d];
            
            if (isBlueOrOut(nx, ny)) return; // 또 파랑색 or 밖이면 이동 X -> 아닌 경우 그 다음 단계로 진행 
        }
        
        // 이동하는 말 묶음 - 바닥 말부터 전부 (idx == 0이므로 전체)
        List<Integer> moving = new ArrayList<>(from);
        
        // 기존 칸 비우기 
        from.clear();
	    
	    // 빨간색인 경우 - 뒤집기 
	    if(map[nx][ny] == 1) {
	        Collections.reverse(moving);
	    }
	    
	    // 목적지에 쌓기 
	    ArrayList<Integer> to = cell[nx][ny];
	    to.addAll(moving);
	    
	    // 좌표 갱신
        for (int id : moving) {
            pieces[id].r = nx;
            pieces[id].c = ny;
        }
	}
	
	static int reverseDir(int d) {
	    if (d == 1) return 2;
        if (d == 2) return 1;
        if (d == 3) return 4;
        return 3;
	}
	
	static boolean isBlueOrOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N || map[r][c] == 2;
    }
	
	static boolean check() {
	    for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cell[i][j].size() >= 4) return true;
            }
        }
        return false;
	}
}

