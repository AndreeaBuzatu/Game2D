package uk.ac.lancaster.scc210.game.states;

import org.jsfml.audio.Music;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.MusicManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.StateManager;
import uk.ac.lancaster.scc210.game.gui.MenuHeader;

/**
 * The type Pause menu.
 */
public class PauseMenu implements State {
    private final int LIST_PADDING = 250;

    private InterfaceList interfaceList;

    private MenuHeader menuHeader;

    private Music music;

    /**
     * Setup for the current state (like a constructor).
     *
     * @param game the game
     */
    @Override
    public void setup(StateBasedGame game) {
        StateManager stateManager = (StateManager) game.getServiceProvider().get(StateManager.class);

        FontManager fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        MusicManager musicManager = (MusicManager) game.getServiceProvider().get(MusicManager.class);

        FloatRect viewBounds = ((ViewSize) game.getServiceProvider().get(ViewSize.class)).getViewBounds();

        music = musicManager.get("example");

        menuHeader = new MenuHeader("Paused", fontManager, viewBounds);

        Vector2f headerPos = menuHeader.getPosition();

        interfaceList = new InterfaceList(game, fontManager.get("font"), new Vector2f(headerPos.x, headerPos.y + LIST_PADDING));

        interfaceList.addListOption("Resume", game::popState);

        interfaceList.addListOption("Help", (() -> game.pushState(stateManager.get("help"))));

        interfaceList.addListOption("Main Menu", () -> {
            game.popState();

            game.popState();

            music.pause();
        });

        interfaceList.setEnabled(true);
    }

    @Override
    public void onEnter(StateBasedGame game) {
        game.addKeyListener(interfaceList);

        music.play();
    }

    @Override
    public void onExit(StateBasedGame game) {
        game.removeKeyListener(interfaceList);
    }

    /**
     * Update the current state of the screen.
     *
     * @param deltaTime the delta time
     */
    @Override
    public void update(Time deltaTime) {
        interfaceList.update();
    }

    /**
     * Draw the current state to the screen.
     *
     * @param target the target usually RenderWindow
     */
    @Override
    public void draw(RenderTarget target) {
        target.draw(menuHeader);

        target.draw(interfaceList);
    }
}
