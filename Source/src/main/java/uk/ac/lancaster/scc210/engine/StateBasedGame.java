package uk.ac.lancaster.scc210.engine;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shader;
import org.jsfml.graphics.View;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import org.w3c.dom.Document;
import uk.ac.lancaster.scc210.engine.content.*;
import uk.ac.lancaster.scc210.engine.resources.ResourceLoader;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.XMLAdapter;
import uk.ac.lancaster.scc210.engine.resources.deserialise.AnimationDeserialiser;
import uk.ac.lancaster.scc210.engine.resources.deserialise.MusicDeserialiser;
import uk.ac.lancaster.scc210.engine.resources.deserialise.SoundDeserialiser;
import uk.ac.lancaster.scc210.engine.resources.deserialise.TextureAtlasDeserialiser;
import uk.ac.lancaster.scc210.engine.service.ServiceProvider;
import uk.ac.lancaster.scc210.engine.states.State;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains the basic functionality for a state based game.
 */
public class StateBasedGame {
    /**
     * The constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * The constant ZOOM_AMOUNT.
     */
    public static final float ZOOM_AMOUNT = 2.0f;

    private final Set<InputListener> inputListeners;

    private final Stack<State> states;

    private final View view;

    private final Clock clock;

    private State currentState;

    private Event event;

    private Time deltaTime, elapsedTime;

    private final int FPS = 60;

    private final int windowWidth, windowHeight;

    /**
     * The Window which is presented to the player.
     */
    protected final RenderWindow window;

    /**
     * The Service provider. Used to access services throughout the game
     */
    protected final ServiceProvider serviceProvider;

    /**
     * Instantiates a new State based game.
     *
     * @param name         the name of the game. Place onto the window title bar
     * @param windowWidth  the window width
     * @param windowHeight the window height
     */
    protected StateBasedGame(final String name, final int windowWidth, final int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        // Only load the shared object on windows
//        if (System.getProperty("os.name").toLowerCase().contains("nux")) {
//            System.load(Objects.requireNonNull(StateBasedGame.class.getClassLoader().getResource("libfixxinit.so")).getFile());
//        }

        window = new RenderWindow(new VideoMode(windowWidth, windowHeight), name);

        // Set the centre of the view to (0, 0) window by finding half the width of the window width and height
        view = new View(new Vector2f((windowWidth >> 1) * ZOOM_AMOUNT, (windowHeight >> 1) * ZOOM_AMOUNT),
                new Vector2f(windowWidth, windowHeight));

        view.zoom(ZOOM_AMOUNT);

        window.setView(view);

        window.setFramerateLimit(FPS);

        window.setKeyRepeatEnabled(false);

        window.setMouseCursorVisible(false);

        states = new Stack<>();

        serviceProvider = new ServiceProvider();

        inputListeners = new HashSet<>();

        try {
            TextureAtlasDeserialiser textureAtlasDeserialiser = new TextureAtlasDeserialiser(deserialiseXML("atlases.xml"));

            TextureAtlasManager atlasManager = new TextureAtlasManager(textureAtlasDeserialiser.getSerialised());

            AnimationDeserialiser animationDeserialiser = new AnimationDeserialiser(atlasManager, deserialiseXML("animations.xml"));

            TextureAnimationManager textureAnimationManager = new TextureAnimationManager(animationDeserialiser.getSerialised());

            serviceProvider.put(TextureAnimationManager.class, textureAnimationManager);

            TextureManager textureManager = new TextureManager(textureAtlasDeserialiser.getSerialised());

            serviceProvider.put(TextureManager.class, textureManager);

            MusicDeserialiser musicDeserialiser = new MusicDeserialiser(deserialiseXML("music.xml"));

            MusicManager musicManager = new MusicManager(musicDeserialiser.getSerialised());

            serviceProvider.put(MusicManager.class, musicManager);

            SoundDeserialiser soundDeserialiser = new SoundDeserialiser(deserialiseXML("sounds.xml"));

            SoundBufferManager soundBufferManager = new SoundBufferManager(soundDeserialiser.getSerialised());

            serviceProvider.put(SoundBufferManager.class, soundBufferManager);

            serviceProvider.put(SoundManager.class, new SoundManager(soundBufferManager));

            if (Shader.isAvailable()) {
                ShaderManager shaderManager = new ShaderManager();

                serviceProvider.put(ShaderManager.class, shaderManager);

            } else {
                LOGGER.log(Level.INFO, "This PC does not support shaders. None will appear");
            }

            FontManager fontManager = new FontManager();

            serviceProvider.put(FontManager.class, fontManager);

        } catch (ResourceNotFoundException e) {
            window.close();
        }

        Vector2f viewCentre = view.getCenter();

        ViewSize viewSize = new ViewSize(new FloatRect(viewCentre.x - view.getSize().x / 2,
                viewCentre.y - view.getSize().y / 2, view.getSize().x, view.getSize().y));

        serviceProvider.put(ViewSize.class, viewSize);

        clock = new Clock();

        elapsedTime = Time.ZERO;
    }

    /**
     * Run the game.
     */
    public void run() {
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
                    inputListeners.forEach(listener -> listener.keyPressed(event.asKeyEvent()));

                    break;

                case TEXT_ENTERED:
                    inputListeners.forEach(listener -> {
                        if (listener != null) {
                            listener.keyTyped(event.asTextEvent());
                        }
                    });

                    break;

                case RESIZED:
                    float aspectRatio = (float) window.getSize().x / window.getSize().y;

                    view.setSize(windowWidth * aspectRatio, windowHeight * aspectRatio);

                    break;

                default:
                    break;
            }
        }

        currentState.update(deltaTime);
    }

    private void draw() {
        window.clear();

        currentState.draw(window);

        window.display();
    }

    /**
     * Push state.
     *
     * @param state the state
     */
    public void pushState(State state) {
        if (currentState != null) {
            currentState.onExit(this);
        }

        state.setup(this);

        states.add(state);

        currentState = states.peek();

        currentState.onEnter(this);
    }

    /**
     * Pop state.
     */
    public void popState() {
        states.pop();

        try {
            currentState.onExit(this);

            currentState = states.peek();

            currentState.onEnter(this);

        } catch (EmptyStackException e) {
            window.close();
        }
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
            ResourceLoader.loadFromStream(xmlAdapter, fileName);

            return xmlAdapter.getResource();

        } catch (ResourceNotFoundException e) {
            window.close();
        }

        return null;
    }

    /**
     * Deserialise local xml document.
     *
     * @param fileName the file name
     * @return the document
     * @throws ResourceNotFoundException the resource not found exception
     */
    protected Document deserialiseLocalXML(final String fileName) throws ResourceNotFoundException {
        XMLAdapter xmlAdapter = new XMLAdapter();

        ResourceLoader.loadFromLocalStream(xmlAdapter, fileName);

        return xmlAdapter.getResource();
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

    /**
     * Add key listener.
     *
     * @param inputListener the input listener
     */
    public void addKeyListener(InputListener inputListener) {
        inputListeners.add(inputListener);
    }

    /**
     * Remove key listener.
     *
     * @param inputListener the input listener
     */
    public void removeKeyListener(InputListener inputListener) {
        inputListeners.remove(inputListener);
    }

    /**
     * Gets states.
     *
     * @return the states
     */
    public Stack<State> getStates() {
        return states;
    }
}
