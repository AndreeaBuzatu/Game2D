package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AsteroidComponent;
import uk.ac.lancaster.scc210.game.ecs.component.HealthComponent;

public class HealthSystem extends IterativeSystem {
    /**
     * Instantiates a new Iterative system.
     *
     * @param world      the world containing entities to use
     *
     */
    public HealthSystem(World world) {
        super(world, HealthComponent.class);
    }

    @Override
    public void update(Time deltaTime) {
        for( Entity entity : entities)
        {
            HealthComponent healthComponent = (HealthComponent) entity.findComponent(HealthComponent.class);

            if (entity.hasComponent(AsteroidComponent.class)) {
                System.out.println("Asteroid health:" + healthComponent.getLives());
            }

            if(healthComponent.isDead()){
                System.out.println("Is dead");

                world.removeEntity(entity);
            }
        }

    }

    @Override
    public void draw(RenderTarget target) {

    }
}
