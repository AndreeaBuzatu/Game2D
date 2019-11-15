package uk.ac.lancaster.game;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import uk.ac.lancaster.game.screens.mainmenu.MainMenu;
import uk.ac.lancaster.game.screens.Screen;

public class Game {
    public static final int WINDOW_WIDTH = 500;

    public static final int WINDOW_HEIGHT = 500;

    private final int FRAME_RATE = 60;

    private final VideoMode VIDEO_MODE = new VideoMode(WINDOW_WIDTH, WINDOW_HEIGHT);

    private final String WINDOW_TITLE = "Shoot 'Em Up";

    private RenderWindow window;

    private Screen currentScreen;

    // Declare as an instance variable to avoid creating a new variable every call of update()
    private Event windowEvent;

    Game() {
        window = new RenderWindow();

        window.setFramerateLimit(FRAME_RATE);

        window.create(VIDEO_MODE, WINDOW_TITLE);

        currentScreen = new MainMenu();
    }

    void run() {
        window.create(VIDEO_MODE, WINDOW_TITLE);

        while (window.isOpen()) {
            update();

            draw();
        }
    }

    private void update() {
        while ((windowEvent = window.pollEvent()) != null) {
            if (windowEvent.type == Event.Type.CLOSED) {
                window.close();
            }

            currentScreen.handleEvents(windowEvent);
        }

        if (currentScreen.isFinished()) {
            if (currentScreen.nextScreen() != null) {
                currentScreen = currentScreen.nextScreen();

            } else {
                window.close();
            }
        }

        currentScreen.update();
    }

    private void draw() {
        window.clear();

        currentScreen.draw(window);

        window.display();
    }
}
