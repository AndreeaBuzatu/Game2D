package uk.ac.lancaster.scc210.states;

import org.jsfml.graphics.RenderTarget;

public interface State {
    void draw(RenderTarget target);

    void update();
}
