package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.FlashComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

/**
 * System used to draw non-animated Sprites to the Screen
 */
public class RenderSystem extends IterativeSystem {
    /**
     * Instantiates a new Iterative system.
     *
     * @param world  the world to draw entities from
     */
    public RenderSystem(World world) {
        super(world, SpriteComponent.class);
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            if (entity.hasComponent(FlashComponent.class)) {
                FlashComponent flashComponent = (FlashComponent) entity.findComponent(FlashComponent.class);

                flashComponent.updateRenderState(deltaTime);
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            if (entity.hasComponent(FlashComponent.class)) {
                FlashComponent flashComponent = (FlashComponent) entity.findComponent(FlashComponent.class);

                System.out.println(flashComponent.getCurrentState());

                target.draw(spriteComponent.getSprite(), flashComponent.getCurrentState());

            } else {
                target.draw(spriteComponent.getSprite());
            }
        }
    }
}
