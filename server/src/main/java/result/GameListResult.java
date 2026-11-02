package result;

import chess.ChessGame;

import java.util.Collection;

public record GameListResult(Collection<ChessGame> games) {
}
