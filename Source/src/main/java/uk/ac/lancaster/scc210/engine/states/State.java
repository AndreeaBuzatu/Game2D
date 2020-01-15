package uk.ac.lancaster.scc210.engine.states;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.StateBasedGame;

public interface State {
    void setup(StateBasedGame game);

    void draw(RenderTarget target);

    void update();
}
