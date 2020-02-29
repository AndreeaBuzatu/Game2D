package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import org.jsfml.window.Keyboard;
import uk.ac.lancaster.scc210.engine.controller.ControllerButton;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.FiringPatternComponent;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;
import uk.ac.lancaster.scc210.game.ecs.entity.PlayerFinder;
import uk.ac.lancaster.scc210.game.patterns.Pattern;

/**
 * System which handles Entities firing. This system has a pool of pre-allocated bullets which it draws from.
 * The system places Bullets (Entities) into the front-middle of the entity.
 */
public class PlayerFiringSystem extends IterativeSystem {
    private Entity player;

    private Time elapsedTime;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world to draw entities from
     */
    public PlayerFiringSystem(World world) {
        super(world, PlayerComponent.class);

        player = PlayerFinder.findPlayer(world);

        elapsedTime = Time.ZERO;
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        player = PlayerFinder.findPlayer(world);
    }

    @Override
    public void update(Time deltaTime) {
        if (player == null) {
            return;
        }

        elapsedTime = Time.add(elapsedTime, deltaTime);

        FiringPatternComponent firingPatternComponent = (FiringPatternComponent) player.findComponent(FiringPatternComponent.class);

        Pattern firingPattern = firingPatternComponent.getPattern();

        if ((Keyboard.isKeyPressed(Keyboard.Key.SPACE) || ControllerButton.A_BUTTON.isPressed()) && elapsedTime.asSeconds() > firingPattern.getFiringGap().asSeconds()) {
            world.addEntities(firingPatternComponent.getPattern().create());

            if (player.hasComponent(SpaceShipComponent.class)) {
                SpaceShipComponent spaceShipComponent = (SpaceShipComponent) player.findComponent(SpaceShipComponent.class);

                spaceShipComponent.playFiringSound();
            }

            elapsedTime = Time.ZERO;
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
