package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class Init extends Management{

    public static void initStudent(String studentName, int[] mainSubject, int[] subSubject, int mentalNum, int[][] mainSubjectScore, int[][] subSubjectScore ){
        List<Subject> mainSubjects = new ArrayList<>();
        List<Subject> subSubjects = new ArrayList<>();
        String mental = "";
        for (int subject : mainSubject) {
            switch (subject) {
                case 1 -> {mainSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "Java", SUBJECT_TYPE_MANDATORY));}
                case 2 -> {mainSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "객체지향", SUBJECT_TYPE_MANDATORY));}
                case 3 -> {mainSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring", SUBJECT_TYPE_MANDATORY));}
                case 4 -> {mainSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "JPA", SUBJECT_TYPE_MANDATORY));}
                case 5 -> {mainSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "MySQL", SUBJECT_TYPE_MANDATORY));}
            }
        }
        for (int subject : subSubject) {
            switch (subject) {
                case 1 -> {subSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "디자인 패턴", SUBJECT_TYPE_CHOICE));}
                case 2 -> {subSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring Security", SUBJECT_TYPE_CHOICE));}
                case 3 -> {subSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "Redis", SUBJECT_TYPE_CHOICE));}
                case 4 -> {subSubjects.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "MongoDB", SUBJECT_TYPE_CHOICE));}
            }
        }
        switch (mentalNum) {
            case 1 -> {mental = "Green";}
            case 2 -> {mental = "Yellow";}
            case 3 -> {mental = "Red";}
        }

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, mainSubjects, subSubjects, mental);

        for (int i = 0; i < mainSubjectScore.length; i++) {
            for (int j = 0; j < mainSubjectScore[i].length; j++) {
                student.addStudentScore(mainSubjects.get(i).getSubjectName(), mainSubjectScore[i][j]);
            }
        }
        for (int i = 0; i < subSubjectScore.length; i++) {
            for (int j = 0; j < subSubjectScore[i].length; j++) {
                student.addStudentScore(subSubjects.get(i).getSubjectName(), subSubjectScore[i][j]);
            }
        }

        studentStore.add(student);

    }
}
