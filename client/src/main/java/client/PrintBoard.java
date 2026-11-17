package client;

import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;
import ui.EscapeSequences;

public class PrintBoard {

    private ChessGame game;

    /**
     * Takes in a chess game and displays it from either the white or black perspective
     * @param gameToDisplay
     */
    public PrintBoard(ChessGame gameToDisplay){
        game = gameToDisplay;
    }

    public void print(String color){

        if(color.equalsIgnoreCase("white")){
            System.out.print(EscapeSequences.ERASE_SCREEN);
            for (int row = 8; row >= 1; row--) {
                System.out.printf(" %d ", row);
                for (int col = 1; col <= 8; col++) {

                    boolean lightSquare = (row + col) % 2 == 0;

                    if (lightSquare) {
                        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE);
                    }
                    else {
                        System.out.print(EscapeSequences.SET_BG_COLOR_MAGENTA);
                    }

                    ChessPosition pos = new ChessPosition(row, col);
                    ChessPiece piece = game.getBoard().getPiece(pos);

                    System.out.print(getPieceString(piece));
                    System.out.print(EscapeSequences.RESET_TEXT_COLOR);
                }
                System.out.print(EscapeSequences.RESET_BG_COLOR);
                System.out.println();

            }
            System.out.println("    a   b   c   d   e   f   g   h");
        }
        else{
            System.out.print(EscapeSequences.ERASE_SCREEN);
            System.out.print(EscapeSequences.SET_TEXT_BOLD);
            for (int row = 1; row <= 8; row++) {
                System.out.printf(" %d ", row);
                for (int col = 8; col >= 1; col--) {

                    boolean lightSquare = (row + col) % 2 == 0;

                    if (lightSquare) {
                        System.out.print(EscapeSequences.SET_BG_COLOR_WHITE);
                    }
                    else {
                        System.out.print(EscapeSequences.SET_BG_COLOR_MAGENTA);
                    }

                    ChessPosition pos = new ChessPosition(row, col);
                    ChessPiece piece = game.getBoard().getPiece(pos);

                    System.out.print(getPieceString(piece));
                    System.out.print(EscapeSequences.RESET_TEXT_COLOR);
                }
                System.out.print(EscapeSequences.RESET_BG_COLOR);
                System.out.println();

            }
            System.out.println("    h   g   f   e   d   c   b   a");
        }
    }

    private String getPieceString(ChessPiece piece) {
        if (piece == null) {
            return EscapeSequences.EMPTY;
        }
        ChessPiece.PieceType pieceType = piece.getPieceType();
        if(piece.getTeamColor() == ChessGame.TeamColor.WHITE){
            if(pieceType == ChessPiece.PieceType.KING){
                return EscapeSequences.SET_TEXT_COLOR_LIGHT_GREY + EscapeSequences.WHITE_KING;
            }
            if(pieceType == ChessPiece.PieceType.QUEEN){
                return EscapeSequences.SET_TEXT_COLOR_LIGHT_GREY + EscapeSequences.WHITE_QUEEN;
            }
            if(pieceType == ChessPiece.PieceType.KNIGHT){
                return EscapeSequences.SET_TEXT_COLOR_LIGHT_GREY + EscapeSequences.WHITE_KNIGHT;
            }
            if(pieceType == ChessPiece.PieceType.ROOK){
                return EscapeSequences.SET_TEXT_COLOR_LIGHT_GREY + EscapeSequences.WHITE_ROOK;
            }
            if(pieceType == ChessPiece.PieceType.BISHOP){
                return EscapeSequences.SET_TEXT_COLOR_LIGHT_GREY + EscapeSequences.WHITE_BISHOP;
            }
            if(pieceType == ChessPiece.PieceType.PAWN){
                return EscapeSequences.SET_TEXT_COLOR_LIGHT_GREY + EscapeSequences.WHITE_PAWN;
            }
        }
        else{
            if(pieceType == ChessPiece.PieceType.KING){
                return EscapeSequences.SET_TEXT_COLOR_BLACK + EscapeSequences.BLACK_KING;
            }
            if(pieceType == ChessPiece.PieceType.QUEEN){
                return EscapeSequences.SET_TEXT_COLOR_BLACK + EscapeSequences.BLACK_QUEEN;
            }
            if(pieceType == ChessPiece.PieceType.KNIGHT){
                return EscapeSequences.SET_TEXT_COLOR_BLACK + EscapeSequences.BLACK_KNIGHT;
            }
            if(pieceType == ChessPiece.PieceType.ROOK){
                return EscapeSequences.SET_TEXT_COLOR_BLACK + EscapeSequences.BLACK_ROOK;
            }
            if(pieceType == ChessPiece.PieceType.BISHOP){
                return EscapeSequences.SET_TEXT_COLOR_BLACK + EscapeSequences.BLACK_BISHOP;
            }
            if(pieceType == ChessPiece.PieceType.PAWN){
                return EscapeSequences.SET_TEXT_COLOR_BLACK + EscapeSequences.BLACK_PAWN;
            }
        }
        return EscapeSequences.EMPTY;
    }
}
