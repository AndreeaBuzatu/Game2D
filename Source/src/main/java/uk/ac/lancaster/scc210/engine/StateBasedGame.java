package uk.ac.lancaster.scc210.engine;

import org.jsfml.graphics.RenderWindow;
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
    private static final int FPS = 60;

    private final Queue<State> states;

    /**
     * The Window which is presented to the player.
     */
    protected final RenderWindow window;

    //private final View view;

    /**
     * The Service provider. Used to access services throughout the game
     */
    protected final ServiceProvider serviceProvider;

    private TextureAtlasDeserialiser textureAtlasDeserialiser;

    private AnimationDeserialiser animationDeserialiser;

    private State currentState;

    private Event event;

    /**
     * Instantiates a new State based game.
     *
     * @param name         the name of the game. Place onto the window title bar
     * @param windowWidth  the window width
     * @param windowHeight the window height
     * @param state        the default starting state of the game
     */
    protected StateBasedGame(final String name, final int windowWidth, final int windowHeight, final State state) {
        this.currentState = state;

        window = new RenderWindow(new VideoMode(windowWidth, windowHeight), name);

        //view = new View();

        //window.setView(view);

        window.setFramerateLimit(FPS);

        states = new LinkedList<>();

        states.add(currentState);

        serviceProvider = new ServiceProvider();

        try {
            textureAtlasDeserialiser = new TextureAtlasDeserialiser(deserialiseXML("atlases.xml"));

            TextureAtlasManager atlasManager = new TextureAtlasManager(textureAtlasDeserialiser.getSerialised());

            animationDeserialiser = new AnimationDeserialiser(atlasManager, deserialiseXML("animations.xml"));

            TextureAnimationManager textureAnimationManager = new TextureAnimationManager(animationDeserialiser.getSerialised());

            serviceProvider.put(TextureAnimationManager.class, textureAnimationManager);

            TextureManager textureManager = new TextureManager(textureAtlasDeserialiser.getSerialised());

            serviceProvider.put(TextureManager.class, textureManager);

        } catch (ResourceNotFoundException e) {
            window.close();
        }

        serviceProvider.put(WindowSize.class, new WindowSize(windowWidth, windowHeight));
    }

    /**
     * Run the game.
     */
    public void run() {
        currentState.setup(this);

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

        currentState.update();
    }

    private void draw() {
        window.clear();

        currentState.draw(window);

        window.display();
    }

    private void addState(State state) {
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
}
