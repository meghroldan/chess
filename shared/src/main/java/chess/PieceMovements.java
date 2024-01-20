package chess;

import java.util.Collection;

public interface PieceMovements {

  Collection<ChessMove> pieceMovesAvailable(ChessBoard board, ChessPosition myPosition);

}
