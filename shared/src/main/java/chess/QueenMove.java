package chess;

import java.util.Collection;
import java.util.Set;

public class QueenMove implements PieceMovement{
    private Set<ChessMove> moves;

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        return moves;
    }
}
