package uk.ac.lancaster.scc210;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class Main {
    private final int WIDTH = 500;

    private final int HEIGHT = 500;

    private RenderWindow window;

    private Main() {
        window = new RenderWindow(new VideoMode(WIDTH, HEIGHT), "Game");

        window.setFramerateLimit(60);

        Event event;

        while (window.isOpen()) {
            while ((event = window.pollEvent()) != null) {
                if (event.type == Event.Type.CLOSED) {
                    window.close();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
