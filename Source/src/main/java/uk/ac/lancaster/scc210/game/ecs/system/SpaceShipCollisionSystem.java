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
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;
import uk.ac.lancaster.scc210.game.ecs.entity.PlayerFinder;

public class SpaceShipCollisionSystem extends IterativeSystem {
    private Entity player;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public SpaceShipCollisionSystem(World world) {
        super(world, EnemyComponent.class);

        player = PlayerFinder.findPlayer(world);
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        player = PlayerFinder.findPlayer(world);
    }

    @Override
    public void update(Time deltaTime) {
        if (player == null) {
            return;
        }

        for (Entity entity : entities) {
            // Player can't collide with themselves
            if (player == entity) {
                continue;
            }

            OrientatedBoxComponent playerOrientedBox = (OrientatedBoxComponent) player.findComponent(OrientatedBoxComponent.class);

            OrientatedBoxComponent entityOrientedBox = (OrientatedBoxComponent) entity.findComponent(OrientatedBoxComponent.class);

            if (OrientatedBox.areColliding(playerOrientedBox.getOrientatedBox(), entityOrientedBox.getOrientatedBox())) {
                SpaceShipComponent spaceShipComponent = (SpaceShipComponent) player.findComponent(SpaceShipComponent.class);

                LivesComponent livesComponent = (LivesComponent) player.findComponent(LivesComponent.class);

                spaceShipComponent.playHitSound();

                livesComponent.kill();
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
