package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.ecs.component.*;

public class SpaceShipPrototype implements Prototype {
    private final String[] items;

    private final TextureAnimationManager animationManager;

    private final String animation;

    private final int speed, score;

    public SpaceShipPrototype(TextureAnimationManager animationManager, String animation, String[] items, int speed, int score) {
        this.animationManager = animationManager;
        this.animation = animation;
        this.items = items;
        this.speed = speed;
        this.score = score;
    }

    public Entity create() {
        final AnimationComponent animationComponent = new AnimationComponent(animationManager.get(animation));

        final Sprite sprite = new Sprite(animationComponent.getTextureAnimation().getTexture());

        final SpriteComponent spriteComponent = new SpriteComponent(sprite);

        final SpeedComponent speedComponent = new SpeedComponent(speed);

        final RotationComponent rotationComponent = new RotationComponent(2f);

        final SpaceShipComponent spaceShipComponent = new SpaceShipComponent(items);

        final TransformableComponent transformableComponent = new TransformableComponent(spriteComponent.getSprite());

        final OrientatedBoxComponent orientatedBoxComponent = new OrientatedBoxComponent(sprite);

        final ScoreComponent scoreComponent = new ScoreComponent(score);

        return World.createEntity(animationComponent, spriteComponent, speedComponent, rotationComponent, spaceShipComponent, orientatedBoxComponent, transformableComponent, scoreComponent);
    }
}
