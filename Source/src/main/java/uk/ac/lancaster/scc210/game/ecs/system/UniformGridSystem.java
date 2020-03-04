package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.collision.UniformGrid;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.TransformableComponent;

import java.util.Collection;

/**
 * The type Uniform grid system.
 */
public class UniformGridSystem extends IterativeSystem {
    private final UniformGrid uniformGrid;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public UniformGridSystem(World world) {
        super(world, TransformableComponent.class);

        uniformGrid = (UniformGrid) world.getServiceProvider().get(UniformGrid.class);

        uniformGrid.addEntities(entities);
    }

    @Override
    public void entityAdded(Entity entity) {
        entities = world.getEntitiesFor(TransformableComponent.class);

        uniformGrid.addEntity(entity);

        uniformGrid.addEntities(entities);
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
        this.entities = world.getEntitiesFor(TransformableComponent.class);

        uniformGrid.addEntities(this.entities);
    }

    @Override
    public void entityRemoved(Entity entity) {
        uniformGrid.removeEntity(entity);
    }

    @Override
    public void update(Time deltaTime) {
        uniformGrid.update();
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
