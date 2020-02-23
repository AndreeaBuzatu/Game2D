package uk.ac.lancaster.scc210.game;

import org.w3c.dom.Document;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.content.ShaderManager;
import uk.ac.lancaster.scc210.engine.content.SoundBufferManager;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.game.content.BulletPrototypeManager;
import uk.ac.lancaster.scc210.game.content.ItemPrototypeManager;
import uk.ac.lancaster.scc210.game.content.LevelManager;
import uk.ac.lancaster.scc210.game.content.SpaceShipPrototypeManager;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;
import uk.ac.lancaster.scc210.game.resources.*;
import uk.ac.lancaster.scc210.game.states.Completion;
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

    private LevelManager levelManager;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        super("Shooter", 1280, 720, new Playing());

        try {
            TextureAnimationManager animationManager = (TextureAnimationManager) serviceProvider.get(TextureAnimationManager.class);

            TextureManager textureManager = (TextureManager) serviceProvider.get(TextureManager.class);

            ShaderManager shaderManager = (ShaderManager) serviceProvider.get(ShaderManager.class);

            SoundBufferManager soundBufferManager = (SoundBufferManager) serviceProvider.get(SoundBufferManager.class);

            BulletDeserialiser bulletDeserialiser = new BulletDeserialiser(deserialiseXML("bullets.xml"));

            BulletPrototypeManager bulletPrototypeManager = new BulletPrototypeManager(textureManager, bulletDeserialiser.getSerialised());

            serviceProvider.put(BulletPrototypeManager.class, bulletPrototypeManager);

            BulletPool bulletPool = new BulletPool(serviceProvider);

            SpaceShipDeserialiser spaceShipDeserialiser = new SpaceShipDeserialiser(deserialiseXML("spaceships.xml"));

            SpaceShipPrototypeManager spaceShipManager = new SpaceShipPrototypeManager(animationManager, shaderManager, soundBufferManager, bulletPool, spaceShipDeserialiser.getSerialised());

            serviceProvider.put(BulletPool.class, new BulletPool(serviceProvider));

            serviceProvider.put(SpaceShipPrototypeManager.class, spaceShipManager);

            ItemPrototypeManager itemManager = new ItemPrototypeManager(textureManager);

            serviceProvider.put(ItemPrototypeManager.class, itemManager);

            LevelDeserialiser levelDeserialiser = new LevelDeserialiser(spaceShipManager, textureManager, shaderManager, deserialiseXML("levels.xml"));

            levelManager = new LevelManager(levelDeserialiser.getSerialised());

            loadPlayerXML();

            serviceProvider.put(LevelManager.class, levelManager);

        } catch (ResourceNotFoundException e) {
            window.close();
        }

        serviceProvider.put(BulletPool.class, new BulletPool(serviceProvider));

        super.addState(new Completion());
    }

    private void loadPlayerXML() throws ResourceNotFoundException {
        Document playerDocument = null;

        try {
            playerDocument = deserialiseLocalXML("player.xml");

        } catch (ResourceNotFoundException ignored) {
        } finally {
            PlayerDataDeserialiser playerDataDeserialiser = new PlayerDataDeserialiser(playerDocument, levelManager);

            // If the player.xml file can't be found, create a in-memory replacement, and manually deserialise
            if (playerDocument == null) {
                playerDataDeserialiser.createStandinXML();

                playerDocument = playerDataDeserialiser.getDocument();

                playerDataDeserialiser.deserialise();
            }

            PlayerWriter playerWriter = new PlayerWriter(playerDocument);

            serviceProvider.put(PlayerWriter.class, playerWriter);

            serviceProvider.put(PlayerData.class, playerDataDeserialiser.getPlayerData());
        }
    }
}
