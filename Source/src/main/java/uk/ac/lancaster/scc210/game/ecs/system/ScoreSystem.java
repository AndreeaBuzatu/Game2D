package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.component.ScoreComponent;

import java.util.Optional;

public class ScoreSystem extends IterativeSystem {
    private Optional<Entity> player;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public ScoreSystem(World world) {
        super(world);
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        player = world.getEntitiesFor(PlayerComponent.class).stream().findFirst();
    }

    @Override
    public void entityRemoved(Entity entity) {
        super.entityRemoved(entity);

        // Don't precede
        if (player.isEmpty()) {
            return;
        }

        Entity playerEntity = player.get();

        if (entity.hasComponent(ScoreComponent.class) && !entity.hasComponent(PlayerComponent.class)) {
            PlayerComponent playerComponent = (PlayerComponent) playerEntity.findComponent(PlayerComponent.class);

            ScoreComponent scoreComponent = (ScoreComponent) entity.findComponent(ScoreComponent.class);

            playerComponent.changeScore(scoreComponent.getScore());
        }
    }

    @Override
    public void update(Time deltaTime) {
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
