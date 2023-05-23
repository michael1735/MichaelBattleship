import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Board board = new Board();
        board.outBoard();

        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):\n");
        System.out.print("> ");
        String coordinates = scanner.nextLine();

        AircraftCarrier aircraftCarrier = new AircraftCarrier(coordinates);
        board.placeShip(aircraftCarrier);

        System.out.printf("Enter the coordinates of the Battleship (4 cells):%n%n> ");
        coordinates = scanner.nextLine();
        Battleship battleship = new Battleship(coordinates);
        board.placeShip(battleship);

        System.out.printf("Enter the coordinates of the Submarine (3 cells):%n%n> ");
        coordinates = scanner.nextLine();
        Submarine submarine = new Submarine(coordinates);
        board.placeShip(submarine);

        System.out.printf("Enter the coordinates of the Cruiser (3 cells):%n%n> ");
        coordinates = scanner.nextLine();
        Cruiser cruiser = new Cruiser(coordinates);
        board.placeShip(cruiser);

        System.out.printf("Enter the coordinates of the Destroyer (2 cells):%n%n> ");
        coordinates = scanner.nextLine();
        Destroyer destroyer = new Destroyer(coordinates);
        board.placeShip(destroyer);

        System.out.printf("%nThe game starts!%n%n");
        board.outEmptyBoard();
        Player player1 = new Player();
        System.out.printf("%nTake a shot!%n%n> ");
        String hitCoor = scanner.next();
        player1.hit(hitCoor, board);
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
     */
}
