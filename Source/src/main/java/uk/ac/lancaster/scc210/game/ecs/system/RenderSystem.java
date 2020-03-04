package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.*;

import java.util.Collection;

/**
 * System used to draw non-animated Sprites to the Screen
 */
public class RenderSystem extends IterativeSystem {
    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world to draw entities from
     */
    public RenderSystem(World world) {
        super(world);

        entities = world.getEntitiesWithAny(SpriteComponent.class, AsteroidComponent.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        entities = world.getEntitiesWithAny(SpriteComponent.class, AsteroidComponent.class);
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
        this.entities = world.getEntitiesWithAny(SpriteComponent.class, AsteroidComponent.class);
    }

    @Override
    public void entityRemoved(Entity entity) {
        this.entities = world.getEntitiesWithAny(SpriteComponent.class, AsteroidComponent.class);
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

            Drawable drawable = null;

            RenderStates renderStates = RenderStates.DEFAULT;

            if (entity.hasComponent(SpriteComponent.class)) {
                drawable = spriteComponent.getSprite();
            }

            if (entity.hasComponent(AsteroidComponent.class)) {
                drawable = ((AsteroidComponent) entity.findComponent(AsteroidComponent.class)).getCircle();
            }

            if (entity.hasComponent(FlashComponent.class)) {
                FlashComponent flashComponent = (FlashComponent) entity.findComponent(FlashComponent.class);

                if (entity.hasComponent(PlayerComponent.class)) {
                    LivesComponent livesComponent = (LivesComponent) entity.findComponent(LivesComponent.class);

                    // Flash the player red if they only have 1 remaining
                    if (livesComponent.getLives() == 1) {
                        flashComponent.setShaderRed();
                    }
                    
                } else {
                    // If the previous shader was white, reset back to white
                    flashComponent.setShaderWhite();
                }

                renderStates = flashComponent.getCurrentState();
            }

            target.draw(drawable, renderStates);
        }
    }
}
