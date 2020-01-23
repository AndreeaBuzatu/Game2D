package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class MovementSystem extends IterativeSystem {
    /**
     * Instantiates a new Iterative system.
     *
     * @param world      the world
     */
    public MovementSystem(World world) {
        super(world, SpriteComponent.class, SpeedComponent.class, BulletComponent.class);
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            float speed = speedComponent.getSpeed();

            float angle = spriteComponent.getSprite().getRotation();

            // This is likely wrong, but I works... I'm not a mathematician
            float posX = (float) (speed * Math.sin(Math.toRadians(angle)));

            float posY = (float) (speed * -Math.cos(Math.toRadians(angle)));

            spriteComponent.getSprite().move(posX, posY);
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
