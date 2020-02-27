package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.HighScores;
import uk.ac.lancaster.scc210.game.gui.EscapeText;
import uk.ac.lancaster.scc210.game.gui.MenuHeader;
import uk.ac.lancaster.scc210.game.resources.HighScore;

import java.util.List;

public class HighScoreList implements State {
    private final int LIST_PADDING = 250;

    private StateBasedGame game;

    private FontManager fontManager;

    private HighScores highScores;

    private InterfaceList highScoreList;

    private MenuHeader menuHeader;

    private EscapeText escapeText;

    /**
     * Setup for the current state (like a constructor).
     *
     * @param game the game
     */
    @Override
    public void setup(StateBasedGame game) {
        this.game = game;

        fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        highScores = (HighScores) game.getServiceProvider().get(HighScores.class);

        FloatRect viewBounds = ((ViewSize) game.getServiceProvider().get(ViewSize.class)).getViewBounds();

        menuHeader = new MenuHeader("High Scores", fontManager, viewBounds);

        escapeText = new EscapeText(fontManager, game);

        createHighScoreList();
    }

    @Override
    public void onEnter(StateBasedGame game) {
        game.addKeyListener(escapeText);
    }

    @Override
    public void onExit(StateBasedGame game) {
        game.removeKeyListener(escapeText);
    }

    @Override
    public void update(Time deltaTime) {
        escapeText.update();
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(menuHeader);

        target.draw(escapeText);

        target.draw(highScoreList);
    }

    private void createHighScoreList() {
        Vector2f headerPos = menuHeader.getPosition();

        List<HighScore> highScoreData = highScores.getHighScores();

        highScoreList = new InterfaceList(game, fontManager.get("font"), new Vector2f(headerPos.x, headerPos.y + LIST_PADDING));

        game.removeKeyListener(highScoreList);

        for (int i = 0; i < highScoreData.size(); i++) {
            HighScore score = highScoreData.get(i);

            highScoreList.addListOption(String.format("%d %s - %s", i + 1, score.getPlayerName(), score.getScore()), (() -> {
            }));
        }
    }
}
