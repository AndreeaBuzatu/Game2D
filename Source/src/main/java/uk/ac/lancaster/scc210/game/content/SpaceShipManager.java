package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.game.ecs.entity.SpaceShip;
import uk.ac.lancaster.scc210.game.resources.SerialisedSpaceShip;

import java.util.List;

/**
 * A content manager for storing Space Ships.
 */
public class SpaceShipManager extends ContentManager<SpaceShip> {
    /**
     * Instantiates a new Space ship manager.
     * Places the given serialisedSpaceShips, place the Space Ships into the manager.
     *
     * @param animationManager     the animation manager to find animations from
     * @param serialisedSpaceShips the XML document which contains Space Ships
     */
    public SpaceShipManager(TextureAnimationManager animationManager, List<SerialisedSpaceShip> serialisedSpaceShips) {
        super(null);

        for (SerialisedSpaceShip serialisedSpaceShip : serialisedSpaceShips) {
            SpaceShip spaceShip = new SpaceShip(animationManager, serialisedSpaceShip.getAnimation(), serialisedSpaceShip.getSpeed());

            put(serialisedSpaceShip.getName(), spaceShip);
            /*
            AnimationComponent animationComponent = new AnimationComponent(animationManager.get(spaceShip.getAnimation()));

            SpriteComponent spriteComponent = new SpriteComponent(new Sprite(animationComponent.getTextureAnimation().getTexture()));

            SpeedComponent speedComponent = new SpeedComponent(spaceShip.getSpeed());

            RotationComponent rotationComponent = new RotationComponent(2f);

            put(spaceShip.getName(), World.createEntity(spriteComponent, animationComponent, speedComponent, rotationComponent));
             */
        }
    }
}
