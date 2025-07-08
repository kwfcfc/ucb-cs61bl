package game2048logic;

import game2048rendering.Side;

import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author Josh Hug
 */
public class GameLogic {
    /**
     * Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c     - the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return if there is a merge, returns the 1 + the row number where the merge
     *         occurred.
     *         if no merge occurs, then return minR.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        int currentTile = board[r][c];
        if (currentTile == 0 || r == 0) {
            return minR;
        }
        if (r <= minR) {
            return minR;
        }
        for (int i = r - 1; i >= minR; i--) {
            if (board[i][c] == currentTile) {
                board[i][c] += currentTile;
                board[r][c] = 0;
                return i + 1;
            } else if (board[i][c] > 0) {
                if (i != r - 1) { // no merge occurs, and has moving
                    board[i + 1][c] = currentTile;
                    board[r][c] = 0;
                }
                return minR;
            }
        }
        // all tile above the current tile is empty, move up to the top.
        board[minR][c] = currentTile;
        board[r][c] = 0;
        return minR;
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board the current state of the board
     * @param c     the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        int minR = 0;
        for (int i = 1; i < board.length; i++) {
            minR = moveTileUpAsFarAsPossible(board, i, c, minR);
        }
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        for (int c = 0; c < board[0].length; c++) {
            tiltColumn(board, c);
        }
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        if (side == Side.NORTH) {
            tiltUp(board);
        } else if (side == Side.EAST) {
            rotateLeft(board);
            tiltUp(board);
            rotateRight(board);
        } else if (side == Side.SOUTH) {
            rotateLeft(board);
            rotateLeft(board);
            tiltUp(board);
            rotateLeft(board);
            rotateLeft(board);
        } else if (side == Side.WEST) {
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
        } else {
            System.out.println("Invalid side specified");
        }
    }
}
