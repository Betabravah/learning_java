package lab3UMLCode;

public class FinalExam3 extends GradedActivity implements Relatable{
    private int numQuestions;
    private double pointsEach;
    private int numMissed;

    public FinalExam3(int numQuestions, int numMissed, double pointsEach) {
        this.numQuestions = numQuestions;
        this.pointsEach = pointsEach;
        this.numMissed = numMissed;

        setScore((numQuestions - numMissed) * pointsEach);
    }

    public double getPointsEach() {
        return pointsEach;
    }

    public int getNumMissed() {
        return numMissed;
    }

    public boolean equals(GradedActivity g) {
        return getScore() == g.getScore();
    }

    public boolean isGreater(GradedActivity g) {
        return getScore() > g.getScore();
    }

    public boolean isLess(GradedActivity g) {
        return getScore() < g.getScore();
    }

}
