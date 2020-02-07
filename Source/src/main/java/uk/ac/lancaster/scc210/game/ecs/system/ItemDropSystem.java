package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.content.ItemPrototypeManager;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.prototypes.ItemPrototype;

public class ItemDropSystem extends IterativeSystem {
    /**
     * Instantiates a new Iterative system.
     *
     * @param world      the world containing entities to use
     */
    public ItemDropSystem(World world) {
        super(world, SpaceShipComponent.class);
    }

    @Override
    public void entityRemoved(Entity entity) {
        super.entityRemoved(entity);

        if (entity.hasComponent(SpaceShipComponent.class)) {
            ItemPrototypeManager itemPrototypeManager = (ItemPrototypeManager) world.getServiceProvider().get(ItemPrototypeManager.class);
            Entity item = itemPrototypeManager.get("test-item").create();
            SpriteComponent itemSpriteComponent = (SpriteComponent) item.findComponent(SpriteComponent.class);
            SpriteComponent entitySpriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);
            itemSpriteComponent.getSprite().setPosition(entitySpriteComponent.getSprite().getPosition());
            world.addEntity(item);
        }
    }

    @Override
    public void update(Time deltaTime) {

    }

    @Override
    public void draw(RenderTarget target) {

    }
    //
}
