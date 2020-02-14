package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.bullets.patterns.StraightLinePattern;
import uk.ac.lancaster.scc210.game.ecs.component.*;

public class SpaceShipPrototype implements Prototype {
    private final String[] items;

    private final TextureAnimationManager animationManager;

    private final Pool pool;

    private final String animation, bulletName;

    private final int speed;

    public SpaceShipPrototype(TextureAnimationManager animationManager, Pool pool, String animation, String[] items, String bulletName, int speed) {
        this.animationManager = animationManager;
        this.pool = pool;
        this.animation = animation;
        this.items = items;
        this.bulletName = bulletName;
        this.speed = speed;
    }

    public Entity create() {
        final AnimationComponent animationComponent = new AnimationComponent(animationManager.get(animation));

        final Sprite sprite = new Sprite(animationComponent.getTextureAnimation().getTexture());

        final SpriteComponent spriteComponent = new SpriteComponent(sprite);

        final SpeedComponent speedComponent = new SpeedComponent(speed);

        final RotationComponent rotationComponent = new RotationComponent(2f);

        final SpaceShipComponent spaceShipComponent = new SpaceShipComponent(items);

        final TransformableComponent transformableComponent = new TransformableComponent(sprite);

        final OrientatedBoxComponent orientatedBoxComponent = new OrientatedBoxComponent(sprite);

        final LivesComponent healthComponent = new LivesComponent(2);

        Entity spaceShip = World.createEntity(animationComponent, spriteComponent, speedComponent, rotationComponent, spaceShipComponent, orientatedBoxComponent, transformableComponent, healthComponent);

        // Let's assume ships will use the straight line pattern for now
        final FiringPatternComponent firingPatternComponent = new FiringPatternComponent(new StraightLinePattern(pool, spaceShip, bulletName));

        spaceShip.addComponent(firingPatternComponent);

        return spaceShip;
    }
}
