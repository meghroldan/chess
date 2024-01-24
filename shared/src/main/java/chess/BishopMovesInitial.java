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
    for(int i = col + 1; i < 9; i++){
      tempRow--;
      if(tempRow > 0) {
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

    tempRow = row;
    tempCol = col;
    for(int i = col - 1; i > 0; i--){
      tempRow++;
      if(tempRow < 9) {
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

    tempRow = row;
    tempCol = col;
    for(int i = row - 1; i > 0; i--){
      tempCol--;
      if(tempCol > 0) {
        tempPos=new ChessPosition(i, tempCol);
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


    tempRow = row;
    tempCol = col;
    for(int i = row + 1; i < 9; i++){
      tempCol++;
      if(tempCol < 9) {
        tempPos=new ChessPosition(i, tempCol);
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
          tempMov = new ChessMove(myPosition, tempPos, null);
          validMoves2.add(tempMov);
        }
      }
    }

    return validMoves2;
  }
}
