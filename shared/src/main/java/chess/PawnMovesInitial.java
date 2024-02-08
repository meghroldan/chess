package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PawnMovesInitial implements PieceMovements{

  //private Map<ChessPosition, ChessPiece> pieces;
  private Set<ChessMove> validMoves2 = new HashSet<>();
  public PawnMovesInitial(){
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

    if(currColor == ChessGame.TeamColor.WHITE) {
      if (row == 2) {
        tempPosition=new ChessPosition(row + 1, col);
        if (board.getPiece(tempPosition) == null) {
          tempPosition=new ChessPosition(row + 2, col);
          if (board.getPiece(tempPosition) == null) {
            tempMove=new ChessMove(myPosition, tempPosition, null);
            validMoves2.add(tempMove);
          }
        }
      }
      if (row + 1 <= 7) {
        tempPosition=new ChessPosition(row + 1, col);
        if (board.getPiece(tempPosition) == null) {
          tempMove=new ChessMove(myPosition, tempPosition, null);
          validMoves2.add(tempMove);
        }
      }
      if (row + 1 != 8) {
        tempPosition=new ChessPosition(row + 1, col + 1);
        if (row + 1 <= 7 && col + 1 <= 7 && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
          tempMove=new ChessMove(myPosition, tempPosition, null);
          validMoves2.add(tempMove);
        }
        tempPosition=new ChessPosition(row + 1, col - 1);
        if (row + 1 <= 7 && col - 1 >= 1 && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
          tempMove=new ChessMove(myPosition, tempPosition, null);
          validMoves2.add(tempMove);
        }
      }


      //promotion white
      if (row + 1 == 8) {
        tempPosition=new ChessPosition(row + 1, col);
        if (board.getPiece(tempPosition) == null) {
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.KNIGHT);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.QUEEN);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.BISHOP);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.ROOK);
          validMoves2.add(tempMove);
        }
        tempPosition=new ChessPosition(row + 1, col + 1);
        if (board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.KNIGHT);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.QUEEN);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.BISHOP);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.ROOK);
          validMoves2.add(tempMove);
        }
        tempPosition=new ChessPosition(row + 1, col - 1);
        if (board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.KNIGHT);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.QUEEN);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.BISHOP);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.ROOK);
          validMoves2.add(tempMove);
        }

      }
    }

    //black pawns
    if(currColor == ChessGame.TeamColor.BLACK) {
      if (row == 7) {
        tempPosition=new ChessPosition(row - 1, col);
        if (board.getPiece(tempPosition) == null) {
          tempPosition=new ChessPosition(row - 2, col);
          if (board.getPiece(tempPosition) == null) {
            tempMove=new ChessMove(myPosition, tempPosition, null);
            validMoves2.add(tempMove);
          }
        }
      }
      if (row - 1 > 1) {
        tempPosition=new ChessPosition(row - 1, col);
        if (board.getPiece(tempPosition) == null) {
          tempMove=new ChessMove(myPosition, tempPosition, null);
          validMoves2.add(tempMove);
        }
      }
      if (row - 1 != 1) {
        tempPosition=new ChessPosition(row - 1, col + 1);
        if (row - 1 >= 1 && col + 1 <= 7 && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
          tempMove=new ChessMove(myPosition, tempPosition, null);
          validMoves2.add(tempMove);
        }
        tempPosition=new ChessPosition(row - 1, col - 1);
        if (row - 1 >= 1 && col - 1 >= 1 && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
          tempMove=new ChessMove(myPosition, tempPosition, null);
          validMoves2.add(tempMove);
        }
      }


      //promotion black
      if (row - 1 == 1) {
        tempPosition=new ChessPosition(row - 1, col);
        if (board.getPiece(tempPosition) == null) {
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.KNIGHT);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.QUEEN);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.BISHOP);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.ROOK);
          validMoves2.add(tempMove);
        }
        tempPosition=new ChessPosition(row - 1, col + 1);
        if (board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.KNIGHT);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.QUEEN);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.BISHOP);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.ROOK);
          validMoves2.add(tempMove);
        }
        tempPosition=new ChessPosition(row - 1, col - 1);
        if (board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.KNIGHT);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.QUEEN);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.BISHOP);
          validMoves2.add(tempMove);
          tempMove=new ChessMove(myPosition, tempPosition, ChessPiece.PieceType.ROOK);
          validMoves2.add(tempMove);
        }
      }
    }




    return validMoves2;
  }
}
