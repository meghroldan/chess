package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KnightMovesInitial implements PieceMovements{

  private Map<ChessPosition, ChessPiece> pieces;
  private Set<ChessMove> validMoves2 = new HashSet<>();
  public KnightMovesInitial(){
  }

  @Override
  public Collection<ChessMove> pieceMovesAvailable(ChessBoard board, ChessPosition myPosition) {

    pieces = board.getAllPieces();
    ChessGame.TeamColor currColor = board.getPiece(myPosition).getTeamColor();

    int col = myPosition.getColumn();
    int row = myPosition.getRow();

    int tempCol = myPosition.getColumn();
    int tempRow = myPosition.getRow();

    ChessPosition tempPosition;
    ChessMove tempMove;

    if(validMoves2 == null){
      return null;
    }
    return validMoves2;
  }
}
