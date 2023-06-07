import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    /**
     * If you input on the wrong input field, the program will produce unexpected behaviours...
     */
    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        Board board1 = new Board();
        Board board2 = new Board();
        int player1ShipNum = 5;
        int player2ShipNum = 5;
        String hitCoor, emptyLine;
        boolean isPlayer1 = true;
        System.out.println("Player1, place your ships on the game field\n");
        board1.outEmptyBoard();
        System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells):\n");
        System.out.print("> ");
        String coordinates = scanner.nextLine();
        AircraftCarrier aircraftCarrier1 = new AircraftCarrier(coordinates);
        board1.placeShip(aircraftCarrier1);

        System.out.printf("\nEnter the coordinates of the Battleship (4 cells):%n%n> ");
        coordinates = scanner.nextLine();
        Battleship battleship1 = new Battleship(coordinates);
        board1.placeShip(battleship1);

        System.out.printf("\nEnter the coordinates of the Submarine (3 cells):%n%n> ");
        coordinates = scanner.nextLine();
        Submarine submarine1 = new Submarine(coordinates);
        board1.placeShip(submarine1);

        System.out.printf("\nEnter the coordinates of the Cruiser (3 cells):%n%n> ");
        coordinates = scanner.nextLine();
        Cruiser cruiser1 = new Cruiser(coordinates);
        board1.placeShip(cruiser1);

        System.out.printf("\nEnter the coordinates of the Destroyer (2 cells):%n%n> ");
        coordinates = scanner.nextLine();
        Destroyer destroyer1 = new Destroyer(coordinates);
        board1.placeShip(destroyer1);

        System.out.print("""

                Press Enter and pass the move to another player
                ...""");
        emptyLine = scanner.nextLine();
        System.out.println("Player 2, place your ships to the game field\n");
        board2.outEmptyBoard();

        System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells):\n");
        System.out.print("> ");
        coordinates = scanner.nextLine();
        AircraftCarrier aircraftCarrier2 = new AircraftCarrier(coordinates);
        board2.placeShip(aircraftCarrier2);

        System.out.printf("\nEnter the coordinates of the Battleship (4 cells):%n%n> ");
        coordinates = scanner.nextLine();
        Battleship battleship2 = new Battleship(coordinates);
        board2.placeShip(battleship2);

        System.out.printf("\nEnter the coordinates of the Submarine (3 cells):%n%n> ");
        coordinates = scanner.nextLine();
        Submarine submarine2 = new Submarine(coordinates);
        board2.placeShip(submarine2);

        System.out.printf("\nEnter the coordinates of the Cruiser (3 cells):%n%n> ");
        coordinates = scanner.nextLine();
        Cruiser cruiser2 = new Cruiser(coordinates);
        board2.placeShip(cruiser2);

        System.out.printf("\nEnter the coordinates of the Destroyer (2 cells):%n%n> ");
        coordinates = scanner.nextLine();
        Destroyer destroyer2 = new Destroyer(coordinates);
        board2.placeShip(destroyer2);
        System.out.println();

        while (player1ShipNum != 0 && player2ShipNum != 0) {
            System.out.print("""
                    Press Enter and pass the move to another player
                    ...""");
            emptyLine = scanner.nextLine();
            System.out.println();
            if (isPlayer1) {
                board2.outEmptyBoard();
                System.out.println("---------------------");
                board1.outBoard();
                System.out.printf("%nPlayer 1, it's your turn:%n%n> ");
                hitCoor = scanner.next();
                player2ShipNum = player1.hit(hitCoor, board2, player2ShipNum);
                isPlayer1 = false;
            } else {
                board1.outEmptyBoard();
                System.out.println("---------------------");
                board2.outBoard();
                System.out.printf("%nPlayer 2, it's your turn:%n%n> ");
                hitCoor = scanner.next();
                player1ShipNum = player2.hit(hitCoor, board1, player1ShipNum);
                isPlayer1 = true;
            }
            emptyLine = scanner.nextLine();
        }
    }
    /*
    F3 F7
    A1 D1
    J7 J10 -> wrong length of submarine
    J10 J8
    B9 D8 -> wrong ship location
    B9 D9
    E6 D6 -> place it too close to another one
    I2 J2

    H2 H6
    F3 F6
    H8 F8
    D4 D6
    C8 D8
     */
}
