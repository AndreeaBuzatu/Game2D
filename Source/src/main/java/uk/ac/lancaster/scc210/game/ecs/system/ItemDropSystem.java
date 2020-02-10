package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.content.ItemPrototypeManager;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

import java.util.Random;

public class ItemDropSystem extends IterativeSystem {
    // nextInt is 0-indexed
    private final int HUNDRED_PERCENT = 99;

    // Items have a 20% chance of dropping
    private final int DROP_CHANCE = 20;

    private final Random random;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world      the world containing entities to use
     */
    public ItemDropSystem(World world) {
        super(world);

        random = new Random();
    }

    @Override
    public void entityRemoved(Entity entity) {
        super.entityRemoved(entity);

        if (random.nextInt(HUNDRED_PERCENT) < DROP_CHANCE) {
            if (entity.hasComponent(SpaceShipComponent.class)) {
                ItemPrototypeManager itemPrototypeManager = (ItemPrototypeManager) world.getServiceProvider().get(ItemPrototypeManager.class);

                SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

                String[] items = spaceShipComponent.getItems();

                String item = items[randomItem(items.length)];

                createItem(itemPrototypeManager, item, entity);
            }
        }
    }

    @Override
    public void update(Time deltaTime) {

    }

    @Override
    public void draw(RenderTarget target) {

    }

    private void createItem(ItemPrototypeManager itemPrototypeManager, String itemName, Entity spaceShip) {
        Entity item = itemPrototypeManager.get(itemName).create();

        SpriteComponent itemSpriteComponent = (SpriteComponent) item.findComponent(SpriteComponent.class);

        SpriteComponent entitySpriteComponent = (SpriteComponent) spaceShip.findComponent(SpriteComponent.class);

        itemSpriteComponent.getSprite().setPosition(entitySpriteComponent.getSprite().getPosition());

        world.addEntity(item);
    }

    private int randomItem(int numItems) {
        return random.nextInt(numItems);
    }
}
