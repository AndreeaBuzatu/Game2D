package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.content.SoundBufferManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AnimationComponent;
import uk.ac.lancaster.scc210.game.ecs.component.EnemyComponent;
import uk.ac.lancaster.scc210.game.ecs.component.FiringPatternComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class EnemyFiringSystem extends IterativeSystem {
    private final Time FIRING_GAP = Time.getSeconds(1);

    private Time elapsedTime;

    private SoundBufferManager soundBufferManager;
    private SoundBuffer soundBuffer;
    private Sound sound;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public EnemyFiringSystem(World world) {
        super(world, SpriteComponent.class, AnimationComponent.class, EnemyComponent.class);

        elapsedTime = Time.ZERO;

        soundBufferManager = (SoundBufferManager) world.getServiceProvider().get(SoundBufferManager.class);
        soundBuffer = soundBufferManager.get("enemy-one-shoot");
        sound = new Sound(soundBuffer);
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            elapsedTime = Time.add(elapsedTime, deltaTime);

            if (elapsedTime.asSeconds() > FIRING_GAP.asSeconds()) {
                FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

                world.addEntities(firingPatternComponent.getPattern().create());

                sound.play();

                elapsedTime = Time.ZERO;
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
