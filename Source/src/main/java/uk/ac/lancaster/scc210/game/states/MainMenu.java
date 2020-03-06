package uk.ac.lancaster.scc210.game.states;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
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

        TextureManager textureManager = (TextureManager) game.getServiceProvider().get(TextureManager.class);

        ViewSize viewSize = (ViewSize) game.getServiceProvider().get(ViewSize.class);

        FloatRect viewBounds = viewSize.getViewBounds();

        levelManager = (LevelManager) game.getServiceProvider().get(LevelManager.class);

        playerData = (PlayerData) game.getServiceProvider().get(PlayerData.class);

        menuHeader = new MenuHeader("Mission: Survival", fontManager, viewBounds);

        Vector2f headerPos = menuHeader.getPosition();

        interfaceList = new InterfaceList(game, fontManager.get("font"), new Vector2f(headerPos.x, headerPos.y + InterfaceList.LIST_PADDING));

        // For play create a new playing state so we can easily reset the game
        interfaceList.addListOption("Play", (() -> game.pushState(new Playing(getUnlockedLevel()))));

        interfaceList.addListOption("Level Select", (() -> game.pushState(stateManager.get("level-select"))));

        interfaceList.addListOption("High Scores", (() -> game.pushState(stateManager.get("high-score-list"))));

        interfaceList.addListOption("Help", (() -> game.pushState(stateManager.get("help"))));

        interfaceList.addListOption("Exit", (game::popState));

        interfaceList.setEnabled(true);

        MusicManager musicManager = (MusicManager) game.getServiceProvider().get(MusicManager.class);

        music = musicManager.get("menu_music");

        music.setVolume(100);

        music.setLoop(true);

        background = new Sprite(textureManager.get("level-select.jpg:level-select"));

        background.setScale(2, 2);
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

        target.draw(menuHeader);

        target.draw(interfaceList);
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
