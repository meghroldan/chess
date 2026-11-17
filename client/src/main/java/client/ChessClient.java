package client;

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
        return output;
    }

    private String evalLoggedIn(String input){
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
