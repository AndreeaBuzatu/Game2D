package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.content.ItemPrototypeManager;
import uk.ac.lancaster.scc210.game.ecs.component.*;

import java.util.Random;

public class ItemDropSystem extends IterativeSystem {
    // Lower bounds for item score
    private final int MIN_SCORE = 1;

    // Upper bounds for item score
    private final int MAX_SCORE = 8;

    // Items have a 5% chance of giving the player some score
    private final int GIVE_SCORE_CHANCE = 25;

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
        // The player or entities which are registered as being out of the window bounds (except waves) don't drop items
        if (entity.hasComponent(PlayerComponent.class) || entity.hasComponent(OutOfBoundsComponent.class)) {
            return;
        }

        //if (percentChance(DROP_CHANCE)) {
            if (entity.hasComponent(SpaceShipComponent.class)) {
                ItemPrototypeManager itemPrototypeManager = (ItemPrototypeManager) world.getServiceProvider().get(ItemPrototypeManager.class);

                SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

                String[] items = spaceShipComponent.getItems();

                if (items.length > 0) {
                    String item = items[randomItem(items.length)];

                    createItem(itemPrototypeManager, item, entity);
                }
            }
        //}
    }

    @Override
    public void update(Time deltaTime) {

    }

    @Override
    public void draw(RenderTarget target) {

    }

    private void createItem(ItemPrototypeManager itemPrototypeManager, String itemName, Entity spaceShip) {
        if (itemName.isEmpty()) {
            return;
        }

        Entity item = itemPrototypeManager.get(itemName).create();

        SpriteComponent itemSpriteComponent = (SpriteComponent) item.findComponent(SpriteComponent.class);

        SpriteComponent entitySpriteComponent = (SpriteComponent) spaceShip.findComponent(SpriteComponent.class);

        itemSpriteComponent.getSprite().setPosition(entitySpriteComponent.getSprite().getPosition());

        // There's a 5% chance an item will have a score (between 0-8) associated with it
        if (percentChance(GIVE_SCORE_CHANCE)) {
            ScoreComponent scoreComponent = new ScoreComponent(randScore());

            item.addComponent(scoreComponent);
        }

        world.addEntity(item);
    }

    private boolean percentChance(int chance) {
        return random.nextInt(HUNDRED_PERCENT) < chance;
    }

    private int randomItem(int numItems) {
        return random.nextInt(numItems);
    }

    private int randScore() {
        return random.nextInt((MAX_SCORE - MIN_SCORE) + 1) + MIN_SCORE;
    }
}
