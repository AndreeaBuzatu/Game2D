package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.window.Keyboard;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.RotationComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

/**
 * System which handles keyboard input. Used to move the player around the screen
 */
public class KeyboardMovementSystem extends IterativeSystem {
    /**
     * Instantiates a new Movement system.
     *
     * @param world the world to draw entities from
     */
    public KeyboardMovementSystem(World world) {
        super(world, SpriteComponent.class, SpeedComponent.class, RotationComponent.class);
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            RotationComponent rotationComponent = (RotationComponent) entity.findComponent(RotationComponent.class);

            float angle = spriteComponent.getSprite().getRotation();

            float rotationAmount = rotationComponent.getRotationAmount();

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

            if (Keyboard.isKeyPressed(Keyboard.Key.Q)) {
                if (angle >= RotationComponent.MAX_ROTATION) {
                    spriteComponent.getSprite().setRotation(RotationComponent.MIN_ROTATION);

                } else {
                    spriteComponent.getSprite().rotate(rotationAmount);
                }

            } else if (Keyboard.isKeyPressed(Keyboard.Key.E)) {
                if (angle <= RotationComponent.MIN_ROTATION) {
                    spriteComponent.getSprite().setRotation(RotationComponent.MAX_ROTATION);

                } else {
                    spriteComponent.getSprite().rotate(-rotationAmount);
                }
            }

            spriteComponent.getSprite().move(moveX, moveY);
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
