package camp;

import java.util.Scanner;

public class Util {
    public static void plzNumber(){
        System.err.println("잘못 입력하셨습니다. 숫자만 입력해주세요.");
    }

    public static void plzSubject(){
        System.err.println("잘못 입력하셨습니다. 유효한 과목을 입력해주세요.");
    }

    public static int filterInt() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("메인으로 돌아가려면 '-1'을 입력해주세요.");
        int answer = 0;
        while(!sc.hasNextInt()){
            sc.next(); // 입력받은 값 날리기
            plzNumber();
        }

        answer = sc.nextInt();
        sc.nextLine();

        if(answer == -1){
            System.out.println("메인화면으로 돌아갑니다....");
            Thread.sleep(1000);
            DisplayMainView.displayMainView();
        }

        return answer;
    }
}
