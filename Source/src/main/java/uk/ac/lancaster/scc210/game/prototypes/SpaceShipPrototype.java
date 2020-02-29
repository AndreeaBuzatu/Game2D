package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.audio.Sound;
import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.content.ShaderManager;
import uk.ac.lancaster.scc210.engine.content.SoundBufferManager;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.engine.service.ServiceProvider;
import uk.ac.lancaster.scc210.game.bullets.patterns.StarBulletPattern;
import uk.ac.lancaster.scc210.game.bullets.patterns.StraightLineBulletPattern;
import uk.ac.lancaster.scc210.game.content.SpaceShipPrototypeManager;
import uk.ac.lancaster.scc210.game.ecs.component.*;
import uk.ac.lancaster.scc210.game.patterns.Pattern;
import uk.ac.lancaster.scc210.game.patterns.StarSpaceshipPattern;
import uk.ac.lancaster.scc210.game.resources.SerialisedSpaceShip;

public class SpaceShipPrototype implements Prototype {
    private final String[] items;

    private final TextureAnimationManager animationManager;

    private final ShaderManager shaderManager;

    private final SpaceShipPrototypeManager spaceShipPrototypeManager;

    private final Pool pool;

    private final String animation, bulletName, pattern;

    private final Sound firingSound, hitSound;

    private final int speed, score, lives;

    public SpaceShipPrototype(ServiceProvider serviceProvider, SpaceShipPrototypeManager spaceShipPrototypeManager, Pool pool, SerialisedSpaceShip spaceShip) {
        this.animationManager = (TextureAnimationManager) serviceProvider.get(TextureAnimationManager.class);
        this.shaderManager = (ShaderManager) serviceProvider.get(ShaderManager.class);
        this.spaceShipPrototypeManager = spaceShipPrototypeManager;
        this.pool = pool;
        this.animation = spaceShip.getAnimation();
        this.items = spaceShip.getItems();
        this.bulletName = spaceShip.getBullet();
        this.pattern = spaceShip.getPattern();
        this.speed = spaceShip.getSpeed();
        this.score = spaceShip.getScore();
        this.lives = spaceShip.getLives();

        SoundBufferManager soundBufferManager = (SoundBufferManager) serviceProvider.get(SoundBufferManager.class);

        this.firingSound = new Sound(soundBufferManager.get(spaceShip.getFiringSound()));
        this.hitSound = new Sound(soundBufferManager.get(spaceShip.getHitSound()));
    }

    public Entity create() {
        final AnimationComponent animationComponent = new AnimationComponent(animationManager.get(animation));

        final Sprite sprite = new Sprite(animationComponent.getTextureAnimation().getTexture());

        // Set the sprite's origin to the exact centre of the sprite
        sprite.setOrigin(sprite.getTexture().getSize().x >> 1, sprite.getTexture().getSize().y >> 1);

        final SpriteComponent spriteComponent = new SpriteComponent(sprite);

        final SpeedComponent speedComponent = new SpeedComponent(speed);

        final RotationComponent rotationComponent = new RotationComponent(2f);

        final SpaceShipComponent spaceShipComponent = new SpaceShipComponent(items, firingSound, hitSound);

        final TransformableComponent transformableComponent = new TransformableComponent(sprite);

        final OrientatedBoxComponent orientatedBoxComponent = new OrientatedBoxComponent(sprite);

        final ScoreComponent scoreComponent = new ScoreComponent(score);

        final LivesComponent livesComponent = new LivesComponent(lives);

        final FlashComponent flashComponent = new FlashComponent(sprite, shaderManager.get("flash"));

        Entity spaceShip = World.createEntity(animationComponent, spriteComponent, speedComponent, rotationComponent, spaceShipComponent, orientatedBoxComponent, transformableComponent, scoreComponent, livesComponent, flashComponent);

        if (pattern != null && !pattern.equals("")) {
            final FiringPatternComponent firingPatternComponent = new FiringPatternComponent(findPattern(spaceShip, pattern));

            spaceShip.addComponent(firingPatternComponent);
        }

        return spaceShip;
    }

    private Pattern findPattern(Entity spaceShip, String patternName) {
        System.out.println("called");
        switch (patternName) {
            case "star-bullet":
                return new StarBulletPattern(pool, spaceShip, bulletName);

            case "star-spaceship":
                return new StarSpaceshipPattern(spaceShip, spaceShipPrototypeManager, bulletName);

            case "straight-bullet":
            default:
                return new StraightLineBulletPattern(pool, spaceShip, bulletName);
        }
    }
}
