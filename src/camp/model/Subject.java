package camp.model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final String    subjectId;
    private       String    subjectName;
    private       String    subjectType;
    private       Score     scores;

    public Subject(String seq, String subjectName, String subjectType) {
        this.subjectId   = seq;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
        this.scores      = new Score();
    }

    // Getter
    public String   getSubjectId()      { return this.subjectId; }
    public String   getSubjectName()    { return this.subjectName; }
    public Score    getScores()         { return this.scores; }
    public String   getSubjectType()    { return this.subjectType; }

    public void printScores() {
        for (int i = 0; i < scores.getScore().size(); i++)
            System.out.println(subjectName + " " + ( i + 1 ) + "회차: " + scores.getScore().get(i));
    }

    public void printGrads() {
        for (char grade : scores.getGrade())
            System.out.println(subjectName + " " + grade);
    }

    public void addScore(int score) {
        if(scores.getScore().size() < 10)
            getScores().setScore(score, subjectType);
        else
            System.err.println("저장에 실패했습니다. 이미 10회차 등록이 완료되어있습니다.");
    }

    public void printSubject(int cnt) {
        System.out.println(cnt + ". " + subjectName);
    }

    public void printAllGrades(){
        for (int i = 0; i < scores.getGrade().size(); i++)
            System.out.println(subjectName + " " + ( i + 1 ) + "회차: " + scores.getGrade().get(i));
    }

    public void printSubject() {
        System.out.println(subjectName);
    }

    public void editScores(int round, int newScore) {
        scores.editScore(round, newScore, subjectType);
    }

    // 수강생 과목별 평균
    public double averageScore(){
        List<Integer> score;
        double average = 0;

        score = this.scores.getScore();

        for (Integer s : score)
            average += s;

        average /= score.size();
        if (score.isEmpty())
            return -1;
        return average;
    }
}