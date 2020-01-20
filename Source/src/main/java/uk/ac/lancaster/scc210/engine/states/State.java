package uk.ac.lancaster.scc210.engine.states;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.StateBasedGame;

/**
 * The interface State.
 */
public interface State {
    /**
     * Sets .
     *
     * @param game the game
     */
    void setup(StateBasedGame game);

    /**
     * Draw.
     *
     * @param target the target
     */
    void draw(RenderTarget target);

    /**
     * Update.
     */
    void update();
}
