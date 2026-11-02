package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BishopMove implements PieceMovement{

    private final Set<ChessMove> myMoves = new HashSet<>();
    private int row;
    private int col;
    private ChessPosition tempPos;
    private ChessMove tempMov;
    private ChessGame.TeamColor color;

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        color = board.getPiece(myPosition).getTeamColor();

        row = myPosition.getRow();
        col = myPosition.getColumn();

        for(int i = col + 1; i < 9; i ++){
            row++;
            if(row < 9){
                tempPos = new ChessPosition(row, i);
                if(board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() != color){
                    tempMov = new ChessMove(myPosition, tempPos, null);
                    myMoves.add(tempMov);
                    break;
                }
                if(board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() == color){
                    break;
                }
                if(board.getPiece(tempPos) == null){
                    tempMov = new ChessMove(myPosition, tempPos, null);
                    myMoves.add(tempMov);
                }
            }
        }

        row = myPosition.getRow();
        col = myPosition.getColumn();

        for(int i = col - 1; i > 0; i --){
            row++;
            if(row < 9){
                tempPos = new ChessPosition(row, i);
                if(board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() != color){
                    tempMov = new ChessMove(myPosition, tempPos, null);
                    myMoves.add(tempMov);
                    break;
                }
                if(board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() == color){
                    break;
                }
                if(board.getPiece(tempPos) == null){
                    tempMov = new ChessMove(myPosition, tempPos, null);
                    myMoves.add(tempMov);
                }
            }
        }

        row = myPosition.getRow();
        col = myPosition.getColumn();

        for(int i = col - 1; i > 0; i --){
            row--;
            if(row > 0){
                tempPos = new ChessPosition(row, i);
                if(board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() != color){
                    tempMov = new ChessMove(myPosition, tempPos, null);
                    myMoves.add(tempMov);
                    break;
                }
                if(board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() == color){
                    break;
                }
                if(board.getPiece(tempPos) == null){
                    tempMov = new ChessMove(myPosition, tempPos, null);
                    myMoves.add(tempMov);
                }
            }
        }

        row = myPosition.getRow();
        col = myPosition.getColumn();

        for(int i = col + 1; i < 9; i ++){
            row--;
            if(row > 0){
                tempPos = new ChessPosition(row, i);
                if(board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() != color){
                    tempMov = new ChessMove(myPosition, tempPos, null);
                    myMoves.add(tempMov);
                    break;
                }
                if(board.getPiece(tempPos) != null && board.getPiece(tempPos).getTeamColor() == color){
                    break;
                }
                if(board.getPiece(tempPos) == null){
                    tempMov = new ChessMove(myPosition, tempPos, null);
                    myMoves.add(tempMov);
                }
            }
        }

        return myMoves;
    }
}
