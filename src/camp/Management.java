package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Management {
    // 과목 타입
    protected static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    protected static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    protected static int studentIndex;
    protected static final String INDEX_TYPE_STUDENT = "ST";
    protected static int subjectIndex;
    protected static final String INDEX_TYPE_SUBJECT = "SU";
    protected static int scoreIndex;
    protected static final String INDEX_TYPE_SCORE = "SC";

    // 데이터 저장소
    protected static List<Student> studentStore;
    protected static List<Subject> subjectStore;

    // 초기 데이터 생성
    public static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
    }

    // index 자동 증가
    protected static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    protected String getStudentId() throws InterruptedException  {
        for(Student student : studentStore)
            System.out.println("학생 ID : " + student.getStudentId() + ", 학생 이름 : "+ student.getStudentName());

        return "ST" + Util.filterInt();
    }

    // 입력된 수강생 번호와 일치하는 인덱스 값 반환
    protected int verifyStudentId() throws InterruptedException {
        String studentId; // 관리할 수강생 고유 번호
        int studentIdx = 0;
        boolean flag = true;

        while(flag) { // 유효한 수강생인지 조회
            System.out.println("\n수강생 ID의 숫자를 입력하시오 : ");
            studentId = getStudentId(); // 관리할 수강생 고유 번호 입력 받기

            // studentIdx로 학생 정보 조회
            for (int i = 0; i < studentStore.size(); i++) {
                if (studentStore.get(i).getStudentId().equals(studentId)) {
                    studentIdx = i;
                    flag = false;
                    break;
                }
            }

            if(flag)
                System.err.println("유효한 수강생 번호가 아닙니다. 다시 입력해주세요");
        }

        return studentIdx;
    }
}
