package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement extends Management{
    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public StudentManagement() {
    }

    public void displayStudentView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 정보 수정");
            System.out.println("4. 수강생 목록 삭제");
            System.out.println("5. 상태별 수강생 목록 조회");
            System.out.println("6. 메인 화면 이동");
            System.out.println("관리 항목을 선택하세요...");

            int input = Util.filterInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> changeStudentInfo(); // 수강생 정보 수정
                case 4 -> removeStudent();  // 수강생 목록 삭제
                case 5 -> mentalSort();     // 상태별 수강생 목록 조회
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.err.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private void createStudent() throws InterruptedException {
        List<Subject> mainSubjects = new ArrayList<Subject>();
        List<Subject> subSubjects = new ArrayList<Subject>();

        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)
        sc.nextLine();
        // 필수 과목 로직입니다.
        while (true) {
            System.out.println("원하는 필수 과목 수를 입력하세요.");
            int ms = Util.filterInt();

            List<String> msNotSame = new ArrayList<>(); // 같은 과목을 수강하는것을 방지하기 위해 저장합니다.

            if (ms < 3 || ms > 5) {
                System.out.println("필수과목은 최소 3과목 이상 5과목 이하로 선택해야 합니다. 다시 입력해 주세요");
            } else {
                for (int i = 0; i < ms; i++) {
                    while (true) {
                        System.out.println("필수과목 [1: 'Java', 2: '객체지향', 3: 'Spring', 4: 'JPA', 5: 'MySQL']중에서 듣고싶은 과목을 선택하세요.");
                        String subject = sc.nextLine();
                        if (msNotSame.contains(subject)) {
                            System.out.println("이미 수강한 과목입니다. 다시 입력해주세요.");
                            continue;
                        }
                        if (subject.equals("1")) {
                            mainSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "Java", SUBJECT_TYPE_MANDATORY));
                            msNotSame.add("1");
                            break;
                        } else if (subject.equals("2")) {
                            mainSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "객체지향", SUBJECT_TYPE_MANDATORY));
                            msNotSame.add("2");
                            break;
                        } else if (subject.equals("3")) {
                            mainSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring", SUBJECT_TYPE_MANDATORY));
                            msNotSame.add("3");
                            break;
                        } else if (subject.equals("4")) {
                            mainSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "JPA", SUBJECT_TYPE_MANDATORY));
                            msNotSame.add("4");
                            break;
                        } else if (subject.equals("5")) {
                            mainSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "MySQL", SUBJECT_TYPE_MANDATORY));
                            msNotSame.add("5");

                            break;
                        } else {
                            System.out.println("과목 명을 다시 입력해 주세요.");
                        }
                    }
                }
                break;
            }
        }
        // 선택 과목 로직입니다.
        while (true) {
            System.out.println("원하는 선택 과목 수를 입력하세요.");
            int ss = Util.filterInt();

            List<String> ssNotSame = new ArrayList<>(); // 같은 과목을 수강하는것을 방지하기 위해 저장합니다.
            if (ss < 2 || ss > 4) {
                System.out.println("선택 과목은 최소 2과목 이상 4과목 이하로 선택해야 합니다. 다시 입력해 주세요");
            } else {
                for (int i = 0; i < ss; i++) {
                    while (true) {
                        System.out.println("선택과목 [1: '디자인 패턴', 2: 'Spring Security', 3: 'Redis', 4: 'MongoDB']중에서 듣고싶은 과목을 선택하세요.");
                        String subject = sc.nextLine();
                        if (ssNotSame.contains(subject)) {
                            System.out.println("이미 수강한 과목입니다. 다시 입력해주세요.");
                            continue;
                        }
                        if (subject.equals("1")) {
                            subSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "디자인 패턴", SUBJECT_TYPE_CHOICE));
                            ssNotSame.add("1");
                            break;
                        } else if (subject.equals("2")) {
                            subSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring Security", SUBJECT_TYPE_CHOICE));
                            ssNotSame.add("2");
                            break;
                        } else if (subject.equals("3")) {
                            subSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "Redis", SUBJECT_TYPE_CHOICE));
                            ssNotSame.add("3");
                            break;
                        } else if (subject.equals("4")) {
                            subSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "MongoDB", SUBJECT_TYPE_CHOICE));
                            ssNotSame.add("4");

                            break;
                        } else {
                            System.out.println("과목 명을 잘못 입력하셨습니다. 과목 명을 다시 입력해 주세요.");
                        }
                    }
                }
                break;
            }
        }

        String mental = Student.choiceMental();

        for (Subject mainSubject : mainSubjects) {
            System.out.println(mainSubject.getSubjectId() + " " + mainSubject.getSubjectName() + " " + mainSubject.getSubjectType());
        }
        for (Subject subSubject : subSubjects) {
            System.out.println(subSubject.getSubjectId() + " " + subSubject.getSubjectName() + " " + subSubject.getSubjectType());
        }
        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, mainSubjects, subSubjects, mental); // 수강생 인스턴스 생성 코드

        // 기능 구현
        System.out.println(student.getStudentName() + " " + student.getStudentId());
        studentStore.add(student);
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        // 1. 수강생 목록을 조회를 하려면
        // 2. 이 목록을 가지고 있는 이름을 찾아보면 studentStore이란걸 알 수 있음
        // 3. 조회라는 행동을 했을 때 return 할 필요 없이 눈에 바로 보여지니까 print 해야됨
        // 4. 수강생이 한명이 아니라 여러명이라 계속 프린트로 뽑아줘야 하고 list 에 있는 size 메소드사용하니
        // 수강생이 몇명인지 알 수 있으니 향상된 for문을 사용할 수 있음(while이 틀린건 아니지만 귀찮음)
        // 향상된for문:배열을 넘기면 배열안에있는 모든 원소를 탐색(조건없이 안에있는 수강생전부 보여줄거니까)
        // 6. 조회필수정보: 고유번호와 이름 -> private이라 직접적으로 접근 불가능 그래서 getter 사용
        // getter 메소드가 무엇인지 정리하면서 해보기 (고유번호/이름 을 반환하는 메소드이름)
        for (Student studentInfo : studentStore) {
            System.out.println("==================================");
            System.out.println("수강생 ID: " + studentInfo.getStudentId());
            System.out.println("수강생 이름: " + studentInfo.getStudentName());
            System.out.println("수강생 상태: " + studentInfo.getMental());
            System.out.print("필수과목: ");
            for (Subject mainSubject : studentInfo.getMainSubjects()) {
                System.out.print("[ " + mainSubject.getSubjectName() + " ] ");
            }
            System.out.print("\n선택과목: ");
            for (Subject subSubject : studentInfo.getSubSubjects()) {
                System.out.print("[ " + subSubject.getSubjectName() + " ] ");
            }
            System.out.println("\n==================================");
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    // 수강생 목록 삭제
    private void removeStudent() throws InterruptedException {
        System.out.println("학생을 삭제합니다...");
        studentStore.remove(verifyStudentId());
    }

    // 상태별 수강생 목록 조회
    private void mentalSort() throws InterruptedException {
        System.out.println("상태별 수강생 목록을 조회합니다. ");
        System.out.println("조회하고 싶은 상태를 입력해주세요.");
        String mental = Student.choiceMental();
        boolean exist = false;
        System.out.println("조회할 상태 : " + mental);
        for(Student student : studentStore){
            if(student.getMental().equals(mental)) {
                exist = true;
                System.out.println("[ " + student.getStudentName() + " ] ");
            }
        }
        if(exist) System.out.println("상태가 " + mental + "인 학생들 조회 성공!");
        else      System.out.println("상태가 " + mental + "인 학생이 없습니다.");
    }

    private void changeStudentInfo() throws InterruptedException {
        System.out.println("학생 정보를 수정합니다...");
        Student student = studentStore.get(verifyStudentId());
        String beforeName = "";

        System.out.println("수정할 학생 정보를 선택하세요.");
        System.out.println("1: 수강생 이름, 2: 학생 상태, 3: 모두");

        while(true) {
            int num = Util.filterInt();

            if (num == 1) {
                System.out.println("변경할 이름을 입력해주세요.");
                String newName = sc.nextLine();
                beforeName = student.getStudentName();
                student.setStudentName(newName);
                System.out.println(beforeName +" 님이 "+ newName + "으로 변경되었습니다.");
                break;
            } else if(num == 2) {
                System.out.println("변경할 수강생의 상태을 입력해주세요.");
                student.setMental(student.choiceMental());
                System.out.println("변경된 수강생의 상태: " + student.getMental());
                break;
            } else if(num == 3) {
                System.out.println("변경할 이름을 입력해주세요.");
                String newName = sc.nextLine();
                student.setStudentName(newName);

                System.out.println("변경할 수강생의 상태을 입력해주세요.");
                student.setMental(student.choiceMental());
                System.out.println(beforeName +" 님이 "+ newName + "으로 변경되었습니다.");
                System.out.println("변경된 수강생의 상태: " + student.getMental());
                break;
            } else {
                System.out.println("잘못 입력하셨습니다.");
            }
        }
    }
}