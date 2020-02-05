package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.WaveComponent;
import uk.ac.lancaster.scc210.game.level.Level;
import uk.ac.lancaster.scc210.game.level.LevelStage;
import uk.ac.lancaster.scc210.game.level.LevelWave;

public class LevelSystem extends IterativeSystem {
    private Level level;

    private LevelStage currentStage;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public LevelSystem(World world, Level level) {
        super(world);

        this.level = level;

        currentStage = level.getCurrentStage();
    }

    @Override
    public void entityRemoved(Entity entity) {
        super.entityRemoved(entity);

        WaveComponent waveComponent = (WaveComponent) entity.findComponent(WaveComponent.class);

        if (waveComponent != null) {
            waveComponent.getWave().remove(entity);
        }
    }

    @Override
    public void update() {
        if (currentStage != null && currentStage.complete()) {
            System.out.println("Completed wave");

            currentStage = level.changeStage();

        } else {
            if (currentStage != null) {
                for (LevelWave wave : currentStage.getWaves()) {
                    Entity newShip = wave.spawnNew();

                    if (newShip != null) {
                        world.addEntity(newShip);
                    }

                    wave.getWave().update(wave.getEntities());
                }
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
