package chess;

import java.util.*;

import static chess.ChessPiece.PieceType.KING;

/**
 * A class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private ChessBoard currBoard = new ChessBoard();

    private Set<ChessMove> validMoves = new HashSet<>();
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
     * Sets which teams turn it is
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
     * Gets all valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        Set<ChessMove> tempMoves = new HashSet<>();
        TeamColor color = currBoard.getPiece(startPosition).getTeamColor();
        validMoves =(Set<ChessMove>) currBoard.getPiece(startPosition).pieceMoves(currBoard, startPosition);

        for(ChessMove moveToMake : validMoves){
            ChessPiece endPiece = null;
            if(currBoard.getPiece(moveToMake.getEndPosition()) != null){
                ChessPiece.PieceType endType = currBoard.getPiece(moveToMake.getEndPosition()).getPieceType();
                TeamColor endColor = currBoard.getPiece(moveToMake.getEndPosition()).getTeamColor();
                endPiece = new ChessPiece(endColor, endType);

            }

            //ChessBoard tempBoard = new ChessBoard((ChessBoard) currBoard);
            ChessPiece type = currBoard.getPiece(startPosition);
            currBoard.addPiece(startPosition, null);
            currBoard.addPiece(moveToMake.getEndPosition(), type);
            if(!isInCheck(color)){
                tempMoves.add(moveToMake);
            }
            currBoard.addPiece(moveToMake.getEndPosition(), endPiece); //this is the problem line
            currBoard.addPiece(moveToMake.getStartPosition(), type);
        }



    validMoves = tempMoves;



        return validMoves;
    }

    /**
     * Makes a move in the chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if((currBoard.getPiece((move.getStartPosition())).getPieceType()) == null){
            InvalidMoveException exceptionN = new InvalidMoveException("Not valid piece");
            throw exceptionN;
        }
        ChessPiece.PieceType tempType = currBoard.getPiece(move.getStartPosition()).getPieceType();
        TeamColor tempColor = currBoard.getPiece(move.getStartPosition()).getTeamColor();

        if(getTeamTurn() != tempColor){
            InvalidMoveException exceptionN = new InvalidMoveException("Not your turn");
            throw exceptionN;
        }
        if(currBoard.getPiece(move.getStartPosition()) == null){
            InvalidMoveException exceptionNoPiece = new InvalidMoveException("No piece");
        }

        validMoves = (Set<ChessMove>) validMoves(move.getStartPosition());

        validMoves = (Set<ChessMove>) validMoves(move.getStartPosition());
        if(validMoves.isEmpty() || !validMoves.contains(move)){
            throw new InvalidMoveException("not a move");
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition king = null;
        for(int i = 1; i < 9; i++){
            for(int j = 1; j < 9; j++){
                ChessPosition startPos = new ChessPosition(i, j);
                if(currBoard.getPiece(startPos) != null && currBoard.getPiece(startPos).getPieceType() == KING && currBoard.getPiece(startPos).getTeamColor() == teamColor){
                    king = startPos;
                    break;
                }
            }
        }

        Set<ChessMove> opponantMoves = new HashSet<>();
        for(int i = 1; i < 9; i++){
            for(int j = 1; j < 9; j++){
                ChessPosition startPos = new ChessPosition(i, j);
                if(currBoard.getPiece(startPos) != null && currBoard.getPiece(startPos).getTeamColor() != teamColor){
                    opponantMoves =(Set<ChessMove>) currBoard.getPiece(startPos).pieceMoves(currBoard, startPos);
                    for(ChessMove move : opponantMoves){
                        if(move.getEndPosition().equals(king)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if(!isInCheck(teamColor)){
            return false;
        }

        for(int i = 1; i < 9; i++) {
            for (int j=1; j < 9; j++) {
                ChessPosition startPos=new ChessPosition(i, j);
                if (currBoard.getPiece(startPos) != null && currBoard.getPiece(startPos).getTeamColor() == teamColor) {
                    if (!validMoves(startPos).isEmpty()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        for(int i = 1; i < 9; i++) {
            for (int j=1; j < 9; j++) {
                ChessPosition startPos=new ChessPosition(i, j);
                if (currBoard.getPiece(startPos) != null && currBoard.getPiece(startPos).getTeamColor() == teamColor) {
                    if (!validMoves(startPos).isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Sets this game's chessboard to a given board
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

    /*
    @Override
    public int hashCode() {
        return Objects.hash(currBoard, validMoves, turn);
    }
     */

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame that=(ChessGame) o;
        return (Objects.equals(currBoard, that.currBoard) && Objects.equals(validMoves, that.validMoves) && Objects.equals(turn, that.turn));
    }
     */

}
