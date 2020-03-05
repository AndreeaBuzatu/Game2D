package uk.ac.lancaster.scc210.game.states;

import org.jsfml.audio.Music;
import org.jsfml.audio.SoundSource;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.*;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.MusicManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.LevelManager;
import uk.ac.lancaster.scc210.game.content.StateManager;
import uk.ac.lancaster.scc210.game.gui.MenuHeader;
import uk.ac.lancaster.scc210.game.resources.PlayerData;

/**
 * The type Main menu.
 */
public class MainMenu implements State {
    private final int LIST_PADDING = 250;

    private LevelManager levelManager;

    private PlayerData playerData;

    private InterfaceList interfaceList;

    private MenuHeader menuHeader;

    private Music music;

    private Sprite background;

    @Override
    public void setup(StateBasedGame game) {
        StateManager stateManager = (StateManager) game.getServiceProvider().get(StateManager.class);

        FontManager fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        ViewSize viewSize = (ViewSize) game.getServiceProvider().get(ViewSize.class);

        FloatRect viewBounds = viewSize.getViewBounds();

        levelManager = (LevelManager) game.getServiceProvider().get(LevelManager.class);

        playerData = (PlayerData) game.getServiceProvider().get(PlayerData.class);

        menuHeader = new MenuHeader("We Don't Have A Name", fontManager, viewBounds);

        Vector2f headerPos = menuHeader.getPosition();

        interfaceList = new InterfaceList(game, fontManager.get("font"), new Vector2f(headerPos.x, headerPos.y + LIST_PADDING));

        // For play create a new playing state so we can easily reset the game
        interfaceList.addListOption("Play", (() -> game.pushState(new Playing(getUnlockedLevel()))));

        interfaceList.addListOption("Level Select", (() -> game.pushState(stateManager.get("level-select"))));

        interfaceList.addListOption("High Scores", (() -> game.pushState(stateManager.get("high-score-list"))));

        interfaceList.addListOption("Leaderboard", (() -> game.pushState(leaderboardSelect)));

        interfaceList.addListOption("Help", (() -> game.pushState(help)));

        interfaceList.addListOption("Exit", (game::popState));

        interfaceList.setEnabled(true);

        MusicManager musicManager = (MusicManager) game.getServiceProvider().get(MusicManager.class);

        music = musicManager.get("menu_music");

        music.setVolume(100);

        music.setLoop(true);
    }

    @Override
    public void onEnter(StateBasedGame game) {
        game.addKeyListener(interfaceList);

        if (music.getStatus().equals(Music.Status.PAUSED) || music.getStatus().equals(Music.Status.STOPPED)) {
            music.play();
        }
    }

    @Override
    public void onExit(StateBasedGame game) {
        game.removeKeyListener(interfaceList);

        music.pause();
    }

    @Override
    public void update(Time deltaTime) {
        interfaceList.update();
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(background);

        target.draw(interfaceList);

        target.draw(titleHeader);

        target.draw(subtitleHeader);
    }

    private String getUnlockedLevel() {
        String unlockedLevel = playerData.getUnlockedLevel();

        int currentUnlocked = levelManager.indexOf(unlockedLevel);

        // If the level can't be found, default to the first level
        if (currentUnlocked < 0) {
            unlockedLevel = levelManager.getLevelList().get(0).getName();

            currentUnlocked = levelManager.indexOf(unlockedLevel);

            unlockedLevel = levelManager.getLevelList().get(currentUnlocked).getName();
        }

        return unlockedLevel;
    }
}
