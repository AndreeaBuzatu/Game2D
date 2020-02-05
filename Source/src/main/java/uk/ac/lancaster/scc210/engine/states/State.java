package uk.ac.lancaster.scc210.engine.states;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.StateBasedGame;

/**
 * Interface used to outline a game state (e.g. main menu, the game itself, high score screen).
 */
public interface State {
    /**
     * Setup for the current state (like a constructor).
     *
     * @param game the game
     */
    void setup(StateBasedGame game);

    /**
     * Update the current state of the screen.
     */
    void update();

    /**
     * Draw the current state to the screen.
     *
     * @param target the target usually RenderWindow
     * @param deltaTime the delta time
     */
    void draw(RenderTarget target, Time deltaTime);

    /**
     * Indicates that the current state is complete. The game will transition to the next state.
     *
     * @return if the state is complete or not
     */
    boolean complete();
}
