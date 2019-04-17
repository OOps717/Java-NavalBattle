import java.util.Scanner;

public class PlayerImpl {
    private char[][] board;

    public PlayerImpl(char[][] board) {
        this.board = board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                this.board[i][j] = '-';
            }
        }
    }


    public void printBoard (String player){
        System.out.println("\nPlayer's" + " " + player + " board");
        char letter = 97;

        for (int r = 0; r < board.length + 1; r++) {
            for (int c = 0; c < board[0].length + 1; c++) {
                if (r == 0 && c == 0) System.out.print("  ");
                else if (r == 0 && c >= 0) {
                    System.out.print(letter + " ");
                    letter++;
                } else if (c == 0 && r >= 0) {
                    if (r == board.length) System.out.print(r);
                    else System.out.print(r + " ");
                } else System.out.print(board[r - 1][c - 1] + " ");
            }
            System.out.println("");
        }
    }

    public char[][] Play(String player) {
        Scanner scan = new Scanner(System.in);
        boolean okay = true;
        int Horizdir = 0;
        int S4 = 1, S3 = 2, S2 = 3, S1 = 4;

        System.out.println("Rules:\n" +
                "1. You have right to choose ships in 4,3,2 and 1 cell\n" +
                "2. You can choose in which direction you can place your ship\n" +
                "3. Do not cheat\n" +
                "4. You can place only 1 4-celled ship, 2 3-celled ships, 3 2-celled ships and 4 1-celled ship\n" +
                "5. Have fun!");

        while (S1 != 0 || S2 != 0 || S3 != 0 || S4 != 0) {
            System.out.println("Choose and place your ship:\n" +
                    "Enter 4 for choosing 4-celled ship\n" +
                    "Enter 3 for choosing 3-celled ship\n" +
                    "Enter 2 for choosing 2-celled ship\n" +
                    "Enter 1 for choosing 1-celled ship\n");

            char ShipCell = scan.next().charAt(0);

            if ((ShipCell == '1' && S1 == 0) || (ShipCell == '2' && S2 == 0) || (ShipCell == '3' && S3 == 0) || (ShipCell == '4' && S4 == 0)) {
                System.out.println("You've already put enough qty of ships, try again");
                continue;
            }

            switch (ShipCell) {
                case '1':
                    System.out.print("You've chosen 1-celled. ");
                    S1--;
                    break;
                case '2':
                    S2--;
                    System.out.print("You've chosen 2-celled. ");
                    break;
                case '3':
                    S3--;
                    System.out.print("You've chosen 3-celled. ");
                    break;
                case '4':
                    S4--;
                    System.out.print("You've chosen 4-celled. ");
                    break;
                default:
                    System.out.println("Wrong input, Should be only integers 4,3,2 or 1");
                    continue;
            }

            System.out.println("Nice choice! Now please choose where to start to put");
            System.out.println("Firstly choose on vertical direction (1-10)");
            int Vertdir = scan.nextInt();
            while (Vertdir < 0 || Vertdir > 10) {
                System.out.println("You should choose only from 1-10! Try again");
                Vertdir = scan.nextInt();
            }
            System.out.println("For now choose on horizontal direction (a-j)");
            char H = scan.next().charAt(0);
            H = Character.toLowerCase(H);
            while (H < 97 || H > 106) {
                System.out.println("You should choose only from a-j! Try again");
                H = scan.next().charAt(0);
                H = Character.toLowerCase(H);
            }
            Horizdir = H - 96;

            if (ShipCell == '1') {
                if (board[Vertdir - 1][Horizdir - 1] == '#') {
                    System.out.println("This cell is already occupied, try again");
                    S1++;
                } else {
                    board[Vertdir - 1][Horizdir - 1] = '#';
                }
            } else {


                System.out.println("Choose l, r, u or d to choose direction left, right, up or down");
                char Dir = scan.next().charAt(0);
                Dir = Character.toLowerCase(Dir);
                while (Dir != 'l' && Dir != 'r' && Dir != 'u' && Dir != 'd') {
                    System.out.println("Only l, r, u or d");
                    Dir = scan.next().charAt(0);
                    Dir = Character.toLowerCase(Dir);
                }
                if (Dir == 'l') {
                    if ((Horizdir <= 3 && ShipCell == '4') || (Horizdir <= 2 && ShipCell == '3') || (Horizdir <= 1 && ShipCell == '2')) {
                        System.out.println("Not appropriate direction to set at this cell, try again");
                        if (ShipCell == '2') S2++;
                        if (ShipCell == '3') S3++;
                        if (ShipCell == '4') S4++;
                    } else {
                        for (int i = Horizdir - 1; i > Horizdir - Character.getNumericValue(ShipCell) - 1; i--) {
                            if (board[Vertdir - 1][i] == '#') {
                                okay = false;
                            }
                        }
                        if (okay) {
                            for (int i = Horizdir - 1; i > Horizdir - Character.getNumericValue(ShipCell) - 1; i--) {
                                board[Vertdir - 1][i] = '#';
                            }
                        } else {
                            System.out.println("Whopps, the other ship is alredy placed there! Try again");
                            if (ShipCell == '2') S2++;
                            if (ShipCell == '3') S3++;
                            if (ShipCell == '4') S4++;
                            okay = true;
                        }
                    }
                }

                if (Dir == 'r') {
                    if ((Horizdir >= 8 && ShipCell == '4') || (Horizdir >= 9 && ShipCell == '3') || (Horizdir >= 10 && ShipCell == '2')) {
                        System.out.println("Not appropriate direction to set at this cell, try again");
                        if (ShipCell == '2') S2++;
                        if (ShipCell == '3') S3++;
                        if (ShipCell == '4') S4++;
                    } else {
                        for (int i = Horizdir - 1; i < Horizdir + Character.getNumericValue(ShipCell) - 1; i++) {
                            if (board[Vertdir - 1][i] == '#') {
                                okay = false;
                            }
                        }
                        if (okay) {
                            for (int i = Horizdir - 1; i < Horizdir + Character.getNumericValue(ShipCell) - 1; i++) {
                                board[Vertdir - 1][i] = '#';
                            }
                        } else {
                            System.out.println("Whopps, the other ship is alredy placed there! Try again");
                            if (ShipCell == '2') S2++;
                            if (ShipCell == '3') S3++;
                            if (ShipCell == '4') S4++;
                            okay = true;
                        }
                    }
                }
                if (Dir == 'd') {
                    if ((Vertdir >= 8 && ShipCell == '4') || (Vertdir >= 9 && ShipCell == '3') || (Vertdir == 10 && ShipCell == '2')) {
                        System.out.println("Not appropriate direction to set at this cell, try again");
                        if (ShipCell == '2') S2++;
                        if (ShipCell == '3') S3++;
                        if (ShipCell == '4') S4++;
                    } else {

                        for (int i = Vertdir - 1; i < Vertdir - 1 + Character.getNumericValue(ShipCell); i++) {
                            if (board[i][Horizdir - 1] == '#') {
                                okay = false;
                            }
                        }
                        if (okay) {
                            for (int i = Vertdir - 1; i < Vertdir - 1 + Character.getNumericValue(ShipCell); i++) {
                                board[i][Horizdir - 1] = '#';
                            }
                        } else {
                            System.out.println("Whopps, the other ship is alredy placed there! Try again");
                            if (ShipCell == '2') S2++;
                            if (ShipCell == '3') S3++;
                            if (ShipCell == '4') S4++;
                            okay = true;
                        }
                    }
                }

                if (Dir == 'u') {
                    if ((Vertdir <= 3 && ShipCell == '4') || (Vertdir <= 2 && ShipCell == '3') || (Vertdir <= 1 && ShipCell == '2')) {
                        System.out.println("Not appropriate direction to set at this cell, try again");
                        if (ShipCell == '2') S2++;
                        if (ShipCell == '3') S3++;
                        if (ShipCell == '4') S4++;
                    } else {
                        for (int i = Vertdir - 1; i > Vertdir - 1 - Character.getNumericValue(ShipCell); i--) {
                            if (board[i][Horizdir - 1] == '#') okay = false;
                        }
                        if (okay) {
                            for (int i = Vertdir - 1; i > Vertdir - 1 - Character.getNumericValue(ShipCell); i--) {
                                board[i][Horizdir - 1] = '#';
                            }
                        } else {
                            System.out.println("Whopps, the other ship is alredy placed there! Try again");
                            if (ShipCell == '2') S2++;
                            if (ShipCell == '3') S3++;
                            if (ShipCell == '4') S4++;
                            okay = true;
                        }
                    }
                }
            }

            printBoard(player);
        }
        return board;
    }
}