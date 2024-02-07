package chess;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private ChessBoard currBoard = new ChessBoard();
    private ChessBoard tempBoard = new ChessBoard(currBoard);
    private Map<ChessPosition, ChessPiece> pieces;  //this is only to pass through functions to test things - MUST EDIT BOARD
    private Set<ChessMove> validMovesToMake;
    private ChessGame.TeamColor turn = TeamColor.WHITE;

    public ChessGame() {

    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return turn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        turn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        return validMovesToMake;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece.PieceType tempType = pieces.get(move.getStartPosition()).getPieceType();
        TeamColor tempColor = pieces.get(move.getStartPosition()).getTeamColor();

        if(getTeamTurn() != tempColor){
            InvalidMoveException exceptionN = new InvalidMoveException("Not your turn");
            throw exceptionN;
        }
        if(currBoard.getPiece(move.getStartPosition()) == null){
            InvalidMoveException exceptionNoPiece = new InvalidMoveException("No piece");
        }

        validMovesToMake = (Set<ChessMove>) validMoves(move.getStartPosition());
        if(validMovesToMake.isEmpty() || !validMovesToMake.contains(move)){
            throw new InvalidMoveException("not a move");
        }

        if(currBoard.getAllPieces().get(move.getEndPosition()) != null && currBoard.getAllPieces().get(move.getEndPosition()).getTeamColor() != getTeamTurn()){

            //check if pawn and if can be promoted
            if (move.getPromotionPiece() != null && currBoard.getAllPieces().get(move.getStartPosition()).getPieceType() == ChessPiece.PieceType.PAWN) {
                ChessPiece tempPiece = new ChessPiece(getTeamTurn(), move.getPromotionPiece());
                ChessPiece.PieceType pieceToRemove = currBoard.getPiece(move.getEndPosition()).getPieceType();
                pieces.remove(move.getStartPosition(), currBoard.getPiece(move.getStartPosition()).getPieceType());
                pieces.remove(move.getEndPosition(), pieceToRemove);
                pieces.put(move.getEndPosition(), tempPiece);
                currBoard.setPieces(pieces); //see if that worked
            }
            else{
                ChessPiece tempPiece = new ChessPiece(getTeamTurn(), currBoard.getPiece(move.getStartPosition()).getPieceType());
                ChessPiece.PieceType pieceToRemove = currBoard.getPiece(move.getEndPosition()).getPieceType();
                pieces.remove(move.getStartPosition(), currBoard.getPiece(move.getStartPosition()).getPieceType());
                pieces.remove(move.getEndPosition(), pieceToRemove);
                pieces.put(move.getEndPosition(), tempPiece);
                currBoard.setPieces(pieces);
            }

        }
        if(currBoard.getPiece(move.getEndPosition()) != null){
            if(move.getPromotionPiece() != null){
                ChessPiece tempPiece = new ChessPiece(getTeamTurn(), move.getPromotionPiece());
                pieces.remove(move.getStartPosition(), currBoard.getPiece(move.getStartPosition()).getPieceType());
                pieces.put(move.getEndPosition(), tempPiece);
                currBoard.setPieces(pieces);
            }
            else{
                ChessPiece tempPiece = new ChessPiece(getTeamTurn(), currBoard.getPiece(move.getStartPosition()).getPieceType());
                pieces.remove(move.getStartPosition(), currBoard.getPiece(move.getStartPosition()).getPieceType());
                pieces.put(move.getEndPosition(), tempPiece);
                currBoard.setPieces(pieces);
            }
        }


    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        currBoard = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return currBoard;
    }
}
