package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.prototypes.ItemPrototype;

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
            SpriteComponent bulletSpriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            for (Entity spaceShip : spaceShips) {
                SpriteComponent spaceShipSprite = (SpriteComponent) spaceShip.findComponent(SpriteComponent.class);

                boolean collision = spaceShipSprite.getSprite().getGlobalBounds().contains(bulletSpriteComponent.getSprite().getPosition());

                if (collision) {
                    PooledComponent pooledComponent = (PooledComponent) entity.findComponent(PooledComponent.class);

                    ItemPrototype test = null; //TODO: instantiate (note: this is just a test)
                    world.addEntity(test.create());
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
