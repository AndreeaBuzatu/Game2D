package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class RenderSystem extends IterativeSystem {
    public RenderSystem(World world) {
        super(world, SpriteComponent.class);
    }

    @Override
    public void draw(RenderTarget target) {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            if (spriteComponent != null) {
                target.draw(spriteComponent.getSprite());
            }
        }
    }

    @Override
    public void update() {

    }
}
