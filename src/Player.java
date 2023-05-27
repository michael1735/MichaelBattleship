import java.util.Scanner;

public class Player {
    public void hit(String coordinates, Board board) {
        int[] coors = {
                findCoors(coordinates.charAt(0)),
                Integer.parseInt(coordinates.substring(1))
        };

        int shipType = 0;
        boolean valid = false;
        /*
        shipCoors {
        shipType: arr[5]
            coordinates of points in that ship: arr[2]
                arr of length 2 -> coordinate of point [type: int]
        }
         */
        while (!valid){
            if (coors[0] > 10 || coors[0] < 1 || coors[1] > 10 || coors[1] < 1) {
                System.out.printf("%nError! You entered wrong coordinates! Try again:%n%n> ");
                reInput(coors);
                continue;
            }
            if (board.emptyBoard[coors[0]][coors[1]].equals("O")) {
                // set the coordinates that the ship takes to "X"
                board.emptyBoard[coors[0]][coors[1]] = "X";
                board.tempBoard[coors[0]][coors[1]] = "X";
               /* for (int i = 0; i < shipCoors.length; i++) {
                    for (int j = 0; j < shipCoors[i].length; j++) {
                        if (shipCoors[i][j][0] == coors[0] && shipCoors[i][j][1] == coors[1]) {
                            shipType = i;
                            break;
                        }
                    }
                }
                for (int i = 0; i < shipCoors[shipType].length; i++) {
                    coors = new int[]{
                            shipCoors[shipType][i][0],
                            shipCoors[shipType][i][1]
                    };
                    board.emptyBoard[coors[0]][coors[1]] = "X";
                } // set all the coordinates accompanied by that ship to "X"*/
                board.outEmptyBoard();
                System.out.printf("%nYou hit a ship!%n%n");
                board.outBoard();
                valid = true;
            } else {
                board.emptyBoard[coors[0]][coors[1]] = "M";
                board.tempBoard[coors[0]][coors[1]] = "M";
                valid = true;
                board.outEmptyBoard();
                System.out.printf("%nYou missed!%n%n");
                board.outBoard();
            }
        }
    }

    public int findCoors(char ch) {
        switch (ch) {
            case 'A' -> {
                return 1;
            }
            case 'B' -> {
                return 2;
            }
            case 'C' -> {
                return 3;
            }
            case 'D' -> {
                return 4;
            }
            case 'E' -> {
                return 5;
            }
            case 'F' -> {
                return 6;
            }
            case 'G' -> {
                return 7;
            }
            case 'H' -> {
                return 8;
            }
            case 'I' -> {
                return 9;
            }
            case 'J' -> {
                return 10;
            }
        }
        return 0;
    }

    private void reInput(int[] coors) {
        Scanner tmpScanner = new Scanner(System.in);
        String first = tmpScanner.next();
        coors[0] = findCoors(first.substring(0, 1).charAt(0));
        coors[1] = Integer.parseInt(first.substring(1));
        System.out.println();
    }
}
