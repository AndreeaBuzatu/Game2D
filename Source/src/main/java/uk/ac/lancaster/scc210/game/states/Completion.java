package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.TextEvent;
import uk.ac.lancaster.scc210.engine.InputListener;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.HighScores;
import uk.ac.lancaster.scc210.game.gui.MenuHeader;
import uk.ac.lancaster.scc210.game.resources.HighScore;
import uk.ac.lancaster.scc210.game.resources.HighScoreWriter;
import uk.ac.lancaster.scc210.game.resources.PlayerData;
import uk.ac.lancaster.scc210.game.resources.PlayerWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * The type Completion.
 */
public class Completion implements State, InputListener {
    private final int LIST_PADDING = 250;

    private final int NAME_LENGTH = 3;

    private final int UNKNOWN_INDEX = -1;

    private int playerScore;

    private StateBasedGame game;

    private HighScores highScores;

    private HighScoreWriter highScoreWriter;

    private InterfaceList highScoreList;

    private MenuHeader menuHeader;

    private boolean shouldEnterName, hasCompleted;

    private char[] name;

    private int charsEntered, playerScoreIndex;

    /**
     * Instantiates a new Completion.
     */
    public Completion() {
        playerScore = -1;

        name = new char[NAME_LENGTH];

        shouldEnterName = true;

        hasCompleted = false;

        charsEntered = 0;
    }

    @Override
    public void setup(StateBasedGame game) {
        this.game = game;

        game.addKeyListener(this);

        FontManager fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        FloatRect viewBounds = ((ViewSize) game.getServiceProvider().get(ViewSize.class)).getViewBounds();

        menuHeader = new MenuHeader("High Scores", fontManager, viewBounds);

        highScoreWriter = (HighScoreWriter) game.getServiceProvider().get(HighScoreWriter.class);

        highScores = (HighScores) game.getServiceProvider().get(HighScores.class);

        Vector2f headerPos = menuHeader.getPosition();

        highScoreList = new InterfaceList(game, fontManager.get("font"), new Vector2f(headerPos.x, headerPos.y + LIST_PADDING));

        playerScoreIndex = findPlayerScoreIndex();

        if (playerScoreIndex > UNKNOWN_INDEX) {
            putPlayerScoreInList(playerScoreIndex);
        }

        for (int i = 0; i < highScores.getHighScores().size(); i++) {
            HighScore highScoreData = highScores.getHighScores().get(i);

            highScoreList.addListOption(String.format("%d %s - %d", i + 1, highScoreData.getPlayerName(), highScoreData.getScore()), (() -> {
            }));
        }

        game.removeKeyListener(highScoreList);
    }

    @Override
    public void onEnter(StateBasedGame game) {
        game.addKeyListener(this);
    }

    @Override
    public void onExit(StateBasedGame game) {
        game.removeKeyListener(this);

        name = new char[NAME_LENGTH];

        shouldEnterName = true;

        charsEntered = 0;
    }

    @Override
    public void update(Time deltaTime) {
        if (hasCompleted) {
            game.popState();

            return;
        }

        if (playerScoreIndex < 0) {
            return;
        }

        formatScoreInput(playerScoreIndex);
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(menuHeader);

        target.draw(highScoreList);
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        if (keyevent.key == Keyboard.Key.RETURN) {
            if (playerScoreIndex > UNKNOWN_INDEX && charsEntered > 0) {
                try {
                    highScoreWriter.writeHighScores();

                } catch (ResourceNotFoundException e) {
                    StateBasedGame.LOGGER.log(Level.WARNING, e.getMessage());
                }

                // Once the player has completed the game, reset their score to 0 and lives back to default and write the file.
                PlayerWriter playerWriter = (PlayerWriter) game.getServiceProvider().get(PlayerWriter.class);

                PlayerData playerData = (PlayerData) game.getServiceProvider().get(PlayerData.class);

                playerData.setScore(0);

                playerData.resetLives();

                playerWriter.writePlayerLevel(playerData);
            }

            // Set a flag here so we don't remove the listener while operating on the list
            hasCompleted = true;

            return;
        }

        if (!shouldEnterName) {
            return;
        }

        if ((keyevent.key == Keyboard.Key.BACKSPACE) && (charsEntered > 0 && charsEntered <= NAME_LENGTH)) {
            charsEntered--;

            // Set the last character of the array to be null
            name[charsEntered] = '\0';
        }
    }

    @Override
    public void keyTyped(TextEvent textevent) {
        if (charsEntered < 0 || charsEntered > NAME_LENGTH - 1 || !shouldEnterName) {
            return;
        }

        char entered = textevent.character;

        if (Character.isLetterOrDigit(entered)) {
            name[charsEntered] = entered;

            charsEntered++;
        }
    }

    private void putPlayerScoreInList(int playerScoreIndex) {
        if (charsEntered > 0 && playerScoreIndex > 0) {
            return;
        }

        String playerName = new String(name).replaceAll("\0", "_");

        HighScore playerScoreData = new HighScore(playerName, playerScore);

        List<HighScore> highScoreData = highScores.getHighScores();

        // Add the score at the given index
        highScoreData.add(playerScoreIndex, playerScoreData);

        highScoreData.remove(highScoreData.size() - 1);

        // If the player is not third on the high score list, remove the last high score from the high score list
        if (playerScoreIndex < highScoreData.size() - 1) {
            playerScoreData.setScore(playerScore);
        }
    }

    private void formatScoreInput(int playerScoreIndex) {
        // Replace any null characters with underscores
        HighScore playerScoreData = highScores.getHighScores().get(playerScoreIndex);

        playerScoreData.setPlayerName(new String(name).replaceAll("\0", "_"));

        formatScore(playerScoreData);
    }

    private void formatScore(HighScore highScoreData) {
        highScoreList.getOptions().get(playerScoreIndex)
                .getText().setString(String.format("%d %s - %d", playerScoreIndex + 1, highScoreData.getPlayerName(), highScoreData.getScore()));
    }

    private int findPlayerScoreIndex() {
        List<Integer> scores = highScores.getHighScores().stream().map(HighScore::getScore).collect(Collectors.toList());

        List<Integer> scoreTemp = new ArrayList<>();

        int highScoreIndex = UNKNOWN_INDEX;

        int i = 0;

        for (int score : scores) {
            if (playerScore >= score && !scoreTemp.contains(playerScore)) {
                scoreTemp.add(playerScore);

                highScoreIndex = i;

                continue;
            }

            scoreTemp.add(score);

            i++;
        }

        return highScoreIndex;
    }

    /**
     * Sets player score.
     *
     * @param playerScore the player score
     */
    void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
