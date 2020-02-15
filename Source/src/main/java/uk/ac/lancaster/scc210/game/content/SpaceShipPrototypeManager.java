package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.engine.content.ShaderManager;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.prototypes.SpaceShipPrototype;
import uk.ac.lancaster.scc210.game.resources.SerialisedSpaceShip;

import java.util.List;

/**
 * A content manager for storing Space Ships.
 */
public class SpaceShipPrototypeManager extends ContentManager<SpaceShipPrototype> {
    /**
     * Instantiates a new Space ship manager.
     * Places the given serialisedSpaceShips, place the Space Ships into the manager.
     *
     * @param animationManager     the animation manager to find animations from
     * @param serialisedSpaceShips the XML document which contains Space Ships
     */
    public SpaceShipPrototypeManager(TextureAnimationManager animationManager, ShaderManager shaderManager, Pool pool, List<SerialisedSpaceShip> serialisedSpaceShips) {
        super(null);

        for (SerialisedSpaceShip serialisedSpaceShip : serialisedSpaceShips) {
            SpaceShipPrototype spaceShip = new SpaceShipPrototype(animationManager, shaderManager, pool, serialisedSpaceShip.getAnimation(), serialisedSpaceShip.getItems(), serialisedSpaceShip.getBullet(), serialisedSpaceShip.getSpeed(), serialisedSpaceShip.getLives());

            put(serialisedSpaceShip.getName(), spaceShip);
        }
    }
}
