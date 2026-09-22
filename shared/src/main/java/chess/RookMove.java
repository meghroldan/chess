package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RookMove implements PieceMovement{

    private Set<ChessMove> moves = new HashSet<>();;

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){

        ChessGame.TeamColor currColor = board.getPiece(position).getTeamColor();

        int col = position.getColumn();
        int row = position.getRow();

        int tempCol = position.getColumn();
        int tempRow = position.getRow();

        ChessPosition tempPosition;
        ChessMove tempMove;

        for(int i = row+1; i < 9; i++){
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

        return moves;
    }
}
