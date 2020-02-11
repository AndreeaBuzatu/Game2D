package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AsteroidComponent;

public class AsteroidSystem extends IterativeSystem {
    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public AsteroidSystem(World world) {
        super(world, AsteroidComponent.class);
    }

    @Override
    public void update(Time deltaTime) {

    }

    @Override
    public void draw(RenderTarget target) {
        for (Entity entity : entities) {
            AsteroidComponent asteroidComponent = (AsteroidComponent) entity.findComponent(AsteroidComponent.class);

            target.draw(asteroidComponent.getCircle());
        }
    }
}