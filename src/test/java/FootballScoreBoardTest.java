import model.Score;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FootballScoreBoardTest {


    FootballScoreBoard scoreBoard;
    @Before
    public void setUp() {
        scoreBoard = new FootballScoreBoard();
    }

    @Test
    public void startgame() {
        scoreBoard.startGame("Home team", "Away team");
        assertEquals("Home team - Away team : 0-0", scoreBoard.getGame("Home team", "Away team").toString());
    }
    @Test
    public void finishgame() {
        scoreBoard.startGame("Home team", "Away team");
        scoreBoard.finishGame("Home team", "Away team");
        assertTrue(scoreBoard.getScoreBoard().isEmpty());
    }

    @Test
    public void updatescore() {
        Score score = new Score(0, 1);
        scoreBoard.startGame("Home team", "Away team");
        scoreBoard.updateScore("Home team", "Away team", score);
        assertEquals("Home team - Away team : 0-1", scoreBoard.getGame("Home team", "Away team").toString());
    }

    @Test
    public void getasummaryofgamesbytotalscore() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateScore("Mexico", "Canada", new Score(0, 5));
        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.updateScore("Spain", "Brazil", new Score(10, 2));
        scoreBoard.startGame("Germany", "France");
        scoreBoard.updateScore("Germany", "France", new Score(2, 2));
        scoreBoard.startGame("Uruguay", "Italy");
        scoreBoard.updateScore("Uruguay", "Italy", new Score(6, 6));
        scoreBoard.startGame("Argentina", "Australia");
        scoreBoard.updateScore("Argentina", "Australia", new Score(3, 1));

        assertEquals("Uruguay - Italy:6-6\n" +
                "Spain - Brazil:10-2\n" +
                "Mexico - Canada:0-5\n" +
                "Argentina - Australia:3-1\n" +
                "Germany - France:2-2\n", scoreBoard.getSummary());

    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException() {
        scoreBoard.startGame("Home team", "Away team");
        assertEquals("Home team - Away team : 0-0", scoreBoard.getGame("Turkey", "France").toString());
    }

}
