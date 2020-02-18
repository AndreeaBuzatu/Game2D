package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import org.jsfml.window.Keyboard;
import uk.ac.lancaster.scc210.engine.content.SoundBufferManager;
import uk.ac.lancaster.scc210.engine.controller.ControllerButton;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.FiringPatternComponent;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;

/**
 * System which handles Entities firing. This system has a pool of pre-allocated bullets which it draws from.
 * The system places Bullets (Entities) into the front-middle of the entity.
 */
public class PlayerFiringSystem extends IterativeSystem {
    private final Time FIRING_GAP = Time.getSeconds(0.2f);

    private Time elapsedTime;

    private SoundBufferManager soundBufferManager;
    private SoundBuffer soundBuffer;
    private Sound sound;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world to draw entities from
     */
    public PlayerFiringSystem(World world) {
        super(world, PlayerComponent.class);

        elapsedTime = Time.ZERO;

        soundBufferManager = (SoundBufferManager) world.getServiceProvider().get(SoundBufferManager.class);
        soundBuffer = soundBufferManager.get("player-death");
        sound = new Sound(soundBuffer);
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            elapsedTime = Time.add(elapsedTime,deltaTime);

            FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

            if ((Keyboard.isKeyPressed(Keyboard.Key.SPACE) || ControllerButton.A_BUTTON.isPressed()) && elapsedTime.asSeconds() > FIRING_GAP.asSeconds()){
                world.addEntities(firingPatternComponent.getPattern().create());

                if (entity.hasComponent(SpaceShipComponent.class)) {
                    SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

                    spaceShipComponent.playFiringSound();
                }

                //sound.play();

                elapsedTime = Time.ZERO;
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
