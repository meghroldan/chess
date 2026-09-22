package chess;

import java.util.Objects;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {

    private int rowFill;
    private int colFill;

    public ChessPosition(int row, int col) {
        rowFill = row;
        colFill = col;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return rowFill;
    }

    /**
     * @return which column this position is in
     * 1 codes for the left column
     */
    public int getColumn() {
        return colFill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPosition that=(ChessPosition) o;
        return rowFill == that.rowFill && colFill == that.colFill;
    }

    @Override
    public int hashCode() {

        return Objects.hash(rowFill, colFill);
    }

    @Override
    public String toString() {
        return "ChessPosition{" +
                "row1=" + rowFill +
                ", col1=" + colFill+
                '}';
    }
}
