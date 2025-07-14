import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;
    private int legsPerSet;
    private int setsToWin;
    private Player currentPlayer;
    private Scanner scanner;

    public Game(String name1, String name2, int startingScore, int legsPerSet
            , int setsToWin) {
        this.player1 = new Player(name1, startingScore);
        this.player2 = new Player(name2, startingScore);
        this.legsPerSet = legsPerSet;
        this.setsToWin = setsToWin;
        this.currentPlayer = player1;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (!player1.hasWonMatch(setsToWin) || !player2.hasWonMatch(setsToWin)) {
            System.out.println("\n ------ Neues Leg startet ------");
            playLeg();

            if (isSetOver()) {
                handleSetWin();

                if (player1.hasWonMatch(setsToWin)) {
                    System.out.println(player1.getName() + " hat " +
                            player1.getSetsWon() + "-" + player2.getSetsWon() +
                            " gewonnen.");
                    break;
                }

                if (player2.hasWonMatch(setsToWin)) {
                    System.out.println(player2 + " hat gewonnen.");
                    break;
                }
            }
        }
    }

    private void playLeg() {
        player1.resetForNewLeg();
        player2.resetForNewLeg();
        currentPlayer = player1;

        while (true) {
            System.out.println(currentPlayer.getName() + " ist dran.");
            int hit = promptScore();
            currentPlayer.subtractScore(hit);
            displayCurrentScores();

            if (currentPlayer.hasWonLeg()) {
                System.out.println(currentPlayer.getName() + " hat das Leg " +
                        "gewonnen.");
                break;
            }
            switchPlayer();
        }
    }

    private int promptScore() {
        int hit;
        System.out.print("Geworfene Punktzahl eingeben (0-180): ");
        while (true) {
            if (scanner.hasNextInt()) {
                hit = scanner.nextInt();
                if (hit >= 0 && hit <= 180) {
                    return hit;
                }
                System.out.print("Ungültig! Zahl muss zwischen 0-180 sein. " +
                        "Punktzahl: ");
            } else {
                System.out.print("Ungültig! Bitte Zahl eingeben. Punktzahl: ");
                scanner.next();
            }
        }
    }

    private void displayCurrentScores() {
        System.out.println(player1);
        System.out.println(player2);
    }

    private boolean isSetOver() {
        return player1.getLegsWon() == legsPerSet
                || player2.getLegsWon() == legsPerSet;
    }

    private void handleSetWin() {
        if (player1.getLegsWon() == legsPerSet) {
            player1.winSet();
            System.out.println(player1.getName() + " hat das Set gewonnen.");
        } else {
            player2.winSet();
            System.out.println(player2.getName() + " hat das Set gewonnen.");
        }

        player1.resetForNewSet();
        player2.resetForNewSet();
        player1.resetForNewSet();
        player2.resetForNewSet();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}
