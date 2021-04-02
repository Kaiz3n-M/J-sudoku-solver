class SudokuSolver {

    public static void main(String[] args) {
        int board[][] = {   {4, 0, 1, 2, 9, 0, 0, 7, 5 },
                            {2, 0, 0, 3, 0, 0, 8, 0, 0 },
                            {0, 7, 0, 0, 8, 0, 0, 0, 6 },
                            {0, 0, 0, 1, 0, 3, 0, 6, 2 },
                            {1, 0, 5, 0, 0, 0, 4, 0, 3 },
                            {7, 3, 0, 6, 0, 8, 0, 0, 0 },
                            {6, 0, 0, 0, 2, 0, 0, 3, 0 },
                            {0, 0, 7, 0, 0, 1, 0, 0, 4 },
                            {8, 9, 0, 0, 6, 5, 1, 0, 7 }
                        };   
            printBoard(board);  
            solve(board);
            System.out.println(".................................");
            printBoard(board);                   
    }
    

    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (i != 0 && i % 3 == 0) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < board[0].length; j++) {
                if (j != 0 && j % 3 == 0) {
                    System.out.print("| ");
                }
                if (j == 8) {
                    System.out.print(board[i][j]);
                } else {
                    System.out.print(board[i][j] + " ");
                }

            }
            System.out.println();
        }
    }

    public static int[] findEmpty(int[][] board) {
        int[] position = new int[2];
        for (int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        return null;
    }

    public static boolean isValid(int[][] board, int number,  int[] position) {
        for (int i = 0; i < board.length; i++) {
            if (board[position[0]][i] == number && position[1] != i) {
                return false;
            }
        }
        for (int j = 0; j < board.length; j++) {
            if (board[j][position[1]] == number && position[1] != j) {
                return false;
            }
        }
        int boxX = position[1] / 3;
        int boxY = position[0] / 3;

        for (int i = boxY * 3; i < boxY*3 + 3; i++) {
            for (int j = boxX*3; j < boxX*3 + 3; j++) {
                if (board[i][j] == number && i != position[0] && j != position[1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean solve(int[][] board){
        int row;
        int column;
        int[] find = findEmpty(board);
        if (find != null) {
            row = find[0];
            column = find[1];  
        } 
        else {
            return true;
        }

        for (int i = 1; i < 10; i++){
            if (isValid(board, i, find)) {
                board[row] [column] = i;
                if (solve(board)) {
                    return true;
                } else {
                    board[row][column] = 0;
                }
            }
        }
        return false;
    }

}