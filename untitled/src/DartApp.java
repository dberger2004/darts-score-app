import java.util.Scanner;

public class DartApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Name Spieler 1: ");
        String name1 = input.nextLine();
        System.out.print("Name Spieler 2: ");
        String name2 = input.nextLine();
        System.out.print("Startscore (301, 501, 701): ");
        int startingScore = input.nextInt();
        System.out.print("Legs pro Set: ");
        int legsPerSet = input.nextInt();
        System.out.print("Sets zum Gewinnen: ");
        int setsToWin = input.nextInt();

        Game game = new Game(name1, name2, startingScore, legsPerSet,
                setsToWin);
        game.start();
        input.close();
    }
}