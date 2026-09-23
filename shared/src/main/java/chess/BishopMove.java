package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BishopMove implements PieceMovement{

    private Set<ChessMove> moves = new HashSet<>();;

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        ChessGame.TeamColor currColor = board.getPiece(position).getTeamColor();
        int col = position.getColumn();
        int row = position.getRow();

        ChessPosition tempPos;
        ChessMove tempMov;

        int tempRow = row;
        int tempCol = col;
        for(int i = col + 1; i < 9; i++){
            tempRow--;
            if(tempRow > 0) {
                tempPos=new ChessPosition(tempRow, i);
                if (board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() != currColor) {
                    tempMov=new ChessMove(position, tempPos, null);
                    moves.add(tempMov);
                    break;
                }
                if(board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() == currColor) {
                        break;
                    }
                }
                if (board.getPiece(tempPos) == null) {
                    tempMov=new ChessMove(position, tempPos, null);
                    moves.add(tempMov);
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
                    tempMov=new ChessMove(position, tempPos, null);
                    moves.add(tempMov);
                    break;
                }
                if(board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() == currColor) {
                        break;
                    }
                }
                if (board.getPiece(tempPos) == null) {
                    tempMov=new ChessMove(position, tempPos, null);
                    moves.add(tempMov);
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
                    tempMov=new ChessMove(position, tempPos, null);
                    moves.add(tempMov);
                    break;
                }
                if(board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() == currColor) {
                        break;
                    }
                }
                if (board.getPiece(tempPos) == null) {
                    tempMov=new ChessMove(position, tempPos, null);
                    moves.add(tempMov);
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
                    tempMov=new ChessMove(position, tempPos, null);
                    moves.add(tempMov);
                    break;
                }
                if(board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() == currColor) {
                        break;
                    }
                }
                if (board.getPiece(tempPos) == null) {
                    tempMov = new ChessMove(position, tempPos, null);
                    moves.add(tempMov);
                }
            }
        }
        if(moves == null){
            //System.out.println("No moves available");
            return null;
        }

        return moves;
    }
}
