package uk.ac.lancaster.scc210.game;

import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.game.content.LevelManager;
import uk.ac.lancaster.scc210.game.content.SpaceShipManager;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;
import uk.ac.lancaster.scc210.game.resources.LevelDeserialiser;
import uk.ac.lancaster.scc210.game.resources.SpaceShipDeserialiser;
import uk.ac.lancaster.scc210.game.states.Playing;

import java.util.logging.Logger;

/**
 * The type Game.
 */
public class Game extends StateBasedGame {
    /**
     * The constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Instantiates a new Game.
     */
    public Game() {
        super("Shooter", 1280, 720, new Playing());

        try {
            TextureManager textureManager = (TextureManager) serviceProvider.get(TextureManager.class);

            TextureAnimationManager animationManager = (TextureAnimationManager) serviceProvider.get(TextureAnimationManager.class);

            SpaceShipDeserialiser spaceShipDeserialiser = new SpaceShipDeserialiser(deserialiseXML("spaceships.xml"));

            SpaceShipManager spaceShipManager = new SpaceShipManager(animationManager, spaceShipDeserialiser.getSerialised());

            serviceProvider.put(SpaceShipManager.class, spaceShipManager);

            LevelDeserialiser levelDeserialiser = new LevelDeserialiser(spaceShipManager, deserialiseXML("levels.xml"));

            LevelManager levelManager = new LevelManager(levelDeserialiser.getSerialised());

            serviceProvider.put(LevelManager.class, levelManager);

        } catch (ResourceNotFoundException e) {
            window.close();
        }

        serviceProvider.put(BulletPool.class, new BulletPool(serviceProvider));
    }
}
