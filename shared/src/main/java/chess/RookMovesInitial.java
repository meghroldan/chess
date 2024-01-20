package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RookMovesInitial implements PieceMovements{

  private Map<ChessPosition, ChessPiece> pieces;
  private Set<ChessMove> validMoves2 = new HashSet<>();
  public RookMovesInitial(){
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

    for(int i = tempRow+1; i < 9; i++){
      tempPosition = new ChessPosition(i, col);
      if(pieces.containsKey(tempPosition) && pieces.get(tempPosition).getTeamColor() != currColor){
        tempMove = new ChessMove(myPosition, tempPosition, null);
        validMoves2.add(tempMove);
        break;
      }
      if(pieces.containsKey(tempPosition) && pieces.get(tempPosition).getTeamColor() == currColor){
        break;
      }
      if(board.getPiece(tempPosition) == null){
        tempMove = new ChessMove(myPosition, tempPosition, null);
        validMoves2.add(tempMove);
      }
    }

    for(int i = tempRow-1; i > 0; i--){
      tempPosition = new ChessPosition(i, col);
      if(pieces.containsKey(tempPosition) && pieces.get(tempPosition).getTeamColor() != currColor){
        tempMove = new ChessMove(myPosition, tempPosition, null);
        validMoves2.add(tempMove);
        break;
      }
      if(pieces.containsKey(tempPosition) && pieces.get(tempPosition).getTeamColor() == currColor){
        break;
      }
      if(board.getPiece(tempPosition) == null){
        tempMove = new ChessMove(myPosition, tempPosition, null);
        validMoves2.add(tempMove);
      }
    }

    for(int i = tempCol-1; i > 0; i--){
      tempPosition = new ChessPosition(row, i);
      if(pieces.containsKey(tempPosition) && pieces.get(tempPosition).getTeamColor() != currColor){
        tempMove = new ChessMove(myPosition, tempPosition, null);
        validMoves2.add(tempMove);
        break;
      }
      if(pieces.containsKey(tempPosition) && pieces.get(tempPosition).getTeamColor() == currColor){
        break;
      }
      if(board.getPiece(tempPosition) == null){
        tempMove = new ChessMove(myPosition, tempPosition, null);
        validMoves2.add(tempMove);
      }

    }

    for(int i = tempCol+1; i < 9; i++){
      tempPosition = new ChessPosition(row, i);
      if(pieces.containsKey(tempPosition) && pieces.get(tempPosition).getTeamColor() != currColor){
        tempMove = new ChessMove(myPosition, tempPosition, null);
        validMoves2.add(tempMove);
        break;
      }
      if(pieces.containsKey(tempPosition) && pieces.get(tempPosition).getTeamColor() == currColor){
        break;
      }
      if(board.getPiece(tempPosition) == null){
        tempMove = new ChessMove(myPosition, tempPosition, null);
        validMoves2.add(tempMove);
      }

    }

    if(validMoves2 == null){
      return null;
    }

    return validMoves2;
  }
}
