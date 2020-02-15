package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.collision.OrientatedBox;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AsteroidComponent;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;

import java.util.Set;

public class AsteroidCollisionSystem extends IterativeSystem {
    private Set<Entity> spaceShips;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public AsteroidCollisionSystem(World world) {
        super(world, AsteroidComponent.class);

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
            OrientatedBoxComponent asteroidBox = (OrientatedBoxComponent) entity.findComponent(OrientatedBoxComponent.class);

            for (Entity spaceShip : spaceShips) {
                OrientatedBoxComponent transformableBox = (OrientatedBoxComponent) spaceShip.findComponent(OrientatedBoxComponent.class);

                if (OrientatedBox.areColliding(asteroidBox.getOrientatedBox(), transformableBox.getOrientatedBox())) {
                    LivesComponent livesComponent = (LivesComponent) spaceShip.findComponent(LivesComponent.class);

                    livesComponent.kill();
                }
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
