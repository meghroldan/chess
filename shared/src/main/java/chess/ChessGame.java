package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static chess.ChessPiece.PieceType.KING;

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
        pieces = currBoard.getAllPieces();
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
        validMovesToMake =(Set<ChessMove>) currBoard.getAllPieces().get(startPosition).pieceMoves(currBoard, startPosition);
        //make sure the moves are valid for this piece
        tempBoard = new ChessBoard((ChessBoard) currBoard);


        for(ChessMove moveToMake : validMovesToMake){
            //if(tempBoard.getPiece(moveToMake.getEndPosition()).getTeamColor() == color){
            //    continue;
            //}
            tempBoard = new ChessBoard((ChessBoard) currBoard);
            ChessPiece type = tempBoard.getPiece(startPosition);
            tempBoard.getAllPieces().remove(startPosition);
            tempBoard.addPiece(moveToMake.getEndPosition(), type);
            if(!isInCheck(color)){
                tempMoves.add(moveToMake);
            }
            tempBoard.getAllPieces().remove(moveToMake.getEndPosition());
            tempBoard.addPiece(moveToMake.getStartPosition(), type);
        }



        validMovesToMake = tempMoves;


        if(validMovesToMake == null){ //TODO see if we need to do this
            return null;
        }
        return validMovesToMake;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        pieces = currBoard.getAllPieces();
        ChessPiece.PieceType tempType = pieces.get(move.getStartPosition()).getPieceType();
        TeamColor tempColor = pieces.get(move.getStartPosition()).getTeamColor();

        if(getTeamTurn() != tempColor){
            InvalidMoveException exceptionN = new InvalidMoveException("Not your turn");
            throw exceptionN;
        }
        if(currBoard.getPiece(move.getStartPosition()) == null){
            InvalidMoveException exceptionNoPiece = new InvalidMoveException("No piece");
        }

        //if(currBoard.getPiece(move.getEndPosition()).getTeamColor() == tempColor){
        //    InvalidMoveException exceptionNoPiece = new InvalidMoveException("Same Color");
        //}

        validMovesToMake = (Set<ChessMove>) validMoves(move.getStartPosition());
        if(validMovesToMake.isEmpty() || !validMovesToMake.contains(move)){
            throw new InvalidMoveException("not a move");
        }

        if(currBoard.getAllPieces().get(move.getEndPosition()) != null && currBoard.getAllPieces().get(move.getEndPosition()).getTeamColor() != getTeamTurn()){

            //check if pawn and if can be promoted
            if (move.getPromotionPiece() != null && currBoard.getAllPieces().get(move.getStartPosition()).getPieceType() == ChessPiece.PieceType.PAWN) {
                ChessPiece tempPiece = new ChessPiece(getTeamTurn(), move.getPromotionPiece());
                //ChessPiece.PieceType pieceToRemove = currBoard.getPiece(move.getEndPosition()).getPieceType();
                pieces.remove(move.getStartPosition());
                pieces.remove(move.getEndPosition());
                pieces.put(move.getEndPosition(), tempPiece);
                currBoard.setPieces(pieces); //see if that worked
            }
            else{
                ChessPiece tempPiece = new ChessPiece(getTeamTurn(), tempType);
                //ChessPiece.PieceType pieceToRemove = currBoard.getPiece(move.getEndPosition()).getPieceType();
                pieces.remove(move.getStartPosition());
                pieces.remove(move.getEndPosition());
                pieces.put(move.getEndPosition(), tempPiece);
                currBoard.setPieces(pieces);
            }

        }
        if(currBoard.getPiece(move.getEndPosition()) == null){
            if(move.getPromotionPiece() != null){
                ChessPiece tempPiece = new ChessPiece(getTeamTurn(), move.getPromotionPiece());
                pieces.remove(move.getStartPosition());
                pieces.put(move.getEndPosition(), tempPiece);
                currBoard.setPieces(pieces);
            }
            else{
                ChessPiece tempPiece = new ChessPiece(getTeamTurn(), currBoard.getPiece(move.getStartPosition()).getPieceType());
                pieces.remove(move.getStartPosition());
                pieces.put(move.getEndPosition(), tempPiece);
                currBoard.setPieces(pieces);
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

        if(tempBoard.getAllPieces().size() == 0){
            tempBoard = new ChessBoard((ChessBoard) currBoard);
        }
    /*
    if(tempBoard.getMyPieces().size() != currBoard.getMyPieces().size()){
      tempBoard = new IChessBoard((IChessBoard) currBoard);
    }

     */
        //this gets piece partially trapped but not cannot elimate check

        pieces = tempBoard.getAllPieces();
        ChessPosition king = null;
        for(Map.Entry<ChessPosition, ChessPiece> en : pieces.entrySet()){
            if(en.getValue().getPieceType() == KING && en.getValue().getTeamColor() == teamColor){
                king = en.getKey();
            }
        }

        Set<ChessMove> opponantMoves = new HashSet<>();
        for(Map.Entry<ChessPosition, ChessPiece> en : pieces.entrySet()){
            if(en.getValue().getTeamColor() != teamColor){
                opponantMoves =(Set<ChessMove>) tempBoard.getPiece(en.getKey()).pieceMoves(tempBoard, en.getKey());
                for(ChessMove move : opponantMoves){
                    if(move.getEndPosition().equals(king)){
                        return true;
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
        tempBoard = new ChessBoard((ChessBoard) currBoard);

        pieces = tempBoard.getAllPieces();
        for(Map.Entry<ChessPosition, ChessPiece> en : pieces.entrySet()) {
            if (en.getValue().getTeamColor() == teamColor) {
                Set<ChessMove> tempMoves = new HashSet<>();
                Set<ChessMove> possibleMoves = new HashSet<>();
                possibleMoves =(Set<ChessMove>) validMoves(en.getKey() );
                for(ChessMove moveToMake : possibleMoves){
                    ChessPiece type = tempBoard.getPiece(moveToMake.getStartPosition());
                    tempBoard.getAllPieces().remove(moveToMake.getStartPosition());
                    tempBoard.addPiece(moveToMake.getEndPosition(), type);
                    if(!isInCheck(teamColor)){
                        tempMoves.add(moveToMake);
                    }
                    tempBoard.getAllPieces().remove(moveToMake.getEndPosition());
                    tempBoard.addPiece(moveToMake.getStartPosition(), type);
                }
                validMovesToMake = tempMoves;
                if(!validMovesToMake.isEmpty()) {
                    return false;
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
        ChessBoard temp = new ChessBoard((ChessBoard) currBoard);
        pieces = temp.getAllPieces();


        Set<ChessMove> opponantMoves = new HashSet<>();
        for(Map.Entry<ChessPosition, ChessPiece> en : pieces.entrySet()) {
            if (en.getValue().getTeamColor() == teamColor) {
                //this has to be valid moves only
                opponantMoves=(Set<ChessMove>) validMoves(en.getKey());
                if (!opponantMoves.isEmpty()) {
                    return false;
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
