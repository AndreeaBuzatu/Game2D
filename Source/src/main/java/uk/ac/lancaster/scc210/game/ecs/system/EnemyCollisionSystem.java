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

public class EnemyCollisionSystem extends IterativeSystem {
    private final Time COLLISION_GAP = Time.getSeconds(1);

    private Time elapsedTime;

    private Entity player;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public EnemyCollisionSystem(World world) {
        super(world, EnemyComponent.class);

        player = PlayerFinder.findPlayer(world);

        elapsedTime = Time.ZERO;
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

        elapsedTime = Time.add(elapsedTime, deltaTime);

        for (Entity entity : entities) {
            // Player can't collide with themselves
            if (player == entity) {
                continue;
            }

            OrientatedBoxComponent playerOrientedBox = (OrientatedBoxComponent) player.findComponent(OrientatedBoxComponent.class);

            OrientatedBoxComponent entityOrientedBox = (OrientatedBoxComponent) entity.findComponent(OrientatedBoxComponent.class);

            if (OrientatedBox.areColliding(playerOrientedBox.getOrientatedBox(), entityOrientedBox.getOrientatedBox()) && elapsedTime.asSeconds() > COLLISION_GAP.asSeconds()) {
                SpaceShipComponent spaceShipComponent = (SpaceShipComponent) player.findComponent(SpaceShipComponent.class);

                LivesComponent livesComponent = (LivesComponent) player.findComponent(LivesComponent.class);

                spaceShipComponent.playHitSound();

                elapsedTime = Time.ZERO;

                livesComponent.setLives(livesComponent.getLives() - 1);
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
