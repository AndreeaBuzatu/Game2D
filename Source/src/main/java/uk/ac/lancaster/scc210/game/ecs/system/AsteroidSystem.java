package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AsteroidComponent;
import uk.ac.lancaster.scc210.game.ecs.component.FlashComponent;

import java.util.Collection;

/**
 * The type Asteroid system.
 */
public class AsteroidSystem extends IterativeSystem {
    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public AsteroidSystem(World world) {
        super(world, AsteroidComponent.class, FlashComponent.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        entities = world.getEntitiesFor(AsteroidComponent.class, FlashComponent.class);
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
        this.entities = world.getEntitiesFor(AsteroidComponent.class, FlashComponent.class);
    }

    @Override
    public void entityRemoved(Entity entity) {

    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            FlashComponent flashComponent = (FlashComponent) entity.findComponent(FlashComponent.class);

            flashComponent.updateRenderState(deltaTime);
        }
    }

    @Override
    public void draw(RenderTarget target) {
        for (Entity entity : entities) {
            AsteroidComponent asteroidComponent = (AsteroidComponent) entity.findComponent(AsteroidComponent.class);

            FlashComponent flashComponent = (FlashComponent) entity.findComponent(FlashComponent.class);

            target.draw(asteroidComponent.getCircle(), flashComponent.getCurrentState());
        }
    }
}
