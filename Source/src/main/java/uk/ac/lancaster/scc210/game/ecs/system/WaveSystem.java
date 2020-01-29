package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.ecs.component.WaveComponent;

public class WaveSystem extends IterativeSystem {
    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public WaveSystem(World world) {
        super(world, SpriteComponent.class, SpeedComponent.class, WaveComponent.class);
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            WaveComponent waveComponent = (WaveComponent) entity.findComponent(WaveComponent.class);

            waveComponent.getWave().update(entity);
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
