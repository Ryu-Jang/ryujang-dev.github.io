import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = "";

        Queue<Integer> queue = new LinkedList<Integer>();

        while (!Objects.equals("exit", str)) {
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int num1 = sc.nextInt();
            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2 = sc.nextInt();
            System.out.print("사칙연산 기호를 입력하세요: ");
            char ch = sc.next().charAt(0);
            int numResult = 0;

            switch (ch) {
                case '+': {
                    numResult = num1 + num2;
                    System.out.println("결과: " + numResult);
                    queue.add(numResult);
                }
                case '-': {
                    numResult = num1 - num2;
                    System.out.println("결과: " + numResult);
                    queue.add(numResult);
                }
                case '*': {
                    numResult = num1 * num2;
                    System.out.println("결과: " + numResult);
                    queue.add(numResult);
                }
                case '/': {
                    if (num2 == 0) {
                        System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                        break;
                    }
                    numResult = num1 / num2;
                    System.out.println("결과: " + numResult);
//                    if (queue.size() >10) {
//                        queue.remove();
//                    }
                    queue.add(numResult);
                }
            }
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            str = sc.nextLine();
            if (Objects.equals("inquiry", str)) {
                System.out.println(queue);
            }
        }
    }
}
