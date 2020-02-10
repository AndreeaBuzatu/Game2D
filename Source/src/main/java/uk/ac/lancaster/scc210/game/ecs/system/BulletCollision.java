package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.collision.OrientatedBox;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;

import java.util.Set;

public class BulletCollision extends IterativeSystem {
    private Set<Entity> spaceShips;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public BulletCollision(World world) {
        super(world, BulletComponent.class);

        spaceShips = world.getEntitiesFor(SpaceShipComponent.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        spaceShips = world.getEntitiesFor(SpaceShipComponent.class);
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            OrientatedBoxComponent entityOrientedBox = (OrientatedBoxComponent) entity.findComponent(OrientatedBoxComponent.class);

            for (Entity spaceShip : spaceShips) {
                OrientatedBoxComponent spaceShipOrientedBox = (OrientatedBoxComponent) spaceShip.findComponent(OrientatedBoxComponent.class);

                boolean colliding = OrientatedBox.areColliding(entityOrientedBox.getOrientatedBox(), spaceShipOrientedBox.getOrientatedBox());

                if (colliding) {
                    PooledComponent pooledComponent = (PooledComponent) entity.findComponent(PooledComponent.class);

                    world.removeEntity(spaceShip);

                    // Return the bullet back to the pool and remove it from the world
                    world.getPool(pooledComponent.getPoolClass()).returnEntity(entity);

                    world.removeEntity(entity);
                }
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
