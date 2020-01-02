package uk.ac.lancaster.scc210.ecs.system;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.ecs.World;
import uk.ac.lancaster.scc210.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.ecs.entity.Entity;

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
