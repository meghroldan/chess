package chess;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPosition that=(ChessPosition) o;
        return row1 == that.row1 && col1 == that.col1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row1, col1);
    }

    @Override
    public String toString() {
        return "ChessPosition{" +
                "row1=" + row1 +
                ", col1=" + col1 +
                '}';
    }
}
