package uk.ac.lancaster.scc210.game.states;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.TextEvent;
import uk.ac.lancaster.scc210.engine.InputListener;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.MusicManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceGrid;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.LevelManager;
import uk.ac.lancaster.scc210.game.gui.EscapeText;
import uk.ac.lancaster.scc210.game.gui.MenuHeader;
import uk.ac.lancaster.scc210.game.level.Level;
import uk.ac.lancaster.scc210.game.resources.PlayerData;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Level select.
 */
public class LevelSelect implements State, InputListener {
    private StateBasedGame game;

    private FontManager fontManager;

    private LevelManager levelManager;

    private EscapeText escapeText;

    private MenuHeader menuHeader;

    private Music music;

    private Sprite background;

    private FloatRect viewBounds;

    private InterfaceGrid interfaceGrid;

    private Keyboard.Key pressedKey;

    private int currentUnlocked;

    @Override
    public void setup(StateBasedGame game) {
        this.game = game;

        game.addKeyListener(this);

        MusicManager musicManager = (MusicManager) game.getServiceProvider().get(MusicManager.class);

        fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        levelManager = (LevelManager) game.getServiceProvider().get(LevelManager.class);

        viewBounds = ((ViewSize) game.getServiceProvider().get(ViewSize.class)).getViewBounds();

        escapeText = new EscapeText(fontManager, game);

        menuHeader = new MenuHeader("Level: Select", fontManager, viewBounds);

        PlayerData playerData = (PlayerData) game.getServiceProvider().get(PlayerData.class);

        TextureManager textureManager = (TextureManager) game.getServiceProvider().get(TextureManager.class);

        currentUnlocked = levelManager.indexOf(playerData.getUnlockedLevel());

        music = musicManager.get("menu_music");

        // If the level can't be found, default to the first level
        if (currentUnlocked < 0) {
            currentUnlocked = levelManager.indexOf(levelManager.getLevelList().get(0).getName());
        }

        background = new Sprite(textureManager.get("level-select.jpg:level-select"));

        background.setScale(2, 2);

        createGrid();
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

    private void createGrid() {
        Vector2f headerPos = menuHeader.getPosition();

        interfaceGrid = new InterfaceGrid(game, new Vector2f(headerPos.x, headerPos.y + InterfaceList.LIST_PADDING));

        List<Level> levels = new ArrayList<>(levelManager.getLevelList());

        InterfaceList interfaceList = null;

        for (int i = 0; i < levels.size(); i++) {
            if (i % 3 == 0) {
                // i % 3 == 0, will == true when i = 0, so in that instance don't add a new column (as one hasn't been created yet)
                if (i > 0) {
                    interfaceGrid.addColumn(interfaceList);
                }

                interfaceList = new InterfaceList(game, fontManager.get("font"), Vector2f.ZERO);
            }

            int finalI = i;

            interfaceList.addListOption(levels.get(i).getName(), (() -> {
                // Pop back to the main menu so that the pause screen can take them back to the main menu
                game.popState();

                game.pushState(new Playing(levels.get(finalI).getName()));
            }));

            if (i > currentUnlocked) {
                interfaceList.getOptions().get(i % 3).setEnabled(false);
            }
        }

        interfaceGrid.addColumn(interfaceList);
    }

    @Override
    public void update(Time deltaTime) {
        interfaceGrid.update();

        if (game != null && pressedKey != null && pressedKey == Keyboard.Key.ESCAPE) {
            game.popState();
        }
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(background);

        target.draw(escapeText);

        target.draw(menuHeader);

        target.draw(interfaceGrid);
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        pressedKey = keyevent.key;
    }

    @Override
    public void keyTyped(TextEvent textevent) {

    }
}
