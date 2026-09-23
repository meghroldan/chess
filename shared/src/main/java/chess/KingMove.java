package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class KingMove implements PieceMovement{

    private Set<ChessMove> moves = new HashSet<>();;

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        return moves;
    }
}
