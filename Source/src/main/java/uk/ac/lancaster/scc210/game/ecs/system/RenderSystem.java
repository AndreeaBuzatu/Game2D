package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AnimationComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

/**
 * The type Render system.
 */
public class RenderSystem extends IterativeSystem {
    /**
     * Instantiates a new Render system.
     *
     * @param world the world
     */
    public RenderSystem(World world) {
        super(world, SpriteComponent.class, AnimationComponent.class);
    }

    @Override
    public void draw(RenderTarget target) {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            AnimationComponent animationComponent = (AnimationComponent) entity.findComponent(AnimationComponent.class);

            spriteComponent.getSprite().setTexture(animationComponent.getTextureAnimation().getTexture());

            target.draw(spriteComponent.getSprite());
        }
    }

    @Override
    public void update() {

    }
}
