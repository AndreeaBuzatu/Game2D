package uk.ac.lancaster.scc210;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import uk.ac.lancaster.scc210.content.TextureManager;
import uk.ac.lancaster.scc210.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.resources.ResourceProvider;
import uk.ac.lancaster.scc210.states.Level;
import uk.ac.lancaster.scc210.states.State;

import java.util.logging.Logger;

public class Game {
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final String TITLE = "Shooter";

    private final int WIDTH = 500;

    private final int HEIGHT = 500;

    private final int FPS = 60;

    private RenderWindow window;

    private ResourceProvider resourceProvider;

    private View view;

    private State currentState;

    private Event event;

    Game() {
        window = new RenderWindow(new VideoMode(WIDTH, HEIGHT), TITLE);

        view = new View();

        currentState = new Level();

        window.setView(view);

        window.setFramerateLimit(FPS);

        try {
            resourceProvider = new ResourceProvider();

        } catch (ResourceNotFoundException e) {
            window.close();
        }

        currentState.setup(this);
    }

    void run() {
        while (window.isOpen()) {
            update();

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

        currentState.draw(window);

        window.display();
    }

    public TextureManager getTextureManager() {
        return resourceProvider.getTextureManager();
    }
}
