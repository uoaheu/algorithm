import java.util.*;

public class Main {
	static int result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		while(true) {
			if(N < 0) {
				break;
			}

			if(N % 5 == 0) {
				result += N / 5;
				N %= 5;
			}
			if(N == 0) {
				System.out.println(result);
				return;
			}
			N -= 3;
			result++;
		}
		System.out.println(-1);
	}
}
