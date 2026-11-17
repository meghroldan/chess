package client;

import dataaccess.DataAccessException;

import java.util.Arrays;
import java.util.Scanner;

public class ChessClient {

    private ServerFacade server;
    private Scanner scanner = new Scanner(System.in);
    private State state = State.LOGGED_OUT;
    private String output;
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
        //return output;
    }

    private String evalLoggedIn(String input){
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

            return "Logged in successfully.";
        } catch (Exception e) {
            return "Login failed.";
        }
    }

    private String register(){
        return output;
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
}
