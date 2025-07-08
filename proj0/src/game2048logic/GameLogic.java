package game2048logic;

import edu.princeton.cs.algs4.In;
import game2048rendering.Side;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return minR.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        int current_tile = board[r][c];
        if (current_tile == 0 || r == 0) {
            return 0;
        }
        if (r <= minR) {
            return minR;
        }
        for (int i = r - 1; i >= minR; i-- ) {
            if (board[i][c] == current_tile) {
                board[i][c] += current_tile;
                board[r][c] = 0;
                return i + 1;
            } else if (board[i][c] > 0) {
                if (i == r - 1) { // no moving
                    return minR;
                }
                board[i + 1][c] = current_tile;
                return i + 1;
            }
        }
        // all tile above the current tile is empty, move up to the top.
        board[minR][c] = current_tile;
        board[r][c] = 0;
        return minR;
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        Stack<Integer> stack = new Stack<>();
        for (int[] row : board) {
            if (row[c] > 0) {
                if (stack.empty() || stack.peek() != row[c]) {
                    stack.push(row[c]);
                } else if (stack.peek() == row[c]) {
                    int merge = stack.pop() + row[c];
                    stack.push(merge);
                }
            }
        }
        for (int i = board.length - 1; i >= stack.size(); i--) {
            board[i][c] = 0;
        }
        for (int i = stack.size() - 1; i >= 0; i--) {
            board[i][c] = stack.pop();
        }
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        // TODO: fill this in in task 6
        return;
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // TODO: fill this in in task 7
        if (side == Side.NORTH) {
            return;
        } else if (side == Side.EAST) {
            return;
        } else if (side == Side.SOUTH) {
            return;
        } else if (side == Side.WEST) {
            return;
        } else {
            System.out.println("Invalid side specified");
        }
    }
}
