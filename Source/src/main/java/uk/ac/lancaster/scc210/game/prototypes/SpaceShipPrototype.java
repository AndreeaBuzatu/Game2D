package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.bullets.patterns.StraightLinePattern;
import uk.ac.lancaster.scc210.game.ecs.component.*;
import uk.ac.lancaster.scc210.game.resources.SerialisedSpaceShip;

public class SpaceShipPrototype implements Prototype {
    private final String[] items;

    private final TextureAnimationManager animationManager;

    private final Pool pool;

    private final String animation, bulletName;

    private final int speed, score;

    public SpaceShipPrototype(TextureAnimationManager animationManager, Pool pool, SerialisedSpaceShip spaceShip) {
        this.animationManager = animationManager;
        this.pool = pool;
        this.animation = spaceShip.getAnimation();
        this.items = spaceShip.getItems();
        this.bulletName = spaceShip.getBullet();
        this.speed = spaceShip.getSpeed();
        this.score = spaceShip.getScore();
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

        final ScoreComponent scoreComponent = new ScoreComponent(score);

        Entity spaceShip = World.createEntity(animationComponent, spriteComponent, speedComponent, rotationComponent, spaceShipComponent, orientatedBoxComponent, transformableComponent, scoreComponent);

        // Let's assume ships will use the straight line pattern for now
        final FiringPatternComponent firingPatternComponent = new FiringPatternComponent(new StraightLinePattern(pool, spaceShip, bulletName));

        spaceShip.addComponent(firingPatternComponent);

        return spaceShip;
    }
}
