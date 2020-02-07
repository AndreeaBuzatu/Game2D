package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.animation.TextureAnimation;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AnimationComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * System used to render TextureAnimations to the RenderTarget (usually the RenderWindow)
 */
public class AnimatedRenderSystem extends IterativeSystem {
    private Set<TextureAnimation> animations;

    /**
     * Instantiates a new Render system.
     *
     * @param world the world to take from entities
     */
    public AnimatedRenderSystem(World world) {
        super(world, SpriteComponent.class, AnimationComponent.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        findAnimations();
    }

    @Override
    public void entityRemoved(Entity entity) {
        super.entityRemoved(entity);

        findAnimations();
    }

    @Override
    public void draw(RenderTarget target) {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            target.draw(spriteComponent.getSprite());
        }
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
                .map(entity -> {
                    AnimationComponent animationComponent = (AnimationComponent) entity.findComponent(AnimationComponent.class);

                    return animationComponent.getTextureAnimation();
                })
                .collect(Collectors.toSet());
    }
}
