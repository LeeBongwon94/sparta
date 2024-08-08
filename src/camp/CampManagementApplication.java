package camp;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */

public class CampManagementApplication {

    public static void main(String[] args) {
        Management.setInitData();
        try {
            Init.initStudent("수현", new int[]{1, 2, 3}, new int[]{1, 2}, 1, new int[][]{{100, 20, 30}, {100, 20}, {30, 50}},new int[][]{{10, 50}, {90, 100}});
            DisplayMainView.displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
            System.out.println("오류 발생 이유: " + e.getMessage());
        }
    }
}
