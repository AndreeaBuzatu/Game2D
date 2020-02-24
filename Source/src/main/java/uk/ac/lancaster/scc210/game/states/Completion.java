package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.states.State;

public class Completion implements State {
    @Override
    public void setup(StateBasedGame game) {
    }

    @Override
    public void update(Time deltaTime) {
        System.out.println("Player has won!!!!!");
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
