package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.SpaceShipManager;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.ecs.component.WaveComponent;
import uk.ac.lancaster.scc210.game.ecs.system.*;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;
import uk.ac.lancaster.scc210.game.waves.SineWave;

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

        world.addSystem(new WaveSystem(world));

        SpaceShipManager spaceShipManager = (SpaceShipManager) game.getServiceProvider().get(SpaceShipManager.class);

        world.addEntity(spaceShipManager.get("player"));

        Sprite sprite = new Sprite(((TextureManager) game.getServiceProvider().get(TextureManager.class)).get("spritesheet.png:other"));

        SpriteComponent spriteComponent = new SpriteComponent(sprite);

        SpeedComponent speedComponent = new SpeedComponent(5);

        WaveComponent waveComponent = new WaveComponent(new SineWave(new Vector2f(0, 0), new Vector2f(500, 500)));

        world.addEntity(World.createEntity(spriteComponent, speedComponent, waveComponent));
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
