package uk.ac.lancaster.scc210.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.window.Keyboard;
import uk.ac.lancaster.scc210.ecs.World;
import uk.ac.lancaster.scc210.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.ecs.entity.Entity;

public class MovementSystem extends IterativeSystem {
    private SpriteComponent spriteComponent;

    public MovementSystem(World world) {
        super(world, SpriteComponent.class);
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            int moveY = 0;

            if (Keyboard.isKeyPressed(Keyboard.Key.W)) {
                moveY = -5;

            } else if (Keyboard.isKeyPressed(Keyboard.Key.S)) {
                moveY = 5;
            }

            spriteComponent.getSprite().move(0, moveY);
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
