package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PawnMove implements PieceMovement{

    private Set<ChessMove> moves = new HashSet<>();

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        //pieces = board.getAllPieces();
        ChessGame.TeamColor currColor = board.getPiece(position).getTeamColor();

        int col = position.getColumn();
        int row = position.getRow();

        ChessPosition tempPosition;
        ChessMove tempMove;

        if(currColor == ChessGame.TeamColor.WHITE) {
            if (row == 2) {
                tempPosition=new ChessPosition(row + 1, col);
                if (board.getPiece(tempPosition) == null) {
                    tempPosition=new ChessPosition(row + 2, col);
                    if (board.getPiece(tempPosition) == null) {
                        tempMove=new ChessMove(position, tempPosition, null);
                        moves.add(tempMove);
                    }
                }
            }
            if (row + 1 <= 7) {
                tempPosition=new ChessPosition(row + 1, col);
                if (board.getPiece(tempPosition) == null) {
                    tempMove=new ChessMove(position, tempPosition, null);
                    moves.add(tempMove);
                }
            }
            if (row + 1 != 8) {
                tempPosition=new ChessPosition(row + 1, col + 1);
                if (row + 1 <= 7 && col + 1 <= 7 && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
                    tempMove=new ChessMove(position, tempPosition, null);
                    moves.add(tempMove);
                }
                tempPosition=new ChessPosition(row + 1, col - 1);
                if (row + 1 <= 7 && col - 1 >= 1 && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
                    tempMove=new ChessMove(position, tempPosition, null);
                    moves.add(tempMove);
                }
            }


            //promotion white
            if (row + 1 == 8) {
                tempPosition=new ChessPosition(row + 1, col);
                if (board.getPiece(tempPosition) == null) {
                    tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.KNIGHT);
                    moves.add(tempMove);
                    tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.QUEEN);
                    moves.add(tempMove);
                    tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.BISHOP);
                    moves.add(tempMove);
                    tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.ROOK);
                    moves.add(tempMove);
                }
                if(col + 1 < 9){
                    tempPosition=new ChessPosition(row + 1, col + 1);
                    if (board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.KNIGHT);
                        moves.add(tempMove);
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.QUEEN);
                        moves.add(tempMove);
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.BISHOP);
                        moves.add(tempMove);
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.ROOK);
                        moves.add(tempMove);
                    }
                }
                if(col - 1 > 0){
                    tempPosition=new ChessPosition(row + 1, col - 1);
                    if (board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.KNIGHT);
                        moves.add(tempMove);
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.QUEEN);
                        moves.add(tempMove);
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.BISHOP);
                        moves.add(tempMove);
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.ROOK);
                        moves.add(tempMove);
                    }
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
                        tempMove=new ChessMove(position, tempPosition, null);
                        moves.add(tempMove);
                    }
                }
            }
            if (row - 1 > 1) {
                tempPosition=new ChessPosition(row - 1, col);
                if (board.getPiece(tempPosition) == null) {
                    tempMove=new ChessMove(position, tempPosition, null);
                    moves.add(tempMove);
                }
            }
            if (row - 1 != 1 && col - 1 >= 1) {
                tempPosition=new ChessPosition(row - 1, col + 1);
                if (row - 1 >= 1 && col + 1 <= 7 && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
                    tempMove=new ChessMove(position, tempPosition, null);
                    moves.add(tempMove);
                }
                tempPosition=new ChessPosition(row - 1, col - 1);
                if (row - 1 >= 1 && col - 1 >= 1 && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
                    tempMove=new ChessMove(position, tempPosition, null);
                    moves.add(tempMove);
                }
            }


            //promotion black
            if (row - 1 == 1) {
                tempPosition=new ChessPosition(row - 1, col);
                if (board.getPiece(tempPosition) == null) {
                    tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.KNIGHT);
                    moves.add(tempMove);
                    tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.QUEEN);
                    moves.add(tempMove);
                    tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.BISHOP);
                    moves.add(tempMove);
                    tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.ROOK);
                    moves.add(tempMove);
                }
                if(col + 1 < 9){
                    tempPosition=new ChessPosition(row - 1, col + 1);
                    if (board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.KNIGHT);
                        moves.add(tempMove);
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.QUEEN);
                        moves.add(tempMove);
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.BISHOP);
                        moves.add(tempMove);
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.ROOK);
                        moves.add(tempMove);
                    }
                }

                if(col - 1 > 0){
                    tempPosition=new ChessPosition(row - 1, col - 1);

                    if (board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor) {
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.KNIGHT);
                        moves.add(tempMove);
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.QUEEN);
                        moves.add(tempMove);
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.BISHOP);
                        moves.add(tempMove);
                        tempMove=new ChessMove(position, tempPosition, ChessPiece.PieceType.ROOK);
                        moves.add(tempMove);
                    }
                }

            }
        }

        /*


        if(moves == null){
            //System.out.println("No moves available");
            return null;
        }

         */


        return moves;
    }
}
