package chess;

import java.util.*;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private Map<ChessPosition,ChessPiece> myPieces = new HashMap<ChessPosition,ChessPiece>();

    public ChessBoard() {
        
    }

    public ChessBoard (ChessBoard old){
        for(Map.Entry<ChessPosition, ChessPiece> entry : old.getAllPieces().entrySet()) {
            ChessPosition tempPos = new ChessPosition(entry.getKey().getRow(), entry.getKey().getColumn());
            ChessPiece tempPie = new ChessPiece(entry.getValue().getTeamColor(), entry.getValue().getPieceType());
            this.myPieces.put(tempPos, tempPie);
        }
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        myPieces.put(position, piece);
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        ChessPiece temp = myPieces.get(position);
        return temp;
    }

    public Map<ChessPosition, ChessPiece> getAllPieces(){
        return myPieces;
    }

    /**
     * Sets the board to the default starting board TODO:debug bc it's not working
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        myPieces.clear();
        for(int column = 1; column <= 8; column++) {
            ChessPiece tempW=new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
            ChessPiece tempB=new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
            ChessPosition white=new ChessPosition(2, column);
            ChessPosition black=new ChessPosition(7, column);
            myPieces.put(white, tempW);
            if (column == 1 || column == 8) {
                ChessPiece tempWR=new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
                ChessPiece tempBR=new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
                ChessPosition whiteR=new ChessPosition(1, column);
                ChessPosition blackR=new ChessPosition(8, column);
                myPieces.put(whiteR, tempWR);
                myPieces.put(blackR, tempBR);
            }
            if (column == 3 || column == 6) {
                ChessPiece tempWB=new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
                ChessPiece tempBB=new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
                ChessPosition whiteB=new ChessPosition(1, column);
                ChessPosition blackB=new ChessPosition(8, column);
                myPieces.put(whiteB, tempWB);
                myPieces.put(blackB, tempBB);
            }
            if (column == 2 || column == 7) {
                ChessPiece tempWN=new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
                ChessPiece tempBN=new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
                ChessPosition whiteN=new ChessPosition(1, column);
                ChessPosition blackN=new ChessPosition(8, column);
                myPieces.put(whiteN, tempWN);
                myPieces.put(blackN, tempBN);
            }
            myPieces.put(black, tempB);
            if (column == 4) {
                ChessPiece tempWQ=new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
                ChessPiece tempBQ=new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
                ChessPosition whiteQ=new ChessPosition(1, column);
                ChessPosition blackQ=new ChessPosition(8, column);
                myPieces.put(whiteQ, tempWQ);
                myPieces.put(blackQ, tempBQ);
            }
            if (column == 5) {
                ChessPiece tempWK=new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
                ChessPiece tempBK=new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
                ChessPosition whiteK=new ChessPosition(1, column);
                ChessPosition blackK=new ChessPosition(8, column);
                myPieces.put(whiteK, tempWK);
                myPieces.put(blackK, tempBK);
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that=(ChessBoard) o;
        return Objects.equals(myPieces, that.myPieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myPieces);
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "myPieces=" + myPieces +
                '}';
    }
}
