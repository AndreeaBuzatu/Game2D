package uk.ac.lancaster.scc210.engine;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import uk.ac.lancaster.scc210.engine.states.State;

import java.util.LinkedList;
import java.util.Queue;

public class StateBasedGame {
    private final int FPS = 60;

    private final Queue<State> states;

    protected final RenderWindow window;

    private final View view;

    private State currentState;

    private Event event;

    protected StateBasedGame(final String name, final int windowWidth, final int windowHeight, final State state) {
        window = new RenderWindow(new VideoMode(windowWidth, windowHeight), name);

        view = new View();

        window.setView(view);

        window.setFramerateLimit(FPS);

        states = new LinkedList<>();

        this.currentState = state;

        states.add(currentState);

        currentState.setup(this);
    }

    public void run() {
        while (window.isOpen()) {
            update();

            currentState.draw(window);

            draw();
        }
    }

    private void update() {
        while ((event = window.pollEvent()) != null) {
            if (event.type == Event.Type.CLOSED) {
                window.close();
            }

            if (event.type == Event.Type.KEY_PRESSED) {
                if (event.asKeyEvent().key == Keyboard.Key.ESCAPE) {
                    window.close();
                }
            }

            currentState.update();
        }
    }

    private void draw() {
        window.clear();

        window.display();
    }

    private void addState(State state) {
        states.add(state);

        currentState = states.peek();
    }
}
