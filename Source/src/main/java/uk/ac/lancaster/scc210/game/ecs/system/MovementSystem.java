package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.window.Keyboard;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

/**
 * The type Movement system.
 */
public class MovementSystem extends IterativeSystem {
    /**
     * Instantiates a new Movement system.
     *
     * @param world the world
     */
    public MovementSystem(World world) {
        super(world, SpriteComponent.class, SpeedComponent.class);
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            int moveY = 0;
            int moveX = 0;

            if (Keyboard.isKeyPressed(Keyboard.Key.W)) {
                moveY = -speedComponent.getSpeed();

            } else if (Keyboard.isKeyPressed(Keyboard.Key.S)) {
                moveY = speedComponent.getSpeed();
            }

            if (Keyboard.isKeyPressed(Keyboard.Key.A)) {
                moveX = -speedComponent.getSpeed();

            } else if (Keyboard.isKeyPressed(Keyboard.Key.D)) {
                moveX = speedComponent.getSpeed();
            }

            spriteComponent.getSprite().move(moveX, moveY);
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
