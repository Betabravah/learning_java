package lab3UMLCode;

public abstract class GradedActivity {
    private double score;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public char getGrade() {
        if (score >= 83)
            return 'A';
        else if (score >= 67)
            return 'B';
        else if (score >= 55)
            return 'C';
        else if (score >= 40)
            return 'D';
        else
            return 'F';
    }
}
