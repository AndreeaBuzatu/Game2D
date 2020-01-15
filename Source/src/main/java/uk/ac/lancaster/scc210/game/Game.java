package uk.ac.lancaster.scc210.game;

import org.jsfml.window.event.Event;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.game.content.SpaceShipManager;
import uk.ac.lancaster.scc210.game.resources.DeserialiserProvider;
import uk.ac.lancaster.scc210.game.states.Level;

import java.util.logging.Logger;

public class Game extends StateBasedGame {
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private DeserialiserProvider deserialiserProvider;

    private SpaceShipManager spaceShipManager;

    private TextureManager textureManager;

    private Event event;

    public Game() {
        super("Shooter", 500, 500, new Level());

        // TODO: Add proper handling here
        try {
            deserialiserProvider = new DeserialiserProvider();

            textureManager = new TextureManager(deserialiserProvider.getTextureAtlasDeserialiser().getSerialised());

            spaceShipManager = new SpaceShipManager(textureManager, deserialiserProvider.getSpaceShipDeserialiser().getSerialised());

        } catch (ResourceNotFoundException e) {
            window.close();
        }
    }

    public SpaceShipManager getSpaceShipManager() {
        return spaceShipManager;
    }
}
