package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.HealthComponent;

public class HealthSystem extends IterativeSystem {
    /**
     * Instantiate the Health System. Each entity *can* have health
     *
     * @param world      the world containing entities to use
     *
     */
    public HealthSystem(World world) {
        super(world, HealthComponent.class);
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            HealthComponent healthComponent = (HealthComponent) entity.findComponent(HealthComponent.class);

            if (healthComponent.isDead()) {
                world.removeEntity(entity);
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
