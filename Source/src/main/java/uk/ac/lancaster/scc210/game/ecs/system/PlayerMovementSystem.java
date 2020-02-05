package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.window.Keyboard;
import uk.ac.lancaster.scc210.engine.controller.Controller;
import uk.ac.lancaster.scc210.engine.controller.ControllerAxis;
import uk.ac.lancaster.scc210.engine.controller.ControllerButton;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.component.RotationComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

/**
 * System which handles keyboard input. Used to move the player around the screen
 */
public class PlayerMovementSystem extends IterativeSystem {
    private final int SPEED_SCALE_DOWN = 100;

    private boolean hasAxises;

    /**
     * Instantiates a new Movement system.
     *
     * @param world the world to draw entities from
     */
    public PlayerMovementSystem(World world) {
        super(world, SpriteComponent.class, SpeedComponent.class, RotationComponent.class, PlayerComponent.class);

        hasAxises = false;

        // Don't bother to detect if a controller hasn't been found
        if (Controller.hasController()) {
            hasAxises = ControllerAxis.RIGHT_JOYSTICK_UP_DOWN.hasAxis();

            hasAxises = ControllerAxis.RIGHT_JOYSTICK_LEFT_RIGHT.hasAxis();
        }
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            RotationComponent rotationComponent = (RotationComponent) entity.findComponent(RotationComponent.class);

            Sprite sprite = spriteComponent.getSprite();

            handleMovement(sprite, speedComponent.getSpeed());

            handleRotation(sprite, rotationComponent.getRotationAmount(), sprite.getRotation());
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }

    private void handleMovement(Sprite sprite, int speed) {
        int moveY;

        int moveX;

        moveX = handleJoystickMove(ControllerAxis.LEFT_JOYSTICK_LEFT_RIGHT, speed);

        moveY = handleJoystickMove(ControllerAxis.LEFT_JOYSTICK_UP_DOWN, speed);

        if (Keyboard.isKeyPressed(Keyboard.Key.W)) {
            moveY = -speed;

        } else if (Keyboard.isKeyPressed(Keyboard.Key.S)) {
            moveY = speed;
        }

        if (Keyboard.isKeyPressed(Keyboard.Key.A)) {
            moveX = -speed;

        } else if (Keyboard.isKeyPressed(Keyboard.Key.D)) {
            moveX = speed;
        }

        sprite.move(moveX, moveY);
    }

    private void handleRotation(Sprite sprite, float rotationAmount, float angle) {
        float toRotate = 0;

        if (hasAxises && ControllerAxis.RIGHT_JOYSTICK_UP_DOWN.axisesMoved(ControllerAxis.RIGHT_JOYSTICK_LEFT_RIGHT)) {
            float xPosition = ControllerAxis.RIGHT_JOYSTICK_LEFT_RIGHT.getAxisPosition();

            float yPosition = ControllerAxis.RIGHT_JOYSTICK_UP_DOWN.getAxisPosition();

            // Get the degree of rotation from the joysticks, set the speed accordingly
            toRotate = (float) Math.atan2(yPosition, xPosition) > RotationComponent.MIN_ROTATION ? -rotationAmount : rotationAmount;
        }

        if (Keyboard.isKeyPressed(Keyboard.Key.Q) || ControllerButton.LEFT_BUMPER.isPressed()) {
            toRotate = rotationAmount;

        } else if (Keyboard.isKeyPressed(Keyboard.Key.E) || ControllerButton.RIGHT_BUMPER.isPressed()) {
            toRotate = -rotationAmount;
        }

        // Prevent the rotation from going beyond 0 and 365.
        if (angle > RotationComponent.MAX_ROTATION) {
            sprite.setRotation(RotationComponent.MIN_ROTATION);

        } else if (angle < RotationComponent.MIN_ROTATION) {
            sprite.setRotation(RotationComponent.MAX_ROTATION);

        } else {
            sprite.rotate(toRotate);
        }
    }

    private int handleJoystickMove(ControllerAxis axis, float speed) {
        int move = 0;

        if (axis.axisMoved()) {
            float position = axis.getAxisPosition();

            // Ensure the controller is sufficiently far up
            if (position > ControllerAxis.AXIS_POSITIVE_THRESHOLD) {
                move = (int) (speed - (position / SPEED_SCALE_DOWN));
            }

            // Ensure the controller is sufficiently far down
            if (position < ControllerAxis.AXIS_NEGATIVE_THRESHOLD) {
                /*
                 * Negate because we are going downwards
                 * The axises return values between -100 and +100 our speed usually isn't in that range (0-10) so divide down
                 */
                move = (int) (-speed - (position / SPEED_SCALE_DOWN));
            }
        }

        return move;
    }
}