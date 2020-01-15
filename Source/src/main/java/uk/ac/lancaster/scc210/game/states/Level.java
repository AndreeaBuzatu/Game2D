package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.SpaceShipManager;
import uk.ac.lancaster.scc210.game.ecs.system.MovementSystem;
import uk.ac.lancaster.scc210.game.ecs.system.RenderSystem;

public class Level implements State {
    private World world;

    @Override
    public void setup(StateBasedGame game) {
        world = new World();

        world.addSystem(new RenderSystem(world));

        world.addSystem(new MovementSystem(world));

        SpaceShipManager spaceShipManager = (SpaceShipManager) game.getServiceProvider().get(SpaceShipManager.class);

        world.addEntity(spaceShipManager.get("player"));

        world.addEntity(spaceShipManager.get("other"));
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
