package uk.ac.lancaster.scc210.game.content;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.game.ecs.component.AnimationComponent;
import uk.ac.lancaster.scc210.game.ecs.component.RotationComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.resources.SerialisedSpaceShip;

import java.util.List;

/**
 * A content manager for storing Space Ships.
 */
public class SpaceShipManager extends ContentManager<Entity> {
    /**
     * Instantiates a new Space ship manager.
     * Places the given serialisedSpaceShips, place the Space Ships into the manager.
     *
     * @param animationManager     the animation manager to find animations from
     * @param serialisedSpaceShips the XML document which contains Space Ships
     */
    public SpaceShipManager(TextureAnimationManager animationManager, List<SerialisedSpaceShip> serialisedSpaceShips) {
        super(new Entity());

        for (SerialisedSpaceShip spaceShip : serialisedSpaceShips) {
            AnimationComponent animationComponent = new AnimationComponent(animationManager.get(spaceShip.getAnimation()));

            SpriteComponent spriteComponent = new SpriteComponent(new Sprite(animationComponent.getTextureAnimation().getTexture()));

            SpeedComponent speedComponent = new SpeedComponent(spaceShip.getSpeed());

            RotationComponent rotationComponent = new RotationComponent(2f);

            put(spaceShip.getName(), World.createEntity(spriteComponent, animationComponent, speedComponent, rotationComponent));
        }
    }
}
