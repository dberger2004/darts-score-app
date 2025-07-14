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
        while (!player1.hasWonMatch(setsToWin) && !player2.hasWonMatch(setsToWin)) {
            System.out.println("\n ------ Neues Leg startet ------");
            playLeg();

            if (isSetOver()) {
                handleSetWin();
            }
        }
        announceWinner();
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
        System.out.print("Geworfene Punktzahl eingeben: ");
        while (!scanner.hasNextInt() || scanner.nextInt() > 180) {
            System.out.println("Ungültig! Bitte Zahl eingeben: ");
            scanner.next();
        }
        return scanner.nextInt();
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
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private void announceWinner() {
        if (player1.hasWonMatch(setsToWin)) {
            System.out.println("\n>>> " + player1.getName() + " hat gewonnen.");
        } else {
            System.out.println("\n>>> " + player2.getName() + " hat gewonnen.");
        }
    }
}
