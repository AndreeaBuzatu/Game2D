package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.animation.TextureAnimation;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AnimationComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * System used to render TextureAnimations to the RenderTarget (usually the RenderWindow)
 */
public class AnimationUpdateSystem extends IterativeSystem {
    private Set<TextureAnimation> animations;

    /**
     * Instantiates a new Render system.
     *
     * @param world the world to take from entities
     */
    public AnimationUpdateSystem(World world) {
        super(world, SpriteComponent.class, AnimationComponent.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        entities = world.getEntitiesFor(SpriteComponent.class, AnimationComponent.class);

        findAnimations();
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
        this.entities = world.getEntitiesFor(SpriteComponent.class, AnimationComponent.class);
    }

    @Override
    public void entityRemoved(Entity entity) {
        findAnimations();
    }

    @Override
    public void draw(RenderTarget target) {
    }

    @Override
    public void update(Time deltaTime) {
        animations.forEach(textureAnimation -> textureAnimation.animate(deltaTime));

        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            AnimationComponent animationComponent = (AnimationComponent) entity.findComponent(AnimationComponent.class);

            spriteComponent.getSprite().setTexture(animationComponent.getTextureAnimation().getTexture());
        }
    }

    private void findAnimations() {
        animations = entities
                .parallelStream()
                .filter(entity -> entity.hasComponent(AnimationComponent.class))
                .map(entity -> {
                    AnimationComponent animationComponent = (AnimationComponent) entity.findComponent(AnimationComponent.class);

                    return animationComponent.getTextureAnimation();
                })
                .collect(Collectors.toSet());
    }
}
