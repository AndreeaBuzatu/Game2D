package uk.ac.lancaster.scc210.engine;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import org.w3c.dom.Document;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.engine.content.TextureAtlasManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.resources.ResourceLoader;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.XMLAdapter;
import uk.ac.lancaster.scc210.engine.resources.deserialise.AnimationDeserialiser;
import uk.ac.lancaster.scc210.engine.resources.deserialise.TextureAtlasDeserialiser;
import uk.ac.lancaster.scc210.engine.service.ServiceProvider;
import uk.ac.lancaster.scc210.engine.states.State;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Contains the basic functionality for a state based game.
 */
public class StateBasedGame {
    public static final int FPS = 60;

    private final float ZOOM_AMOUNT = 2.0f;

    private final Queue<State> states;

    /**
     * The Window which is presented to the player.
     */
    protected final RenderWindow window;

    private final View view;

    private final int windowWidth, windowHeight;

    /**
     * The Service provider. Used to access services throughout the game
     */
    protected final ServiceProvider serviceProvider;

    private final ViewSize viewSize;

    private State currentState;

    private Event event;

    private Clock clock;

    private Time deltaTime, elapsedTime;

    /**
     * Instantiates a new State based game.
     *
     * @param name         the name of the game. Place onto the window title bar
     * @param windowWidth  the window width
     * @param windowHeight the window height
     * @param state        the default starting state of the game
     */
    protected StateBasedGame(final String name, final int windowWidth, final int windowHeight, final State state) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.currentState = state;

        window = new RenderWindow(new VideoMode(windowWidth, windowHeight), name);

        // Set the centre of the view to (0, 0) window by finding half the width of the window width and height
        view = new View(new Vector2f((windowWidth >> 1) * ZOOM_AMOUNT, (windowHeight >> 1) * ZOOM_AMOUNT),
                new Vector2f(windowWidth, windowHeight));

        view.zoom(ZOOM_AMOUNT);

        window.setView(view);

        window.setFramerateLimit(FPS);

        states = new LinkedList<>();

        states.add(currentState);

        serviceProvider = new ServiceProvider();

        try {
            TextureAtlasDeserialiser textureAtlasDeserialiser = new TextureAtlasDeserialiser(deserialiseXML("atlases.xml"));

            TextureAtlasManager atlasManager = new TextureAtlasManager(textureAtlasDeserialiser.getSerialised());

            AnimationDeserialiser animationDeserialiser = new AnimationDeserialiser(atlasManager, deserialiseXML("animations.xml"));

            TextureAnimationManager textureAnimationManager = new TextureAnimationManager(animationDeserialiser.getSerialised());

            serviceProvider.put(TextureAnimationManager.class, textureAnimationManager);

            TextureManager textureManager = new TextureManager(textureAtlasDeserialiser.getSerialised());

            serviceProvider.put(TextureManager.class, textureManager);

        } catch (ResourceNotFoundException e) {
            window.close();
        }

        Vector2f viewCentre = view.getCenter();

        Vector2f viewSize = view.getSize();

        this.viewSize = new ViewSize(new FloatRect(viewCentre.x - viewSize.x / 2,
                viewCentre.y - viewSize.y / 2, viewSize.x, viewSize.y));

        serviceProvider.put(ViewSize.class, this.viewSize);

        clock = new Clock();

        elapsedTime = Time.ZERO;
    }

    /**
     * Run the game.
     */
    public void run() {
        states.forEach(state -> state.setup(this));

        while (window.isOpen()) {
            update();

            draw();
        }
    }

    private void update() {
        // Get the elapsed time and restart the clock
        deltaTime = clock.restart();

        elapsedTime = Time.add(elapsedTime, deltaTime);

        while ((event = window.pollEvent()) != null) {
            switch (event.type) {
                case CLOSED:
                    window.close();

                    break;

                case KEY_PRESSED:
                    if (event.asKeyEvent().key == Keyboard.Key.ESCAPE) {
                        window.close();
                    }

                    break;

                case RESIZED:
                    float aspectRatio = (float) window.getSize().x / window.getSize().y;

                    view.setSize(windowWidth * aspectRatio, windowHeight * aspectRatio);

                    break;

                default:
                    break;
            }
        }

        if (currentState.complete()) {
            states.remove();

            State state = states.peek();

            if (state != null) {
                currentState = states.peek();
            }
        }

        currentState.update();
    }

    private void draw() {
        window.clear();

        currentState.draw(window);

        window.display();
    }

    protected void addState(State state) {
        states.add(state);

        currentState = states.peek();
    }

    /**
     * Deserialise xml document from a given file name.
     *
     * @param fileName the file name
     * @return the Document created from the fileName
     */
    protected Document deserialiseXML(final String fileName) {
        XMLAdapter xmlAdapter = new XMLAdapter();

        try {
            ResourceLoader.loadFromFile(xmlAdapter, fileName);

            return xmlAdapter.getResource();

        } catch (ResourceNotFoundException e) {
            window.close();
        }

        return null;
    }

    /**
     * Gets service provider.
     *
     * @return the service provider
     */
    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    private Vector2f setViewSize() {
        return new Vector2f((windowWidth >> 1) * ZOOM_AMOUNT, (windowHeight >> 1) * ZOOM_AMOUNT);
    }
}
