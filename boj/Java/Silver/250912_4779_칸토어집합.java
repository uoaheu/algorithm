import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
    // scanner.hasNextInt()
		// - 입력이 더 있는지 확인 
		// - 반복문을 돌면서 n을 입력받고, 칸토어 함수를 호출하여 결과 출력
		// - 입력이 더 이상 없으면 프로그램 종료

		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			System.out.println(func(n));
		}
		sc.close();
	}

	public static String func(int n) {
		if (n == 0) {
			return "-";
		}

		String prev = func(n - 1);

		StringBuilder sb = new StringBuilder();
		sb.append(prev);

		// 공백 추가
		for (int i = 0; i < prev.length(); i++) {
			sb.append(" ");
		}

		sb.append(prev);

		return sb.toString();
	}
}
