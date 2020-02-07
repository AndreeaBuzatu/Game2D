package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AsteroidComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

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
            AsteroidComponent asteroidComponent = (AsteroidComponent) entity.findComponent(AsteroidComponent.class);

            CircleShape asteroid = asteroidComponent.getCircle();

            FloatRect asteroidBounds = asteroid.getGlobalBounds();

            for (Entity spaceShip : spaceShips) {
                SpriteComponent spriteComponent = (SpriteComponent) spaceShip.findComponent(SpriteComponent.class);

                boolean collision = asteroidBounds.contains(spriteComponent.getSprite().getPosition());

                if (collision) {
                    world.removeEntity(spaceShip);
                }
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
