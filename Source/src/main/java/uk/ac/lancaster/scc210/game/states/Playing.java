package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.LevelManager;
import uk.ac.lancaster.scc210.game.content.SpaceShipManager;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.system.*;
import uk.ac.lancaster.scc210.game.level.Level;
import uk.ac.lancaster.scc210.game.level.LevelWave;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;

/**
 * Represents the actual game-play state.
 */
public class Playing implements State {
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

        world.addSystem(new WaveSystem(world));

        SpaceShipManager spaceShipManager = (SpaceShipManager) game.getServiceProvider().get(SpaceShipManager.class);

        Entity player = spaceShipManager.get("player").createEntity();

        player.addComponent(new PlayerComponent());

        world.addEntity(player);

        LevelManager levelManager = (LevelManager) game.getServiceProvider().get(LevelManager.class);

        Level level = levelManager.get("0");

        for (LevelWave wave : level.getCurrentStage().getWaves()) {
            for (Entity entity : wave.getEntities()) {
                world.addEntity(entity);
            }
        }

        /*
        Sprite sprite = new Sprite(((TextureManager) game.getServiceProvider().get(TextureManager.class)).get("spritesheet.png:other"));

        SpriteComponent spriteComponent = new SpriteComponent(sprite);

        SpeedComponent speedComponent = new SpeedComponent(5);

        WaveComponent waveComponent = new WaveComponent(new SineWave(new Vector2f(0, 0), new Vector2f(500, 500)));

        world.addEntity(World.createEntity(spriteComponent, speedComponent, waveComponent));
         */
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
