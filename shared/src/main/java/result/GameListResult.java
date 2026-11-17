package result;


import model.GameData;
import java.util.ArrayList;

public record GameListResult(ArrayList<GameData> games) {
}
