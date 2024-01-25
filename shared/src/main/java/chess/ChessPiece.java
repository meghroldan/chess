package chess;

import java.util.*;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor color;
    private ChessPiece.PieceType type;
    private Set<ChessMove> validMoves2 = new HashSet<>();
    private Map<ChessPosition,ChessPiece> myPieces = new HashMap<ChessPosition,ChessPiece>();

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        color = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        myPieces = board.getAllPieces();
        ChessPiece piece = board.getPiece(myPosition);

        BishopMovesInitial bishop = new BishopMovesInitial();

        if(piece.getPieceType() == PieceType.BISHOP){
            return bishop.pieceMovesAvailable(board, myPosition);
        }
        RookMovesInitial rook = new RookMovesInitial();
        if(piece.getPieceType() == PieceType.ROOK){
            return rook.pieceMovesAvailable(board, myPosition);
        }

        KnightMovesInitial knight = new KnightMovesInitial();
        if(piece.getPieceType() == PieceType.KNIGHT){
            return knight.pieceMovesAvailable(board, myPosition);
        }

        QueenMovesInitial queen = new QueenMovesInitial();
        if(piece.getPieceType() == PieceType.QUEEN){
            return queen.pieceMovesAvailable(board, myPosition);
        }

        KingMovesInitial king = new KingMovesInitial();
        if(piece.getPieceType() == PieceType.KING){
            return king.pieceMovesAvailable(board, myPosition);
        }

        PawnMovesInitial pawn = new PawnMovesInitial();
        if(piece.getPieceType() == PieceType.PAWN){
            return pawn.pieceMovesAvailable(board, myPosition);
        }

        return validMoves2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that=(ChessPiece) o;
        return color == that.color && type == that.type && Objects.equals(validMoves2, that.validMoves2) && Objects.equals(myPieces, that.myPieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, validMoves2, myPieces);
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "color=" + color +
                ", type=" + type +
                ", validMoves2=" + validMoves2 +
                ", myPieces=" + myPieces +
                '}';
    }
}
