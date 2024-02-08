package chess;

import java.util.*;

import static chess.ChessPiece.PieceType.KING;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private ChessBoard currBoard = new ChessBoard();
    //private ChessBoard tempBoard = new ChessBoard(currBoard);
    //private Map<ChessPosition, ChessPiece> pieces = currBoard.getAllPieces();  //this is only to pass through functions to test things - MUST EDIT BOARD
    private Set<ChessMove> validMovesToMake = new HashSet<>();
    private ChessGame.TeamColor turn = TeamColor.WHITE;

    public ChessGame() {
        //pieces = currBoard.getAllPieces();
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
        Set<ChessMove> tempMoves = new HashSet<>();
        TeamColor color = currBoard.getPiece(startPosition).getTeamColor();
        validMovesToMake =(Set<ChessMove>) currBoard.getPiece(startPosition).pieceMoves(currBoard, startPosition);

        //tempBoard = new ChessBoard((ChessBoard) currBoard);


        for(ChessMove moveToMake : validMovesToMake){

            //ChessBoard tempBoard = new ChessBoard((ChessBoard) currBoard);
            ChessPiece type = currBoard.getPiece(startPosition);
            currBoard.addPiece(startPosition, null);
            currBoard.addPiece(moveToMake.getEndPosition(), type);
            if(!isInCheck(color)){
                tempMoves.add(moveToMake);
            }
            currBoard.addPiece(moveToMake.getEndPosition(), null);
            currBoard.addPiece(moveToMake.getStartPosition(), type);
        }



        validMovesToMake = tempMoves;



        return validMovesToMake;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        //pieces = currBoard.getAllPieces();
        ChessPiece.PieceType tempType = currBoard.getPiece(move.getStartPosition()).getPieceType();
        TeamColor tempColor = currBoard.getPiece(move.getStartPosition()).getTeamColor();

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
        //pieces = currBoard.getAllPieces();

        if(currBoard.getPiece(move.getEndPosition()) != null && currBoard.getPiece(move.getEndPosition()).getTeamColor() != getTeamTurn()){

            //check if pawn and if can be promoted
            if (move.getPromotionPiece() != null && currBoard.getPiece(move.getStartPosition()).getPieceType() == ChessPiece.PieceType.PAWN) {
                ChessPiece tempPiece = new ChessPiece(getTeamTurn(), move.getPromotionPiece());
                ChessPiece.PieceType pieceToRemove = currBoard.getPiece(move.getEndPosition()).getPieceType();
                currBoard.addPiece(move.getStartPosition(), null);
                currBoard.addPiece(move.getEndPosition(), null);
                currBoard.addPiece(move.getEndPosition(), tempPiece);
                //currBoard.setPieces(pieces); //see if that worked
            }
            else{
                //pieces = currBoard.getAllPieces();
                ChessPiece tempPiece = new ChessPiece(getTeamTurn(), tempType);
                ChessPiece.PieceType pieceToRemove = currBoard.getPiece(move.getEndPosition()).getPieceType();
                currBoard.addPiece(move.getStartPosition(), null);
                currBoard.addPiece(move.getEndPosition(), null);
                currBoard.addPiece(move.getEndPosition(), tempPiece);
                //currBoard.setPieces(pieces);
            }

        }
        if(currBoard.getPiece(move.getEndPosition()) == null){
            if(move.getPromotionPiece() != null){
                ChessPiece tempPiece = new ChessPiece(getTeamTurn(), move.getPromotionPiece());
                currBoard.addPiece(move.getStartPosition(), null);
                currBoard.addPiece(move.getEndPosition(), tempPiece);
                //currBoard.setPieces(pieces);
            }
            else{
                ChessPiece tempPiece = new ChessPiece(getTeamTurn(), currBoard.getPiece(move.getStartPosition()).getPieceType());
                currBoard.addPiece(move.getStartPosition(), null);
                currBoard.addPiece(move.getEndPosition(), tempPiece);
                //currBoard.setPieces(pieces);
            }
        }

        if(getTeamTurn() == TeamColor.BLACK){
            setTeamTurn(TeamColor.WHITE);
        }
        else{
            setTeamTurn(TeamColor.BLACK);
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

        //ChessBoard tempBoard = new ChessBoard((ChessBoard) currBoard);
        if(!isInCheck(teamColor)){
            return false;
        }

        //pieces = tempBoard.getAllPieces();
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
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        //ChessBoard tempBoard = new  ChessBoard((ChessBoard) currBoard);
        //pieces = temp.getAllPieces();


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
