package client;

import dataaccess.DataAccessException;
import model.GameData;

import java.util.*;

public class ChessClient {

    private ServerFacade server;
    private Scanner scanner = new Scanner(System.in);
    private State state = State.LOGGED_OUT;
    private final HashMap<Integer, GameData> gameList = new HashMap<>(); //integers for the user to see
    private ArrayList<GameData> currentGames; //unmapped games
    private String output = "not yet implemented";
    private String authToken;

    enum State {
        LOGGED_OUT,
        LOGGED_IN
    }

    public ChessClient(String serverUrl) {
        server = new ServerFacade(serverUrl);
    }

    public void run() {
        System.out.println(" Welcome to CS 240 Chess! Sign in to start.");
        System.out.print(help());

        Scanner scanner = new Scanner(System.in);

        String result = "";

        while (!result.equalsIgnoreCase("quit")) {

            printPrompt();
            String line = scanner.nextLine();

            try {
                result = eval(line);
                if (!result.isEmpty()) {
                    System.out.println(result);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());

            }
        }
        System.out.println("Goodbye!");

    }

    public String eval(String input) {
        if (state == State.LOGGED_IN) {
            return evalLoggedIn(input);

        } else {
            return evalLoggedOut(input);
        }
    }

    private String evalLoggedOut(String input) {
        try {
            String[] tokens = input.toLowerCase().split(" ");
            String cmd = (tokens.length > 0) ? tokens[0] : "help";
            String[] params = Arrays.copyOfRange(tokens, 1, tokens.length);
            return switch (cmd) {
                case "help" -> help();
                case "quit" -> "quit";
                case "login" -> login();
                case "register" -> register();
                default -> help();
            };
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    private String evalLoggedIn(String input){
        try {
            String[] tokens = input.toLowerCase().split(" ");
            String cmd = (tokens.length > 0) ? tokens[0] : "help";
            String[] params = Arrays.copyOfRange(tokens, 1, tokens.length);
            return switch (cmd) {
                case "help" -> help();
                case "logout" -> logout();
                case "create" -> createGame();
                case "list" -> listGames();
                case "play" -> playGame();
                case "observe" -> observeGame();
                default -> help();
            };
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    private String logout(){
        try{
            server.logout(authToken);

            authToken = null;
            state = State.LOGGED_OUT;
            return "Logged out successfully. \n" + help();
        } catch (Exception e) {
            return "Logout failed. \n" + help();
        }
    }

    private String createGame(){
        System.out.println("Enter game name: ");
        String gameName = scanner.nextLine();

        try {
            var auth = server.createGame(authToken, gameName);

            return "Game created successfully. \n" + help();
        } catch (Exception e) {
            System.out.println(authToken);
            return "Game unable to be created as entered. \n"+ e.getMessage() + help();
        }
    }

    private String listGames(){
        try {
            var gameResult = server.listGames(authToken);

            currentGames = gameResult.games();
            if (currentGames.isEmpty()) {
                return "No games available.";
            }

            StringBuilder userGamesString = new StringBuilder();
            userGamesString.append("Current Games: \n");

            for (int i = 0; i < currentGames.size(); i++) {
                GameData gameData = currentGames.get(i);

                userGamesString.append(i + 1);
                userGamesString.append(". ");
                userGamesString.append(gameData.gameName()).append("\n");
                userGamesString.append("White: ");
                userGamesString.append(gameData.whiteUsername()).append("\n");
                userGamesString.append("Black: ");
                userGamesString.append(gameData.blackUsername()).append("\n\n");

                gameList.put((i+1), gameData);

            }

            return userGamesString.toString();

        } catch (Exception e) {
            return "Games unable to be listed. \n" + help();
        }
    }

    private String playGame(){
        return output;
    }

    private String observeGame(){
        return output;
    }

    private String login(){
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        try {
            var auth = server.login(username, password);
            authToken = auth.authToken();
            state = State.LOGGED_IN;

            return "Logged in successfully. \n" + help();
        } catch (Exception e) {
            return "Login failed. \n" + help();
        }
    }

    private String register(){
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        try {
            var auth = server.register(username, password, email);
            authToken = auth.authToken();
            state = State.LOGGED_IN;

            return "Registered successfully.\n" + help();
        } catch (Exception e) {
            return "Register failed: " + e.getMessage() + "\n" + help();
        }
    }

    public String help() {
        if (state == State.LOGGED_OUT) {
            return """
            Available commands:
              Help
              Quit
              Login
              Register    
            """;
        }
        return """
        Available commands:
          Help
          Logout
          Create Game
          List Games
          Play Game
          Observe Games      
        """;
    }

    private void printPrompt() {
        if (state == State.LOGGED_IN) {
            System.out.print("\n[LOGGED IN] >>> ");

        } else {
            System.out.print("\n[LOGGED OUT] >>> ");

        }
    }

    private void assertSignedIn() throws DataAccessException {
        if (state == State.LOGGED_OUT) {
            throw new DataAccessException("You must sign in");
        }
    }
}
