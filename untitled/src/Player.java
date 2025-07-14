public class Player {

    private String name;
    private int startingScore;
    private int currentScore;
    private int legsWon;
    private int setsWon;

    public Player(String name, int startingScore) {
        this.name = name;
        this.startingScore = startingScore;
        resetForNewLeg();
        this.legsWon = 0;
        this.setsWon = 0;
    }

    public String getName() {
        return name;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getLegsWon() {
        return legsWon;
    }

    public int getSetsWon() {
        return setsWon;
    }

    public void subtractScore(int hit) {
        if (hit > currentScore) {
            System.out.println("Überworfen! Rest: " + currentScore);
        } else if (currentScore - hit == 0) {
            currentScore = 0;
            winLeg();
            resetForNewLeg();
        } else {
            currentScore -= hit;
        }
    }

    public void resetForNewLeg() {
        this.currentScore = startingScore;
    }

    public void winLeg() {
        legsWon++;
        resetForNewLeg();
    }

    public void winSet() {
        setsWon++;
        legsWon = 0;
        resetForNewLeg();
    }

    public boolean hasWonLeg() {
        return currentScore == 0;
    }

    public boolean hasWonMatch(int targetSets) {
        return setsWon >= targetSets;
    }

    @Override
    public String toString() {
        return name + " | Score: " + currentScore + " | Legs: " + legsWon +
                " | Sets: " + setsWon;
    }
}
