package uk.ac.lancaster.scc210.game.states;

import org.jsfml.audio.Music;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.MusicManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.HighScores;
import uk.ac.lancaster.scc210.game.gui.EscapeText;
import uk.ac.lancaster.scc210.game.gui.MenuHeader;
import uk.ac.lancaster.scc210.game.resources.HighScore;

import java.util.List;

/**
 * The type High score list.
 */
public class HighScoreList implements State {
    private final int LIST_PADDING = 250;

    private StateBasedGame game;

    private FontManager fontManager;

    private HighScores highScores;

    private InterfaceList highScoreList;

    private MenuHeader menuHeader;

    private EscapeText escapeText;

    private Music music;

    private Sprite background;

    /**
     * Setup for the current state (like a constructor).
     *
     * @param game the game
     */
    @Override
    public void setup(StateBasedGame game) {
        this.game = game;

        MusicManager musicManager = (MusicManager) game.getServiceProvider().get(MusicManager.class);

        fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        highScores = (HighScores) game.getServiceProvider().get(HighScores.class);

        FloatRect viewBounds = ((ViewSize) game.getServiceProvider().get(ViewSize.class)).getViewBounds();

        menuHeader = new MenuHeader("High: Scores", fontManager, viewBounds);

        escapeText = new EscapeText(fontManager, game);

        music = musicManager.get("menu_music");

        TextureManager textureManager = (TextureManager) game.getServiceProvider().get(TextureManager.class);

        background = new Sprite(textureManager.get("level-select.jpg:level-select"));

        background.setScale(2, 2);

        createHighScoreList();
    }

    @Override
    public void onEnter(StateBasedGame game) {
        game.addKeyListener(escapeText);

        music.play();
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
        target.draw(background);

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
