package uk.ac.lancaster.scc210.engine.states;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.game.Game;

public interface State {
    void setup(Game game);

    void draw(RenderTarget target);

    void update();
}
