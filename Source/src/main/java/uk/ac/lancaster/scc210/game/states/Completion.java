package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.TextEvent;
import uk.ac.lancaster.scc210.engine.InputListener;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.HighScores;
import uk.ac.lancaster.scc210.game.resources.HighScore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Completion implements State, InputListener {
    private final int NAME_LENGTH = 3;

    private final int playerScore;

    private HighScores highScores;

    private InterfaceList highScoreList;

    private boolean shouldEnterName;

    private char[] name;

    private int charsEntered, playerScoreIndex;

    public Completion(int playerScore) {
        this.playerScore = playerScore;

        name = new char[NAME_LENGTH];

        shouldEnterName = true;

        charsEntered = 0;
    }

    @Override
    public void setup(StateBasedGame game) {
        game.addKeyListener(this);

        FontManager fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        highScores = (HighScores) game.getServiceProvider().get(HighScores.class);

        highScoreList = new InterfaceList(game, fontManager.get("font"), new Vector2f(300, 300));

        for (int i = 0; i < highScores.getHighScores().size(); i++) {
            HighScore highScoreData = highScores.getHighScores().get(i);

            highScoreList.addListOption(String.format("%d %s - %d", i + 1, highScoreData.getPlayerName(), highScoreData.getScore()), (() -> {
            }));
        }

        playerScoreIndex = findPlayerScoreIndex();

        putPlayerScoreInList(playerScoreIndex);
    }

    @Override
    public void update(Time deltaTime) {
        formatScoreInput(playerScoreIndex);
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(highScoreList);
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
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
        HighScore playerScoreData = highScores.getHighScores().get(playerScoreIndex);

        playerScoreData.setScore(playerScore);
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

        int highScoreIndex = -1;

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
}
