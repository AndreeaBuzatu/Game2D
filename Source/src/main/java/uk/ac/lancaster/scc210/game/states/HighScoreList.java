package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;
import uk.ac.lancaster.scc210.engine.InputListener;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.HighScores;
import uk.ac.lancaster.scc210.game.resources.HighScore;

import java.util.List;

public class HighScoreList implements State, InputListener {
    private final int MENU_TEXT_SIZE = 100;

    private final int LIST_PADDING = 250;

    private StateBasedGame game;

    private FontManager fontManager;

    private HighScores highScores;

    private InterfaceList highScoreList;

    private Text menuHeader, exitText;

    private FloatRect viewBounds;

    private Keyboard.Key pressedKey;

    /**
     * Setup for the current state (like a constructor).
     *
     * @param game the game
     */
    @Override
    public void setup(StateBasedGame game) {
        this.game = game;

        game.addKeyListener(this);

        fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        highScores = (HighScores) game.getServiceProvider().get(HighScores.class);

        viewBounds = ((ViewSize) game.getServiceProvider().get(ViewSize.class)).getViewBounds();

        createHeader();

        createExitText();

        createHighScoreList();
    }

    /**
     * Update the current state of the screen.
     *
     * @param deltaTime the delta time
     */
    @Override
    public void update(Time deltaTime) {
        if (game != null && pressedKey != null && pressedKey == Keyboard.Key.ESCAPE) {
            game.popState();
        }
    }

    /**
     * Draw the current state to the screen.
     *
     * @param target the target usually RenderWindow
     */
    @Override
    public void draw(RenderTarget target) {
        target.draw(menuHeader);

        target.draw(exitText);

        target.draw(highScoreList);
    }

    private void createHeader() {
        menuHeader = new Text();

        menuHeader.setString("High Scores");

        menuHeader.setFont(fontManager.get("font"));

        menuHeader.setCharacterSize(MENU_TEXT_SIZE);

        FloatRect headerBounds = menuHeader.getGlobalBounds();

        Vector2f headerPos = new Vector2f((viewBounds.width / 2) - (headerBounds.width / 2), viewBounds.height / 5f);

        menuHeader.setPosition(headerPos);
    }

    private void createExitText() {
        exitText = new Text();

        exitText.setString("Press ESC to go back");

        exitText.setFont(fontManager.get("font"));

        exitText.setPosition(0, 0);

        exitText.setCharacterSize(50);

        exitText.setColor(Color.WHITE);
    }

    private void createHighScoreList() {
        Vector2f headerPos = menuHeader.getPosition();

        List<HighScore> highScoreData = highScores.getHighScores();

        highScoreList = new InterfaceList(game, fontManager.get("font"), new Vector2f(headerPos.x, headerPos.y + LIST_PADDING));

        for (int i = 0; i < highScoreData.size(); i++) {
            HighScore score = highScoreData.get(i);

            highScoreList.addListOption(String.format("%d %s - %s", i + 1, score.getPlayerName(), score.getScore()), (() -> {
            }));
        }
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        pressedKey = keyevent.key;
    }
}
