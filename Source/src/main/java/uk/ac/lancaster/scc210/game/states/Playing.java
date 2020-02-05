package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.LevelManager;
import uk.ac.lancaster.scc210.game.content.SpaceShipPrototypeManager;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.system.*;
import uk.ac.lancaster.scc210.game.level.Level;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;

import java.util.Iterator;

/**
 * Represents the actual game-play state.
 */
public class Playing implements State {
    private Iterator<Level> levelIterator;

    private World world;

    private Level level;

    private LevelSystem levelSystem;

    private boolean completed;

    @Override
    public void setup(StateBasedGame game) {
        completed = false;

        LevelManager levelManager = (LevelManager) game.getServiceProvider().get(LevelManager.class);

        levelIterator = levelManager.getIterator();

        level = levelIterator.next();

        world = new World(game.getServiceProvider());

        levelSystem = new LevelSystem(world, level);

        world.addPool((BulletPool) game.getServiceProvider().get(BulletPool.class));

        world.addSystem(new AnimatedRenderSystem(world));

        world.addSystem(new PlayerMovementSystem(world));

        world.addSystem(new RenderSystem(world));

        world.addSystem(new FiringSystem(world));

        world.addSystem(new MovementSystem(world));

        world.addSystem(new ViewBoundsSystem(world));

        world.addSystem(new BulletCollision(world));

        world.addSystem(levelSystem);

        SpaceShipPrototypeManager spaceShipManager = (SpaceShipPrototypeManager) game.getServiceProvider().get(SpaceShipPrototypeManager.class);

        Entity player = spaceShipManager.get("player").create();

        player.addComponent(new PlayerComponent());

        world.addEntity(player);
    }

    @Override
    public void draw(RenderTarget target) {
        world.draw(target);
    }

    @Override
    public boolean complete() {
        return completed;
    }

    @Override
    public void update() {
        world.update();

        if (level.complete()) {
            System.out.println("Complete level");

            if (levelIterator.hasNext()) {
                level = levelIterator.next();

                levelSystem.setLevel(level);

            } else {
                completed = true;
            }
        }
    }
}
