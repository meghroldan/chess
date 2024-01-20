package chess;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {

    private int row1;
    private int col1;

    public ChessPosition(int row, int col) {
        row1 = row;
        col1 = col;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return row1;
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return col1;
    }

    public void updatePosition(int row, int col){
        row1 = row;
        col1 = col;
    }
}
