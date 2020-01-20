package uk.ac.lancaster.scc210.game;

import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.game.content.SpaceShipManager;
import uk.ac.lancaster.scc210.game.resources.SpaceShipDeserialiser;
import uk.ac.lancaster.scc210.game.states.Level;

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
        super("Shooter", 500, 500, new Level());

        try {
            TextureManager textureManager = (TextureManager) serviceProvider.get(TextureManager.class);

            TextureAnimationManager animationManager = (TextureAnimationManager) serviceProvider.get(TextureAnimationManager.class);

            SpaceShipDeserialiser spaceShipDeserialiser = new SpaceShipDeserialiser(deserialiseXML("spaceships.xml"));

            serviceProvider.put(SpaceShipManager.class, new SpaceShipManager(animationManager, spaceShipDeserialiser.getSerialised()));

        } catch (ResourceNotFoundException e) {
            window.close();
        }
    }
}
