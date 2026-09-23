package chess;

import org.junit.jupiter.api.Order;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class KnightMove implements PieceMovement{

    private Set<ChessMove> moves = new HashSet<>();;

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        ChessGame.TeamColor currColor = board.getPiece(position).getTeamColor();

        int col = position.getColumn();
        int row = position.getRow();

        ChessPosition tempPosition;
        ChessMove tempMove;


        tempPosition = new ChessPosition(row - 2, col + 1);
        tempMove = new ChessMove(position, tempPosition, null);
        if((tempPosition.getRow() > 0) && (tempPosition.getColumn() < 9) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
                || ((tempPosition.getRow() > 0) && (tempPosition.getColumn() < 9) && board.getPiece(tempPosition) == null)){
            moves.add(tempMove);
        }

        tempPosition = new ChessPosition(row - 2, col - 1);
        tempMove = new ChessMove(position, tempPosition, null);
        if((tempPosition.getRow() > 0) && (tempPosition.getColumn() > 0) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
                || ((tempPosition.getRow() > 0) && (tempPosition.getColumn() > 0) && board.getPiece(tempPosition) == null)){
            moves.add(tempMove);
        }

        tempPosition = new ChessPosition(row + 2, col + 1);
        tempMove = new ChessMove(position, tempPosition, null);
        if((tempPosition.getRow() < 9) && (tempPosition.getColumn() < 9) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
                || ((tempPosition.getRow() < 9) && (tempPosition.getColumn() < 9) && board.getPiece(tempPosition) == null)){
            moves.add(tempMove);
        }

        tempPosition = new ChessPosition(row + 2, col - 1);
        tempMove = new ChessMove(position, tempPosition, null);
        if((tempPosition.getRow() < 9) && (tempPosition.getColumn() > 0) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
                || ((tempPosition.getRow() < 9) && (tempPosition.getColumn() > 0) && board.getPiece(tempPosition) == null )){
            moves.add(tempMove);
        }

        tempPosition = new ChessPosition(row + 1, col - 2);
        tempMove = new ChessMove(position, tempPosition, null);
        if((tempPosition.getRow() < 9) && (tempPosition.getColumn() > 0) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
                || ((tempPosition.getRow() < 9) && (tempPosition.getColumn() > 0) && board.getPiece(tempPosition) == null)){
            moves.add(tempMove);
        }

        tempPosition = new ChessPosition(row - 1, col - 2);
        tempMove = new ChessMove(position, tempPosition, null);
        if((tempPosition.getRow() > 0) && (tempPosition.getColumn() > 0) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
                || ((tempPosition.getRow() > 0) && (tempPosition.getColumn() > 0) && board.getPiece(tempPosition) == null)){
            moves.add(tempMove);
        }

        tempPosition = new ChessPosition(row + 1, col + 2);
        tempMove = new ChessMove(position, tempPosition, null);
        if((tempPosition.getRow() < 9) && (tempPosition.getColumn() < 9) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
                || ((tempPosition.getRow() < 9) && (tempPosition.getColumn() < 9) && board.getPiece(tempPosition) == null)){
            moves.add(tempMove);
        }

        tempPosition = new ChessPosition(row - 1, col + 2);
        tempMove = new ChessMove(position, tempPosition, null);
        if((tempPosition.getRow() > 0) && (tempPosition.getColumn() < 9) && board.getPiece(tempPosition) != null && board.getPiece(tempPosition).getTeamColor() != currColor
                || ((tempPosition.getRow() > 0) && (tempPosition.getColumn() < 9) && board.getPiece(tempPosition) == null)){
            moves.add(tempMove);
        }

        if(moves == null){
            //System.out.println("No moves available");
            return null;
        }
        return moves;
    }
}
