package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KingMovesInitial implements PieceMovements{

  //private Map<ChessPosition, ChessPiece> pieces;
  private Set<ChessMove> validMoves2 = new HashSet<>();
  public KingMovesInitial(){
  }

  @Override
  public Collection<ChessMove> pieceMovesAvailable(ChessBoard board, ChessPosition myPosition) {

    //pieces = board.getAllPieces();
    ChessGame.TeamColor currColor = board.getPiece(myPosition).getTeamColor();

    int col = myPosition.getColumn();
    int row = myPosition.getRow();

    int tempCol = myPosition.getColumn();
    int tempRow = myPosition.getRow();

    ChessPosition tempPosition;
    ChessMove tempMove;

    tempPosition = new ChessPosition(row + 1, col);

    tempMove = new ChessMove(myPosition, tempPosition, null);
    if((row + 1 < 9) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
            || ((row + 1 < 9) && board.getPiece(tempPosition) == null)){
      validMoves2.add(tempMove);
    }

    tempPosition = new ChessPosition(row - 1, col);
    tempMove = new ChessMove(myPosition, tempPosition, null);
    if((row - 1 > 0) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
            || ((row - 1 > 0) && board.getPiece(tempPosition) == null)){
      validMoves2.add(tempMove);
    }

    tempPosition = new ChessPosition(row, col - 1);
    tempMove = new ChessMove(myPosition, tempPosition, null);
    if((col- 1 > 0) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
            || ((col- 1 > 0) && board.getPiece(tempPosition) == null)){
      validMoves2.add(tempMove);
    }

    tempPosition = new ChessPosition(row, col + 1);
    tempMove = new ChessMove(myPosition, tempPosition, null);
    if((col + 1 < 9) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
            || ((col + 1 < 9) && board.getPiece(tempPosition) == null)){
      validMoves2.add(tempMove);
    }


    tempPosition = new ChessPosition(row - 1, col - 1);
    tempMove = new ChessMove(myPosition, tempPosition, null);
    if((row - 1 > 0) && (col- 1 > 0) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
            || ((row - 1 > 0) && (col- 1 > 0) && board.getPiece(tempPosition) == null)){
      validMoves2.add(tempMove);
    }

    tempPosition = new ChessPosition(row - 1, col + 1);
    tempMove = new ChessMove(myPosition, tempPosition, null);
    if((row - 1 > 0) && (col + 1 < 9) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
            || ((row - 1 > 0) && (col + 1 < 9) && board.getPiece(tempPosition) == null)){
      validMoves2.add(tempMove);
    }

    tempPosition = new ChessPosition(row + 1, col - 1);
    tempMove = new ChessMove(myPosition, tempPosition, null);
    if((col - 1 > 0) && (row + 1 < 9) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
            || ((col - 1 > 0) && (row + 1 < 9) && board.getPiece(tempPosition) == null)){
      validMoves2.add(tempMove);
    }

    tempPosition = new ChessPosition(row + 1, col + 1);
    tempMove = new ChessMove(myPosition, tempPosition, null);
    if((row + 1 < 9) && (col + 1 < 9) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
            || ((row + 1 < 9) && (col + 1 < 9) && board.getPiece(tempPosition) == null)){
      validMoves2.add(tempMove);
    }


    return validMoves2;
  }
}
