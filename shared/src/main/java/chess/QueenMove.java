package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class QueenMove implements PieceMovement{
    private Set<ChessMove> moves = new HashSet<>();;

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){

        ChessGame.TeamColor currColor = board.getPiece(position).getTeamColor();
        int col = position.getColumn();
        int row = position.getRow();

        ChessPosition tempPosistion;
        ChessMove tempMove;

        int tempRow = row;
        int tempCol = col;
        for(int i = col + 1; i < 9; i++){
            tempRow--;
            if(tempRow > 0) {
                tempPosistion=new ChessPosition(tempRow, i);
                if (board.getPiece(tempPosistion) != null && board.getPiece(tempPosistion).getTeamColor() != currColor) {
                    tempMove=new ChessMove(position, tempPosistion, null);
                    moves.add(tempMove);
                    break;
                }
                if(board.getPiece(tempPosistion) != null) {
                    if (board.getPiece(tempPosistion).getTeamColor() == currColor) {
                        break;
                    }
                }
                if (board.getPiece(tempPosistion) == null) {
                    tempMove=new ChessMove(position, tempPosistion, null);
                    moves.add(tempMove);
                }
            }

        }

        tempRow = row;
        for(int i = col - 1; i > 0; i--){
            tempRow++;
            if(tempRow < 9) {
                tempPosistion=new ChessPosition(tempRow, i);
                if (board.getPiece(tempPosistion) != null && board.getPiece(tempPosistion).getTeamColor() != currColor) {
                    tempMove=new ChessMove(position, tempPosistion, null);
                    moves.add(tempMove);
                    break;
                }
                if(board.getPiece(tempPosistion) != null) {
                    if (board.getPiece(tempPosistion).getTeamColor() == currColor) {
                        break;
                    }
                }
                if (board.getPiece(tempPosistion) == null) {
                    tempMove=new ChessMove(position, tempPosistion, null);
                    moves.add(tempMove);
                }
            }
        }


        tempCol = col;
        for(int i = row - 1; i > 0; i--){
            tempCol--;
            if(tempCol > 0) {
                tempPosistion=new ChessPosition(i, tempCol);
                if (board.getPiece(tempPosistion) != null && board.getPiece(tempPosistion).getTeamColor() != currColor) {
                    tempMove=new ChessMove(position, tempPosistion, null);
                    moves.add(tempMove);
                    break;
                }
                if(board.getPiece(tempPosistion) != null) {
                    if (board.getPiece(tempPosistion).getTeamColor() == currColor) {
                        break;
                    }
                }
                if (board.getPiece(tempPosistion) == null) {
                    tempMove=new ChessMove(position, tempPosistion, null);
                    moves.add(tempMove);
                }
            }
        }



        tempCol = col;
        for(int i = row + 1; i < 9; i++){
            tempCol++;
            if(tempCol < 9) {
                tempPosistion=new ChessPosition(i, tempCol);
                if (board.getPiece(tempPosistion) != null && board.getPiece(tempPosistion).getTeamColor() != currColor) {
                    tempMove=new ChessMove(position, tempPosistion, null);
                    moves.add(tempMove);
                    break;
                }
                if(board.getPiece(tempPosistion) != null) {
                    if (board.getPiece(tempPosistion).getTeamColor() == currColor) {
                        break;
                    }
                }
                if (board.getPiece(tempPosistion) == null) {
                    tempMove=new ChessMove(position, tempPosistion, null);
                    moves.add(tempMove);
                }
            }
        }

        ChessPosition tempPosition;
        for(int i = row + 1; i < 9; i++){
            tempPosition = new ChessPosition(i, col);
            if(board.getPiece(tempPosition) != null  && board.getPiece(tempPosition).getTeamColor() != currColor){
                tempMove = new ChessMove(position, tempPosition, null);
                moves.add(tempMove);
                break;
            }
            if(board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() == currColor){
                break;
            }
            if(board.getPiece(tempPosition) == null){
                tempMove = new ChessMove(position, tempPosition, null);
                moves.add(tempMove);
            }
        }

        for(int i = row-1; i > 0; i--){
            tempPosition = new ChessPosition(i, col);
            if(board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor){
                tempMove = new ChessMove(position, tempPosition, null);
                moves.add(tempMove);
                break;
            }
            if(board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() == currColor){
                break;
            }
            if(board.getPiece(tempPosition) == null){
                tempMove = new ChessMove(position, tempPosition, null);
                moves.add(tempMove);
            }
        }

        for(int i = col-1; i > 0; i--){
            tempPosition = new ChessPosition(row, i);
            if(board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor){
                tempMove = new ChessMove(position, tempPosition, null);
                moves.add(tempMove);
                break;
            }
            if(board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() == currColor){
                break;
            }
            if(board.getPiece(tempPosition) == null){
                tempMove = new ChessMove(position, tempPosition, null);
                moves.add(tempMove);
            }

        }

        for(int i = col+1; i < 9; i++){
            tempPosition = new ChessPosition(row, i);
            if(board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor){
                tempMove = new ChessMove(position, tempPosition, null);
                moves.add(tempMove);
                break;
            }
            if(board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() == currColor){
                break;
            }
            if(board.getPiece(tempPosition) == null){
                tempMove = new ChessMove(position, tempPosition, null);
                moves.add(tempMove);
            }

        }

        if(moves == null){
            //System.out.println("No moves available");
            return null;
        }
        return moves;
    }
}
