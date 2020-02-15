package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;

public class LivesSystem extends IterativeSystem {
    /**
     * Instantiate the Health System. Each entity *can* have health
     *
     * @param world      the world containing entities to use
     *
     */
    public LivesSystem(World world) {
        super(world, LivesComponent.class);
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            LivesComponent livesComponent = (LivesComponent) entity.findComponent(LivesComponent.class);

            if (livesComponent.isDead()) {
                world.removeEntity(entity);
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
