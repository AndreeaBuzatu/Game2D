package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

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
    public void entityChanged() {
        super.entityChanged();

        spaceShips = world.getEntitiesFor(SpaceShipComponent.class);
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            SpriteComponent bulletSpriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            FloatRect spriteBounds = bulletSpriteComponent.getSprite().getGlobalBounds();

            for (Entity spaceShip : spaceShips) {
                SpriteComponent spaceShipSprite = (SpriteComponent) spaceShip.findComponent(SpriteComponent.class);

                boolean collision = spaceShipSprite.getSprite().getGlobalBounds().contains(bulletSpriteComponent.getSprite().getPosition());

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
