package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.collision.OrientatedBox;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.EnemyComponent;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;

import java.util.Optional;

public class SpaceShipCollisionSystem extends IterativeSystem {
    private Optional<Entity> player;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public SpaceShipCollisionSystem(World world) {
        super(world, EnemyComponent.class);

        player = world.getEntitiesFor(PlayerComponent.class).stream().findFirst();
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        player = world.getEntitiesFor(PlayerComponent.class).stream().findFirst();
    }

    @Override
    public void update(Time deltaTime) {
        if (player.isEmpty()) {
            return;
        }

        Entity playerEntity = player.get();

        for (Entity entity : entities) {
            // Player can't collide with themselves
            if (playerEntity == entity) {
                continue;
            }

            OrientatedBoxComponent playerOrientedBox = (OrientatedBoxComponent) playerEntity.findComponent(OrientatedBoxComponent.class);

            OrientatedBoxComponent entityOrientedBox = (OrientatedBoxComponent) entity.findComponent(OrientatedBoxComponent.class);

            if (OrientatedBox.areColliding(playerOrientedBox.getOrientatedBox(), entityOrientedBox.getOrientatedBox())) {
                SpaceShipComponent spaceShipComponent = (SpaceShipComponent) playerEntity.findComponent(SpaceShipComponent.class);

                LivesComponent livesComponent = (LivesComponent) playerEntity.findComponent(LivesComponent.class);

                spaceShipComponent.playHitSound();

                livesComponent.kill();
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
