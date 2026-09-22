package chess;

import java.util.Collection;
import java.util.Set;

public class BishopMove implements PieceMovement{

    private Set<ChessMove> moves;

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        return moves;
    }
}
