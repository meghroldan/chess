package chess;

import java.util.Collection;

public interface PieceMovement {

    Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position);
}