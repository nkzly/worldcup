import model.Game;
import model.Score;

import java.util.*;
import java.util.function.Predicate;

public class FootballScoreBoard {


    public List<Game> getScoreBoard() {
        return scoreBoard;
    }
    private List<Game> scoreBoard = new ArrayList<>();

    public void startGame(String home_team, String away_team) {
        scoreBoard.add(new Game(home_team, away_team));
    }

    public Game getGame(String home_team, String away_team) {
        try {
        return scoreBoard.stream().filter(new Predicate<Game>() {
            @Override
            public boolean test(Game game) {
                return game.getHomeTeam().equals(home_team) && game.getAwayTeam().equals(away_team);
            }
        }).findAny().get();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Wrong Match");
        }
    }

    public void finishGame(String homeTeam, String awayTeam) {
        try {
            scoreBoard.removeIf(game -> game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam));
        } catch (Exception ex) {
            throw new IllegalArgumentException("Wrong Match");
        }
    }

    public void updateScore(String homeTeam, String awayTeam, Score score) {
        try {
            scoreBoard.stream().forEach(game -> {
                if (game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam)) {
                    game.setHomeScore(score.getHomeScore());
                    game.setAwayScore(score.getAwayScore());
                }
            });
        } catch (Exception ex) {
            throw new IllegalArgumentException("Wrong Match");
        }
    }

    public String getSummary() {
        String summary = "";
        Queue<Game> lifoQueue = Collections.asLifoQueue(new ArrayDeque<>());
        scoreBoard.forEach(lifoQueue::add);
        while (!lifoQueue.isEmpty()) {
            Game maxGameScore = lifoQueue.stream().max(Comparator.comparing(Game::getToTalScore)).orElse(null);
            summary += maxGameScore.getSummary();
            lifoQueue.remove(maxGameScore);
        }
        return summary;
    }

}
