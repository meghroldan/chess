package chess;

import java.util.Objects;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {

    ChessPosition startP;
    ChessPosition endP;
    ChessPiece.PieceType promo = null;


    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {

        startP = startPosition;
        endP = endPosition;
        promo = promotionPiece;


    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return startP;
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return endP;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return promo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessMove chessMove=(ChessMove) o;
        return Objects.equals(startP, chessMove.startP) && Objects.equals(endP, chessMove.endP) && promo == chessMove.promo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startP, endP, promo);
    }

    @Override
    public String toString() {
        return "ChessMove{" +
                "firstPosition=" + startP +
                ", lastPosition=" + endP +
                ", promotion=" + promo +
                '}';
    }
}
