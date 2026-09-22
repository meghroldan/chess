package chess;

import org.junit.jupiter.api.Order;

import java.util.Collection;
import java.util.Set;

public class KnightMove implements PieceMovement{

    private Set<ChessMove> moves;

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        return moves;
    }
}
