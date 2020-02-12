package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import org.jsfml.window.Keyboard;
import uk.ac.lancaster.scc210.engine.controller.ControllerButton;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AnimationComponent;
import uk.ac.lancaster.scc210.game.ecs.component.FiringPatternComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

/**
 * System which handles Entities firing. This system has a pool of pre-allocated bullets which it draws from.
 * The system places Bullets (Entities) into the front-middle of the entity.
 */
public class PlayerFiringSystem extends IterativeSystem {

    private final Time FIRING_GAP = Time.getSeconds(0.2f);

    private Time elapsedTime;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world to draw entities from
     */
    public PlayerFiringSystem(World world) {
        super(world, SpriteComponent.class, AnimationComponent.class);

        elapsedTime = Time.ZERO;
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            elapsedTime = Time.add(elapsedTime,deltaTime);

            FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

            if ((Keyboard.isKeyPressed(Keyboard.Key.SPACE) || ControllerButton.A_BUTTON.isPressed()) && elapsedTime.asSeconds() > FIRING_GAP.asSeconds()){
                world.addEntities(firingPatternComponent.getPattern().create());

                elapsedTime = Time.ZERO;
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
