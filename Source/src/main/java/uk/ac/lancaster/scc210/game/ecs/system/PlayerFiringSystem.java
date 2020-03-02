package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import org.jsfml.window.Keyboard;
import uk.ac.lancaster.scc210.engine.content.SoundManager;
import uk.ac.lancaster.scc210.engine.controller.ControllerButton;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.FiringPatternComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;
import uk.ac.lancaster.scc210.game.ecs.entity.PlayerFinder;
import uk.ac.lancaster.scc210.game.patterns.Pattern;

import java.util.Collection;

/**
 * System which handles Entities firing. This system has a pool of pre-allocated bullets which it draws from.
 * The system places Bullets (Entities) into the front-middle of the entity.
 */
public class PlayerFiringSystem extends IterativeSystem {
    private final SoundManager soundManager;

    private Entity player;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world to draw entities from
     */
    public PlayerFiringSystem(World world) {
        super(world);

        player = PlayerFinder.findPlayer(world);

        soundManager = (SoundManager) world.getServiceProvider().get(SoundManager.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        if (player == null) {
            player = PlayerFinder.findPlayer(world);
        }
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
        if (player == null) {
            player = PlayerFinder.findPlayer(world);
        }
    }

    @Override
    public void entityRemoved(Entity entity) {
    }

    @Override
    public void update(Time deltaTime) {
        if (player == null) {
            return;
        }

        FiringPatternComponent firingPatternComponent = (FiringPatternComponent) player.findComponent(FiringPatternComponent.class);

        Pattern firingPattern = firingPatternComponent.getPattern();

        firingPattern.setElapsedTime(Time.add(firingPattern.getElapsedTime(), deltaTime));

        if ((Keyboard.isKeyPressed(Keyboard.Key.SPACE) || ControllerButton.A_BUTTON.isPressed()) && firingPattern.getElapsedTime().asSeconds() > firingPattern.getFiringGap().asSeconds()) {
            world.addEntities(firingPatternComponent.getPattern().create());

            if (player.hasComponent(SpaceShipComponent.class)) {
                SpaceShipComponent spaceShipComponent = (SpaceShipComponent) player.findComponent(SpaceShipComponent.class);

                //soundManager.playSound(spaceShipComponent.getFiringSound());
            }

            firingPattern.setElapsedTime(Time.ZERO);
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
