import java.util.Scanner;

public class Player {
    public int hit(String coordinates, Board board, int shipNum) {
        int[] coors = {
                findCoors(coordinates.charAt(0)),
                Integer.parseInt(coordinates.substring(1))
        };

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
                System.out.printf("%nError! You entered wrong coordinates! Try again:%n");
                reInput(coors);
                continue;
            }
            String ch = board.mainBoard[coors[0]][coors[1]];
            switch (ch) {
                case "O" -> {
                    board.mainBoard[coors[0]][coors[1]] = "X";
                    board.tempBoard[coors[0]][coors[1]] = "X";
//                    board.outEmptyBoard();
                    if (determineIfSank(coors[0], coors[1], board.mainBoard)) {
                        shipNum -= 1;
                        if (shipNum != 0) {
                            System.out.printf("%nYou sank a ship! Specify a new target:%n");
                        } else {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                        }

                    } else {
                        System.out.printf("%nYou hit a ship!%n");
                    }
                }
                case "X" -> {
//                    board.outEmptyBoard();
                    if (determineIfSank(coors[0], coors[1], board.mainBoard)) {
                        System.out.printf("%nYou sank a ship! Specify a new target:%n");
                    } else {
                        System.out.printf("%nYou hit a ship!%n");
                    }
                }
                case "M" -> {
//                    board.outEmptyBoard();
                    System.out.printf("%nYou missed.%n");
                }
                case "~" -> {
                    board.mainBoard[coors[0]][coors[1]] = "M";
                    board.tempBoard[coors[0]][coors[1]] = "M";
//                    board.outEmptyBoard();
                    System.out.printf("%nYou missed.%n");
                }
            }
            valid = true;
        }
        return shipNum;
    }

    private boolean determineIfSank(int row, int col, String[][] board) {
        int tmpCol = col;
        int tmpRow = row;
        boolean sank = true;
        if (col - 1 > 0) {
            if (board[row][col - 1].equals("O") || board[row][col - 1].equals("X")) {
                tmpCol = tmpCol - 1;
                while (!(board[tmpRow][tmpCol].equals("~")) && tmpCol > 0) {
                    if (board[tmpRow][tmpCol].equals("O")) {
                        sank = false;
                        break;
                    }
                    tmpCol--;
                }
            }
        }
        tmpCol = col;
        if (row - 1 > 0) {
            if (board[row - 1][col].equals("O") || board[row - 1][col].equals("X")) {
                tmpRow = tmpRow - 1;
                while (!(board[tmpRow][tmpCol].equals("~")) && tmpRow > 0) {
                    if (board[tmpRow][tmpCol].equals("O")) {
                        sank = false;
                        break;
                    }
                    tmpRow--;
                }
            }
        }
        tmpRow = row;
        if (col + 1 <= 10) {
            if (board[row][col + 1].equals("O") || board[row][col + 1].equals("X")) {
                tmpCol = tmpCol + 1;
                while (!(board[tmpRow][tmpCol].equals("~")) && tmpCol <= 10) {
                    if (board[tmpRow][tmpCol].equals("O")) {
                        sank = false;
                        break;
                    }
                    tmpCol++;
                }
            }
        }
        tmpCol = col;
        if (row + 1 <= 10) {
            if (board[row + 1][col].equals("O") || board[row + 1][col].equals("X")) {
                tmpRow += 1;
                while (!(board[tmpRow][tmpCol].equals("~")) && tmpRow <= 10) {
                    if (board[tmpRow][tmpCol].equals("O")) {
                        sank = false;
                        break;
                    }
                    tmpRow++;
                }
            }
        }
        return sank;
    }

    private int findCoors(char ch) {
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
