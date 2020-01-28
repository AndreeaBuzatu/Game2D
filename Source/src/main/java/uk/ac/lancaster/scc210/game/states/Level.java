package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.SpaceShipManager;
import uk.ac.lancaster.scc210.game.ecs.system.*;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;

/**
 * Represents the actual game-play state.
 */
public class Level implements State {
    private World world;

    @Override
    public void setup(StateBasedGame game) {
        world = new World(game.getServiceProvider());

        world.addPool((BulletPool) game.getServiceProvider().get(BulletPool.class));

        world.addSystem(new AnimatedRenderSystem(world));

        world.addSystem(new PlayerMovementSystem(world));

        world.addSystem(new RenderSystem(world));

        world.addSystem(new FiringSystem(world));

        world.addSystem(new MovementSystem(world));

        world.addSystem(new ViewBoundsSystem(world));

        SpaceShipManager spaceShipManager = (SpaceShipManager) game.getServiceProvider().get(SpaceShipManager.class);

        world.addEntity(spaceShipManager.get("player"));
    }

    @Override
    public void draw(RenderTarget target) {
        world.draw(target);
    }

    @Override
    public void update() {
        world.update();
    }
}
