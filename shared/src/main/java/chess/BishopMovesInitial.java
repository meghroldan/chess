package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BishopMovesInitial implements PieceMovements{

  private Map<ChessPosition, ChessPiece> pieces;
  private Set<ChessMove> validMoves2 = new HashSet<>();
  public BishopMovesInitial(){
  }

  @Override
  public Collection<ChessMove> pieceMovesAvailable(ChessBoard board, ChessPosition myPosition) {
    pieces = board.getAllPieces();
    ChessGame.TeamColor currColor = board.getPiece(myPosition).getTeamColor();
    int col = myPosition.getColumn();
    int row = myPosition.getRow();

    ChessPosition tempPos;
    ChessMove tempMov;
    //debug this loop
    int tempRow = row;
    int tempCol = col;
    for(int i = col + 1; i <= 8; i++){
      tempRow--;
      if(tempRow >= 1) {
        tempPos=new ChessPosition(tempRow, i);
        if (board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() != currColor) {
          tempMov=new ChessMove(myPosition, tempPos, null);
          validMoves2.add(tempMov);
          break;
        }
        if(board.getPiece(tempPos) != null) {
          if (board.getPiece(tempPos).getTeamColor() == currColor) {
            break;
          }
        }
        if (board.getPiece(tempPos) == null) {
          tempMov=new ChessMove(myPosition, tempPos, null);
          validMoves2.add(tempMov);
        }
      }

    }

    if(validMoves2 == null){
      return null;
    }
    return validMoves2;
  }
}
