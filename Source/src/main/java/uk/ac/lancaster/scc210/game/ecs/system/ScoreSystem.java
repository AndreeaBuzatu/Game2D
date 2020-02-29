package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.OutOfBoundsComponent;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.component.ScoreComponent;
import uk.ac.lancaster.scc210.game.ecs.entity.PlayerFinder;

public class ScoreSystem extends IterativeSystem {
    private Entity player;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public ScoreSystem(World world) {
        super(world);

        player = PlayerFinder.findPlayer(world);
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        player = PlayerFinder.findPlayer(world);
    }

    @Override
    public void entityRemoved(Entity entity) {
        super.entityRemoved(entity);

        // Don't precede
        if (player == null) {
            return;
        }

        // Entities which leave the bounds of the window (except waves) don't give score
        if (entity.hasComponent(ScoreComponent.class) && !entity.hasComponent(PlayerComponent.class) && !entity.hasComponent(OutOfBoundsComponent.class)) {
            PlayerComponent playerComponent = (PlayerComponent) player.findComponent(PlayerComponent.class);

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
