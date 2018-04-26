package edu.dzioba;

import java.util.function.Supplier;

public class GameSessionManager {
    Supplier<String> userInputProvider;
    private Journalist journalist;
    private Player currentPlayer;
    private GameSession session;

    public GameSessionManager(Supplier<String> userInputProvider, Journalist journalist) {
        this.userInputProvider = userInputProvider;
        this.journalist = journalist;
    }

    public Player getWinner() { // TODO
        return new Player("Foo", Sign.X);
    }

    public boolean isEndOfGameSession(GameSession session) {
        return session.games.size() == 3;
    }

    public void setUpPlayers() {
        setUpPlayer(session, Sign.X);
        journalist.sayMessage("Player added");
        setUpPlayer(session, Sign.O);
        journalist.sayMessage("Player added");
    }

    private void setUpPlayer(GameSession session, Sign sign) {
        journalist.sayMessage("Hello player " + sign.name() + ". Please say me what is your name: ");
        String playerName = userInputProvider.get();
        session.addPlayer(new Player(playerName, sign));
    }

    public Journalist getJournalist() {
        return journalist;
    }

    public Sign askWhoShouldBegin() {
        String userInput;
        do {
            journalist.sayMessage("Who should begins? (X or O)");
            userInput = userInputProvider.get();
        } while (!userInput.equals("O") && !userInput.equals("X"));
        return Sign.valueOf(userInput);
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    private Player searchPlayer(Sign sign) {
        Player chosenPlayer = null;
        for (Player player : session.getPlayers()) {
            if(player.sign.equals(sign))
                chosenPlayer = player;
        }
        return chosenPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setUpSession(GameSession gameSession) {
        this.session = gameSession;
    }

    public void setUpFirstPlayer() {
        Sign sign = askWhoShouldBegin();
        Player player = searchPlayer(sign);
        setCurrentPlayer(player);
    }
}
