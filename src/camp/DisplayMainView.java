package camp;

public class DisplayMainView {

    public static void displayMainView() throws InterruptedException{
        ScoreMagagement scoreMagagement = new ScoreMagagement();
        StudentManagement studentManagement = new StudentManagement();
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.println("관리 항목을 선택하세요...");

            int input = Util.filterInt();

            switch (input) {
                case 1 -> studentManagement.displayStudentView(); // 수강생 관리
                case 2 -> scoreMagagement.displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.err.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }
}
