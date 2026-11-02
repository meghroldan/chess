package result;

import chess.ChessGame;
import model.GameData;

import java.util.ArrayList;
import java.util.Collection;

public record GameListResult(ArrayList<GameData> games) {
}
