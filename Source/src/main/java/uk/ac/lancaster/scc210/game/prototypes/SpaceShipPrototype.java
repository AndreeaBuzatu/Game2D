package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.ShaderManager;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
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
import uk.ac.lancaster.scc210.game.states.Playing;

/**
 * The type Space ship prototype.
 */
public class SpaceShipPrototype implements Prototype {
    private final String[] items;

    private final ViewSize viewSize;

    private final TextureAnimationManager animationManager;

    private final TextureManager textureManager;

    private final ShaderManager shaderManager;

    private final SpaceShipPrototypeManager spaceShipPrototypeManager;

    private final Pool pool;

    private final String animation, bulletName, pattern, texture;

    private final String firingSound, hitSound;

    private final int speed, score, lives;

    /**
     * Instantiates a new Space ship prototype.
     *
     * @param serviceProvider           the service provider
     * @param spaceShipPrototypeManager the space ship prototype manager
     * @param pool                      the pool
     * @param spaceShip                 the space ship
     */
    public SpaceShipPrototype(ServiceProvider serviceProvider, SpaceShipPrototypeManager spaceShipPrototypeManager, Pool pool, SerialisedSpaceShip spaceShip) {
        this.animationManager = (TextureAnimationManager) serviceProvider.get(TextureAnimationManager.class);
        this.textureManager = (TextureManager) serviceProvider.get(TextureManager.class);
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
        this.firingSound = spaceShip.getFiringSound();
        this.hitSound = spaceShip.getHitSound();
        this.texture = spaceShip.getTexture();


        viewSize = (ViewSize) serviceProvider.get(ViewSize.class);
    }

    public Entity create() {
        final Sprite sprite;

        AnimationComponent animationComponent = null;

        if (!animation.equals("")) {
            animationComponent = new AnimationComponent(animationManager.get(animation));

            sprite = new Sprite(animationComponent.getTextureAnimation().getTexture());

        } else {
            sprite = new Sprite(textureManager.get(texture));
        }

        // Set the sprite's origin to the exact centre of the sprite
        sprite.setOrigin(sprite.getTexture().getSize().x >> 1, sprite.getTexture().getSize().y >> 1);

        final SpriteComponent spriteComponent = new SpriteComponent(sprite);

        final SpeedComponent speedComponent = new SpeedComponent(speed);

        final RotationComponent rotationComponent = new RotationComponent(2f);

        final SpaceShipComponent spaceShipComponent = new SpaceShipComponent(items, firingSound, hitSound, bulletName);

        final TransformableComponent transformableComponent = new TransformableComponent(sprite);

        final OrientatedBoxComponent orientatedBoxComponent = new OrientatedBoxComponent(sprite);

        final ScoreComponent scoreComponent = new ScoreComponent(score);

        final LivesComponent livesComponent = new LivesComponent(lives);

        final FlashComponent flashComponent = new FlashComponent(sprite, shaderManager.get("flash"));

        Entity spaceShip = World.createEntity(spriteComponent, speedComponent, rotationComponent, spaceShipComponent, orientatedBoxComponent, transformableComponent, scoreComponent, livesComponent, flashComponent);

        if (pattern != null && !pattern.equals("")) {
            final FiringPatternComponent firingPatternComponent = new FiringPatternComponent(findPattern(spaceShip, pattern));

            spaceShip.addComponent(firingPatternComponent);
        }

        if (animationComponent != null) {
            spaceShip.addComponent(animationComponent);
        }

        return spaceShip;
    }

    private Pattern findPattern(Entity spaceShip, String patternName) {
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

    public static void positionSpaceShip(ViewSize viewSize, Sprite sprite, Vector2f givenPosition) {
        Vector2i size = sprite.getTexture().getSize();

        float posX = givenPosition.x;
        float posY = givenPosition.y;

        if (givenPosition.x < size.x >> 1) {
            posX = size.x >> 1;

            sprite.setPosition(new Vector2f(posX , posY));
        }

        if (givenPosition.x > (viewSize.getViewBounds().width - (size.x >> 1))) {
            posX = viewSize.getViewBounds().width - (size.x >> 1);

            sprite.setPosition(new Vector2f(posX,posY));
        }

        if (givenPosition.y > viewSize.getViewBounds().height - (size.y >> 1)) {
            posY = viewSize.getViewBounds().height - (size.y >> 1);

            sprite.setPosition(new Vector2f(posX, posY));
        }

        if (givenPosition.y < Playing.INFO_BOX_HEIGHT) {
            posY = Playing.INFO_BOX_HEIGHT;

            sprite.setPosition(new Vector2f(posX, posY + (size.y >> 1)));

        } else {
            sprite.setPosition(new Vector2f(posX, posY));
        }
    }
}
