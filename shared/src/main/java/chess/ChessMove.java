package chess;

import java.util.Objects;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {

    private ChessPosition firstPosition;
    private ChessPosition lastPosition;
    private ChessPiece.PieceType promotion;
    private String stringVar;

    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        firstPosition = startPosition;
        lastPosition = endPosition;
        promotion = promotionPiece;
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return firstPosition;
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return lastPosition;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return promotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessMove chessMove=(ChessMove) o;
        return Objects.equals(firstPosition, chessMove.firstPosition) && Objects.equals(lastPosition, chessMove.lastPosition) && promotion == chessMove.promotion && Objects.equals(stringVar, chessMove.stringVar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPosition, lastPosition, promotion, stringVar);
    }

    @Override
    public String toString() {
        return "ChessMove{" +
                "firstPosition=" + firstPosition +
                ", lastPosition=" + lastPosition +
                ", promotion=" + promotion +
                ", stringVar='" + stringVar + '\'' +
                '}';
    }
}
