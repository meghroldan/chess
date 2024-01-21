package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class QueenMovesInitial implements PieceMovements{

  private Map<ChessPosition, ChessPiece> pieces;
  private Set<ChessMove> validMoves2 = new HashSet<>();
  public QueenMovesInitial(){
  }

  @Override
  public Collection<ChessMove> pieceMovesAvailable(ChessBoard board, ChessPosition myPosition) {
    pieces = board.getAllPieces();
    ChessGame.TeamColor currColor = board.getPiece(myPosition).getTeamColor();
    int col = myPosition.getColumn();
    int row = myPosition.getRow();

    ChessPosition tempPosistion;
    ChessMove tempMove;
    //debug this loop
    int tempRow = row;
    int tempCol = col;
    for(int i = col + 1; i < 9; i++){
      tempRow--;
      if(tempRow > 0) {
        tempPosistion=new ChessPosition(tempRow, i);
        if (board.getPiece(tempPosistion) != null && board.getPiece(tempPosistion).getTeamColor() != currColor) {
          tempMove=new ChessMove(myPosition, tempPosistion, null);
          validMoves2.add(tempMove);
          break;
        }
        if(board.getPiece(tempPosistion) != null) {
          if (board.getPiece(tempPosistion).getTeamColor() == currColor) {
            break;
          }
        }
        if (board.getPiece(tempPosistion) == null) {
          tempMove=new ChessMove(myPosition, tempPosistion, null);
          validMoves2.add(tempMove);
        }
      }

    }

    tempRow = row;
    tempCol = col;
    for(int i = col - 1; i > 0; i--){
      tempRow++;
      if(tempRow < 9) {
        tempPosistion=new ChessPosition(tempRow, i);
        if (board.getPiece(tempPosistion) != null && board.getPiece(tempPosistion).getTeamColor() != currColor) {
          tempMove=new ChessMove(myPosition, tempPosistion, null);
          validMoves2.add(tempMove);
          break;
        }
        if(board.getPiece(tempPosistion) != null) {
          if (board.getPiece(tempPosistion).getTeamColor() == currColor) {
            break;
          }
        }
        if (board.getPiece(tempPosistion) == null) {
          tempMove=new ChessMove(myPosition, tempPosistion, null);
          validMoves2.add(tempMove);
        }
      }
    }

    tempRow = row;
    tempCol = col;
    for(int i = row - 1; i > 0; i--){
      tempCol--;
      if(tempCol > 0) {
        tempPosistion=new ChessPosition(i, tempCol);
        if (board.getPiece(tempPosistion) != null && board.getPiece(tempPosistion).getTeamColor() != currColor) {
          tempMove=new ChessMove(myPosition, tempPosistion, null);
          validMoves2.add(tempMove);
          break;
        }
        if(board.getPiece(tempPosistion) != null) {
          if (board.getPiece(tempPosistion).getTeamColor() == currColor) {
            break;
          }
        }
        if (board.getPiece(tempPosistion) == null) {
          tempMove=new ChessMove(myPosition, tempPosistion, null);
          validMoves2.add(tempMove);
        }
      }
    }


    tempRow = row;
    tempCol = col;
    for(int i = row + 1; i < 9; i++){
      tempCol++;
      if(tempCol < 9) {
        tempPosistion=new ChessPosition(i, tempCol);
        if (board.getPiece(tempPosistion) != null && board.getPiece(tempPosistion).getTeamColor() != currColor) {
          tempMove=new ChessMove(myPosition, tempPosistion, null);
          validMoves2.add(tempMove);
          break;
        }
        if(board.getPiece(tempPosistion) != null) {
          if (board.getPiece(tempPosistion).getTeamColor() == currColor) {
            break;
          }
        }
        if (board.getPiece(tempPosistion) == null) {
          tempMove=new ChessMove(myPosition, tempPosistion, null);
          validMoves2.add(tempMove);
        }
      }
    }

    ChessPosition tempPosition;
    for(int i=tempRow+1; i < 9; i++){
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




    return validMoves2;
  }
}
