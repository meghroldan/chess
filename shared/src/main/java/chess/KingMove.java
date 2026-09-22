package chess;

import java.util.Collection;
import java.util.Set;

public class KingMove implements PieceMovement{

    private Set<ChessMove> moves;

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        return moves;
    }
}
