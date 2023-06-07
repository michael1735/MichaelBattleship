import java.util.Scanner;

public class Board {
    public String[][] mainBoard = {
            {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
            {"A", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"B", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"C", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"D", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"E", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"F", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"G", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"H", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"I", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"J", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}
    };

    public String[][] tempBoard = {
            {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
            {"A", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"B", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"C", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"D", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"E", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"F", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"G", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"H", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"I", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"J", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}
    };

    public void placeShip(AircraftCarrier ship) {
        int[] firstCoor = new int[]{
                findCoors(ship.coordinates[0].substring(0, 1).charAt(0)),
                Integer.parseInt(ship.coordinates[0].substring(1))
        };
        int[] secondCoor = new int[]{
                findCoors(ship.coordinates[1].substring(0, 1).charAt(0)),
                Integer.parseInt(ship.coordinates[1].substring(1))
        };
        boolean valid = false;
        boolean vertical = true;

        outer:
        while (!valid) {
            // 解决交叠问题, 直接循环secondCoors[1].parseInt() - firstCoor[1].parseInt()次, 查找array里的三个数
            int max;
            int min;
            int index;
            if (firstCoor[0] == secondCoor[0]) {
                max = Math.max(firstCoor[1], secondCoor[1]);
                min = Math.min(firstCoor[1], secondCoor[1]);
                index = firstCoor[0]; // easier for array queries
                vertical = false;
            } else if (firstCoor[1] == secondCoor[1]) {
                max = Math.max(firstCoor[0], secondCoor[0]);
                min = Math.min(firstCoor[0], secondCoor[0]);
                index = firstCoor[1]; // easier for array queries
            } else { // this can be simplified to else
                // 输出报错, 重新输入
                System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                reInput(firstCoor, secondCoor);
                continue;
            }

            // in case they don't have length 5
            if (max - min + 1 != ship.getLength()) {
                // 输出报错信息, 重新输入
                System.out.printf("%nError! Wrong length of the %s! Try again:%n%n> ", ship.getName());
                reInput(firstCoor, secondCoor);
                continue;
            }
            // in case they overlap
            for (int i = min; i <= max; i++) {
                if (firstCoor[0] == secondCoor[0]) {
                    if (mainBoard[index][i].equals("O")) {
                        // 输出报错, 重新输入
                        System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue outer;
                    }
                } else if (firstCoor[1] == secondCoor[1]) {
                    if (mainBoard[i][index].equals("O")) {
                        // 输出报错, 重新输入
                        System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue outer;
                    }
                }
            }

            // in case they are close to another ship
            /*
             1  |       2       | 3
            _______________________
             4  | x  x  x  x  x | 5
            _______________________
             6  |       7       | 8
             */

            // 1
            if (!vertical) {
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        if (mainBoard[index - 1][min - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            } else {
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        if (mainBoard[min - 1][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }

            if (!vertical) { // 2
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        for (int i = min; i <= max; i++) {
                            if (mainBoard[index - 1][i].equals("O")) {
                                System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                                reInput(firstCoor, secondCoor);
                                continue outer;
                            }
                        }
                    }
                }
            } else {
                if (min - 1 >= 0) {
                    if (mainBoard[min - 1][index].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            }

            // 3
            if (!vertical) {
                if (index - 1 > 0) {
                    if (max + 1 < 11) {
                        if (mainBoard[index - 1][max + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            } else {
                if (min - 1 >= 0) {
                    if (index + 1 <= 10) {
                        if (mainBoard[min - 1][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }
            // 6
            if (!vertical) {
                if (index - 1 >= 10 && min - 1 >= 0) {
                    if (mainBoard[index - 1][min - 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (index - 1 <= 0) {
                        if (mainBoard[max + 1][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }
            // 8
            if (!vertical) {
                if (index + 1 <= 10 && max + 1 <= 10) {
                    if (mainBoard[index + 1][max + 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (index + 1 <= 10) {
                        if (mainBoard[max + 1][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }

            // 7
            if (!vertical) {
                if (index + 1 <= 10) {
                    for (int i = min; i <= max; i++) {
                        if (mainBoard[index - 1][i].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (mainBoard[max + 1][index].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            }

            // 4
            if (!vertical) {
                if (min - 1 >= 0) {
                    if (mainBoard[index][min - 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (index - 1 >= 0) {
                    for (int i = min; i <= max ; i++) {
                        if (mainBoard[i][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            }
            // 5
            if (!vertical) {
                if (max + 1 <= 10) {
                    if (mainBoard[index][max + 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (index + 1 <= 10) {
                    for (int i = min; i <= max; i++) {
                        if (mainBoard[i][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            }

            // if there are no input problems
            valid = true;

            for (int i = min; i <= max; i++) {
                if (firstCoor[0] == secondCoor[0]) {
                    mainBoard[index][i] = "O";
                } else if (firstCoor[1] == secondCoor[1]) {
                    mainBoard[i][index] = "O";
                }
            }
        }
        System.out.println();
        outBoard();
    }

    public void placeShip(Battleship ship) {
        int[] firstCoor = new int[]{
                findCoors(ship.coordinates[0].substring(0, 1).charAt(0)),
                Integer.parseInt(ship.coordinates[0].substring(1))
        };
        int[] secondCoor = new int[]{
                findCoors(ship.coordinates[1].substring(0, 1).charAt(0)),
                Integer.parseInt(ship.coordinates[1].substring(1))
        };
        boolean valid = false;
        boolean vertical = true;

        outer:
        while (!valid) {
            // 解决交叠问题, 直接循环secondCoors[1].parseInt() - firstCoor[1].parseInt()次, 查找array里的三个数
            int max;
            int min;
            int index;
            if (firstCoor[0] == secondCoor[0]) {
                max = Math.max(firstCoor[1], secondCoor[1]);
                min = Math.min(firstCoor[1], secondCoor[1]);
                index = firstCoor[0]; // easier for array queries
                vertical = false;
            } else if (firstCoor[1] == secondCoor[1]) {
                max = Math.max(firstCoor[0], secondCoor[0]);
                min = Math.min(firstCoor[0], secondCoor[0]);
                index = firstCoor[1]; // easier for array queries
            } else { // this can be simplified to else
                // 输出报错, 重新输入
                System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                reInput(firstCoor, secondCoor);
                continue;
            }

            // in case they don't have length 5
            if (max - min + 1 != ship.getLength()) {
                // 输出报错信息, 重新输入
                System.out.printf("%nError! Wrong length of the %s! Try again:%n%n> ", ship.getName());
                reInput(firstCoor, secondCoor);
                continue;
            }
            // in case they overlap
            for (int i = min; i <= max; i++) {
                if (firstCoor[0] == secondCoor[0]) {
                    if (mainBoard[index][i].equals("O")) {
                        // 输出报错, 重新输入
                        System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue outer;
                    }
                } else if (firstCoor[1] == secondCoor[1]) {
                    if (mainBoard[i][index].equals("O")) {
                        // 输出报错, 重新输入
                        System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue outer;
                    }
                }
            }

            // in case they are close to another ship
            /*
             1  |       2       | 3
            _______________________
             4  | x  x  x  x  x | 5
            _______________________
             6  |       7       | 8
             */

            // 1
            if (!vertical) {
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        if (mainBoard[index - 1][min - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            } else {
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        if (mainBoard[min - 1][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }

            if (!vertical) { // 2
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        for (int i = min; i <= max; i++) {
                            if (mainBoard[index - 1][i].equals("O")) {
                                System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                                reInput(firstCoor, secondCoor);
                                continue outer;
                            }
                        }
                    }
                }
            } else {
                if (min - 1 >= 0) {
                    if (mainBoard[min - 1][index].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            }

            // 3
            if (!vertical) {
                if (index - 1 > 0) {
                    if (max + 1 < 11) {
                        if (mainBoard[index - 1][max + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            } else {
                if (min - 1 >= 0) {
                    if (index + 1 <= 10) {
                        if (mainBoard[min - 1][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }
            // 6
            if (!vertical) {
                if (index - 1 >= 10 && min - 1 >= 0) {
                    if (mainBoard[index - 1][min - 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (index - 1 <= 0) {
                        if (mainBoard[max + 1][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }
            // 8
            if (!vertical) {
                if (index + 1 <= 10 && max + 1 <= 10) {
                    if (mainBoard[index + 1][max + 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (index + 1 <= 10) {
                        if (mainBoard[max + 1][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }

            // 7
            if (!vertical) {
                if (index + 1 <= 10) {
                    for (int i = min; i <= max; i++) {
                        if (mainBoard[index - 1][i].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (mainBoard[max + 1][index].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            }

            // 4
            if (!vertical) {
                if (min - 1 >= 0) {
                    if (mainBoard[index][min - 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (index - 1 >= 0) {
                    for (int i = min; i <= max ; i++) {
                        if (mainBoard[i][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            }
            // 5
            if (!vertical) {
                if (max + 1 <= 10) {
                    if (mainBoard[index][max + 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (index + 1 <= 10) {
                    for (int i = min; i <= max; i++) {
                        if (mainBoard[i][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            }

            // if there are no input problems
            valid = true;

            for (int i = min; i <= max; i++) {
                if (firstCoor[0] == secondCoor[0]) {
                    mainBoard[index][i] = "O";
                } else if (firstCoor[1] == secondCoor[1]) {
                    mainBoard[i][index] = "O";
                }
            }
        }
        System.out.println();
        outBoard();
    }

    public void placeShip(Submarine ship) {
        int[] firstCoor = new int[]{
                findCoors(ship.coordinates[0].substring(0, 1).charAt(0)),
                Integer.parseInt(ship.coordinates[0].substring(1))
        };
        int[] secondCoor = new int[]{
                findCoors(ship.coordinates[1].substring(0, 1).charAt(0)),
                Integer.parseInt(ship.coordinates[1].substring(1))
        };
        boolean valid = false;
        boolean vertical = true;

        outer:
        while (!valid) {
            // 解决交叠问题, 直接循环secondCoors[1].parseInt() - firstCoor[1].parseInt()次, 查找array里的三个数
            int max;
            int min;
            int index;
            if (firstCoor[0] == secondCoor[0]) {
                max = Math.max(firstCoor[1], secondCoor[1]);
                min = Math.min(firstCoor[1], secondCoor[1]);
                index = firstCoor[0]; // easier for array queries
                vertical = false;
            } else if (firstCoor[1] == secondCoor[1]) {
                max = Math.max(firstCoor[0], secondCoor[0]);
                min = Math.min(firstCoor[0], secondCoor[0]);
                index = firstCoor[1]; // easier for array queries
            } else { // this can be simplified to else
                // 输出报错, 重新输入
                System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                reInput(firstCoor, secondCoor);
                continue;
            }

            // in case they don't have length 5
            if (max - min + 1 != ship.getLength()) {
                // 输出报错信息, 重新输入
                System.out.printf("%nError! Wrong length of the %s! Try again:%n%n> ", ship.getName());
                reInput(firstCoor, secondCoor);
                continue;
            }
            // in case they overlap
            for (int i = min; i <= max; i++) {
                if (firstCoor[0] == secondCoor[0]) {
                    if (mainBoard[index][i].equals("O")) {
                        // 输出报错, 重新输入
                        System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue outer;
                    }
                } else if (firstCoor[1] == secondCoor[1]) {
                    if (mainBoard[i][index].equals("O")) {
                        // 输出报错, 重新输入
                        System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue outer;
                    }
                }
            }

            // in case they are close to another ship
            /*
             1  |       2       | 3
            _______________________
             4  | x  x  x  x  x | 5
            _______________________
             6  |       7       | 8
             */

            // 1
            if (!vertical) {
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        if (mainBoard[index - 1][min - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            } else {
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        if (mainBoard[min - 1][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }

            if (!vertical) { // 2
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        for (int i = min; i <= max; i++) {
                            if (mainBoard[index - 1][i].equals("O")) {
                                System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                                reInput(firstCoor, secondCoor);
                                continue outer;
                            }
                        }
                    }
                }
            } else {
                if (min - 1 >= 0) {
                    if (mainBoard[min - 1][index].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            }

            // 3
            if (!vertical) {
                if (index - 1 > 0) {
                    if (max + 1 < 11) {
                        if (mainBoard[index - 1][max + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            } else {
                if (min - 1 >= 0) {
                    if (index + 1 <= 10) {
                        if (mainBoard[min - 1][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }
            // 6
            if (!vertical) {
                if (index - 1 >= 10 && min - 1 >= 0) {
                    if (mainBoard[index - 1][min - 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (index - 1 <= 0) {
                        if (mainBoard[max + 1][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }
            // 8
            if (!vertical) {
                if (index + 1 <= 10 && max + 1 <= 10) {
                    if (mainBoard[index + 1][max + 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (index + 1 <= 10) {
                        if (mainBoard[max + 1][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }

            // 7
            if (!vertical) {
                if (index + 1 <= 10) {
                    for (int i = min; i <= max; i++) {
                        if (mainBoard[index - 1][i].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (mainBoard[max + 1][index].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            }

            // 4
            if (!vertical) {
                if (min - 1 >= 0) {
                    if (mainBoard[index][min - 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (index - 1 >= 0) {
                    for (int i = min; i <= max ; i++) {
                        if (mainBoard[i][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            }
            // 5
            if (!vertical) {
                if (max + 1 <= 10) {
                    if (mainBoard[index][max + 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (index + 1 <= 10) {
                    for (int i = min; i <= max; i++) {
                        if (mainBoard[i][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            }

            // if there are no input problems
            valid = true;

            for (int i = min; i <= max; i++) {
                if (firstCoor[0] == secondCoor[0]) {
                    mainBoard[index][i] = "O";
                } else if (firstCoor[1] == secondCoor[1]) {
                    mainBoard[i][index] = "O";
                }
            }
        }
        System.out.println();
        outBoard();
    }

    public void placeShip(Cruiser ship) {
        int[] firstCoor = new int[]{
                findCoors(ship.coordinates[0].substring(0, 1).charAt(0)),
                Integer.parseInt(ship.coordinates[0].substring(1))
        };
        int[] secondCoor = new int[]{
                findCoors(ship.coordinates[1].substring(0, 1).charAt(0)),
                Integer.parseInt(ship.coordinates[1].substring(1))
        };
        boolean valid = false;
        boolean vertical = true;

        outer:
        while (!valid) {
            // 解决交叠问题, 直接循环secondCoors[1].parseInt() - firstCoor[1].parseInt()次, 查找array里的三个数
            int max;
            int min;
            int index;
            if (firstCoor[0] == secondCoor[0]) {
                max = Math.max(firstCoor[1], secondCoor[1]);
                min = Math.min(firstCoor[1], secondCoor[1]);
                index = firstCoor[0]; // easier for array queries
                vertical = false;
            } else if (firstCoor[1] == secondCoor[1]) {
                max = Math.max(firstCoor[0], secondCoor[0]);
                min = Math.min(firstCoor[0], secondCoor[0]);
                index = firstCoor[1]; // easier for array queries
            } else { // this can be simplified to else
                // 输出报错, 重新输入
                System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                reInput(firstCoor, secondCoor);
                continue;
            }

            // in case they don't have length 5
            if (max - min + 1 != ship.getLength()) {
                // 输出报错信息, 重新输入
                System.out.printf("%nError! Wrong length of the %s! Try again:%n%n> ", ship.getName());
                reInput(firstCoor, secondCoor);
                continue;
            }
            // in case they overlap
            for (int i = min; i <= max; i++) {
                if (firstCoor[0] == secondCoor[0]) {
                    if (mainBoard[index][i].equals("O")) {
                        // 输出报错, 重新输入
                        System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue outer;
                    }
                } else if (firstCoor[1] == secondCoor[1]) {
                    if (mainBoard[i][index].equals("O")) {
                        // 输出报错, 重新输入
                        System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue outer;
                    }
                }
            }

            // in case they are close to another ship
            /*
             1  |       2       | 3
            _______________________
             4  | x  x  x  x  x | 5
            _______________________
             6  |       7       | 8
             */

            // 1
            if (!vertical) {
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        if (mainBoard[index - 1][min - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            } else {
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        if (mainBoard[min - 1][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }

            if (!vertical) { // 2
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        for (int i = min; i <= max; i++) {
                            if (mainBoard[index - 1][i].equals("O")) {
                                System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                                reInput(firstCoor, secondCoor);
                                continue outer;
                            }
                        }
                    }
                }
            } else {
                if (min - 1 >= 0) {
                    if (mainBoard[min - 1][index].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            }

            // 3
            if (!vertical) {
                if (index - 1 > 0) {
                    if (max + 1 < 11) {
                        if (mainBoard[index - 1][max + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            } else {
                if (min - 1 >= 0) {
                    if (index + 1 <= 10) {
                        if (mainBoard[min - 1][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }
            // 6
            if (!vertical) {
                if (index - 1 >= 10 && min - 1 >= 0) {
                    if (mainBoard[index - 1][min - 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (index - 1 <= 0) {
                        if (mainBoard[max + 1][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }
            // 8
            if (!vertical) {
                if (index + 1 <= 10 && max + 1 <= 10) {
                    if (mainBoard[index + 1][max + 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (index + 1 <= 10) {
                        if (mainBoard[max + 1][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }

            // 7
            if (!vertical) {
                if (index + 1 <= 10) {
                    for (int i = min; i <= max; i++) {
                        if (mainBoard[index - 1][i].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (mainBoard[max + 1][index].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            }

            // 4
            if (!vertical) {
                if (min - 1 >= 0) {
                    if (mainBoard[index][min - 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (index - 1 >= 0) {
                    for (int i = min; i <= max ; i++) {
                        if (mainBoard[i][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            }
            // 5
            if (!vertical) {
                if (max + 1 <= 10) {
                    if (mainBoard[index][max + 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (index + 1 <= 10) {
                    for (int i = min; i <= max; i++) {
                        if (mainBoard[i][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            }

            // if there are no input problems
            valid = true;

            for (int i = min; i <= max; i++) {
                if (firstCoor[0] == secondCoor[0]) {
                    mainBoard[index][i] = "O";
                } else if (firstCoor[1] == secondCoor[1]) {
                    mainBoard[i][index] = "O";
                }
            }
        }
        System.out.println();
        outBoard();
    }

    public void placeShip(Destroyer ship) {
        int[] firstCoor = new int[]{
                findCoors(ship.coordinates[0].substring(0, 1).charAt(0)),
                Integer.parseInt(ship.coordinates[0].substring(1))
        };
        int[] secondCoor = new int[]{
                findCoors(ship.coordinates[1].substring(0, 1).charAt(0)),
                Integer.parseInt(ship.coordinates[1].substring(1))
        };
        boolean valid = false;
        boolean vertical = true;

        outer:
        while (!valid) {
            // 解决交叠问题, 直接循环secondCoors[1].parseInt() - firstCoor[1].parseInt()次, 查找array里的三个数
            int max;
            int min;
            int index;
            if (firstCoor[0] == secondCoor[0]) {
                max = Math.max(firstCoor[1], secondCoor[1]);
                min = Math.min(firstCoor[1], secondCoor[1]);
                index = firstCoor[0]; // easier for array queries
                vertical = false;
            } else if (firstCoor[1] == secondCoor[1]) {
                max = Math.max(firstCoor[0], secondCoor[0]);
                min = Math.min(firstCoor[0], secondCoor[0]);
                index = firstCoor[1]; // easier for array queries
            } else { // this can be simplified to else
                // 输出报错, 重新输入
                System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                reInput(firstCoor, secondCoor);
                continue;
            }

            // in case they don't have length 5
            if (max - min + 1 != ship.getLength()) {
                // 输出报错信息, 重新输入
                System.out.printf("%nError! Wrong length of the %s! Try again:%n%n> ", ship.getName());
                reInput(firstCoor, secondCoor);
                continue;
            }
            // in case they overlap
            for (int i = min; i <= max; i++) {
                if (firstCoor[0] == secondCoor[0]) {
                    if (mainBoard[index][i].equals("O")) {
                        // 输出报错, 重新输入
                        System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue outer;
                    }
                } else if (firstCoor[1] == secondCoor[1]) {
                    if (mainBoard[i][index].equals("O")) {
                        // 输出报错, 重新输入
                        System.out.printf("%nError! Wrong ship location! Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue outer;
                    }
                }
            }

            // in case they are close to another ship
            /*
             1  |       2       | 3
            _______________________
             4  | x  x  x  x  x | 5
            _______________________
             6  |       7       | 8
             */

            // 1
            if (!vertical) {
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        if (mainBoard[index - 1][min - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            } else {
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        if (mainBoard[min - 1][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }

            if (!vertical) { // 2
                if (index - 1 >= 0) {
                    if (min - 1 >= 0) {
                        for (int i = min; i <= max; i++) {
                            if (mainBoard[index - 1][i].equals("O")) {
                                System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                                reInput(firstCoor, secondCoor);
                                continue outer;
                            }
                        }
                    }
                }
            } else {
                if (min - 1 >= 0) {
                    if (mainBoard[min - 1][index].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            }

            // 3
            if (!vertical) {
                if (index - 1 > 0) {
                    if (max + 1 < 11) {
                        if (mainBoard[index - 1][max + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            } else {
                if (min - 1 >= 0) {
                    if (index + 1 <= 10) {
                        if (mainBoard[min - 1][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }
            // 6
            if (!vertical) {
                if (index - 1 >= 10 && min - 1 >= 0) {
                    if (mainBoard[index - 1][min - 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (index - 1 <= 0) {
                        if (mainBoard[max + 1][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }
            // 8
            if (!vertical) {
                if (index + 1 <= 10 && max + 1 <= 10) {
                    if (mainBoard[index + 1][max + 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (index + 1 <= 10) {
                        if (mainBoard[max + 1][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue;
                        }
                    }
                }
            }

            // 7
            if (!vertical) {
                if (index + 1 <= 10) {
                    for (int i = min; i <= max; i++) {
                        if (mainBoard[index - 1][i].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            } else {
                if (max + 1 <= 10) {
                    if (mainBoard[max + 1][index].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            }

            // 4
            if (!vertical) {
                if (min - 1 >= 0) {
                    if (mainBoard[index][min - 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (index - 1 >= 0) {
                    for (int i = min; i <= max ; i++) {
                        if (mainBoard[i][index - 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            }
            // 5
            if (!vertical) {
                if (max + 1 <= 10) {
                    if (mainBoard[index][max + 1].equals("O")) {
                        System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                        reInput(firstCoor, secondCoor);
                        continue;
                    }
                }
            } else {
                if (index + 1 <= 10) {
                    for (int i = min; i <= max; i++) {
                        if (mainBoard[i][index + 1].equals("O")) {
                            System.out.printf("%nError! You placed it too close to another one. Try again:%n%n> ");
                            reInput(firstCoor, secondCoor);
                            continue outer;
                        }
                    }
                }
            }

            // if there are no input problems
            valid = true;

            for (int i = min; i <= max; i++) {
                if (firstCoor[0] == secondCoor[0]) {
                    mainBoard[index][i] = "O";
                } else if (firstCoor[1] == secondCoor[1]) {
                    mainBoard[i][index] = "O";
                }
            }
        }
        System.out.println();
        outBoard();
    }

    private void reInput(int[] firstCoor, int[] secondCoor) {
        Scanner tmpScanner = new Scanner(System.in);
        String first = tmpScanner.next();
        String next = tmpScanner.next();
        firstCoor[0] = findCoors(first.substring(0, 1).charAt(0)); // problem, 输入的永远是斜着的
        firstCoor[1] = Integer.parseInt(first.substring(1));
        secondCoor[0] = findCoors(next.substring(0, 1).charAt(0));
        secondCoor[1] = Integer.parseInt(next.substring(1));
    }

    // used to map the alphabetical coordinates of the ship to the index of the array
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

    public void outBoard() {
        for (String[] strings : mainBoard) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

    public void outEmptyBoard() {
        for (String[] strings : tempBoard) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
}
