package uk.ac.lancaster.scc210;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

class Game {
    private final String TITLE = "Shooter";

    private final int WIDTH = 500;

    private final int HEIGHT = 500;

    private RenderWindow window;

    private Event event;

    Game() {
        window = new RenderWindow(new VideoMode(WIDTH, HEIGHT), TITLE);

        window.setFramerateLimit(60);
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
        }
    }

    private void draw() {
    }
}
