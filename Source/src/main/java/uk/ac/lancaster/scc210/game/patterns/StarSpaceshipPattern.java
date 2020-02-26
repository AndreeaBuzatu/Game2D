package uk.ac.lancaster.scc210.game.patterns;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.content.SpaceShipPrototypeManager;
import uk.ac.lancaster.scc210.game.ecs.component.EnemyComponent;
import uk.ac.lancaster.scc210.game.ecs.component.FiredComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class StarSpaceshipPattern extends Pattern {
    private static final Time FIRING_GAP = Time.getSeconds(5);

    private static final int NUM_BULLETS = 8;

    private SpaceShipPrototypeManager spaceShipPrototypeManager;

    public StarSpaceshipPattern(Entity spaceShip, SpaceShipPrototypeManager spaceShipPrototypeManager) {
        super(spaceShip, new Entity[NUM_BULLETS], "spawned-other", FIRING_GAP);

        this.spaceShipPrototypeManager = spaceShipPrototypeManager;
    }

    @Override
    public Entity[] create() {
        for (int i = 0; i < NUM_BULLETS; i++) {
            if (i == 0) {
                // It doesn't matter if we waste one allocation that's okay...
                toFire[0] = spaceShipPrototypeManager.get(spawnedEntityName).create();

                Sprite bulletSprite = ((SpriteComponent) toFire[0].findComponent(SpriteComponent.class)).getSprite();

                // Pre-determine the bullet positions
                position(bulletSprite);
            }

            toFire[i] = this.spaceShipPrototypeManager.get(spawnedEntityName).create();

            toFire[i].addComponent(new FiredComponent());

            if (spaceShip.hasComponent(EnemyComponent.class)) {
                toFire[i].addComponent(new EnemyComponent());
            }

            Sprite firedSpaceShipSprite = ((SpriteComponent) toFire[i].findComponent(SpriteComponent.class)).getSprite();

            Vector2f firedShipPos = spaceShipSprite.getTransform().transformPoint(coords[i]);

            firedSpaceShipSprite.setPosition(firedShipPos);

            firedSpaceShipSprite.setRotation(spaceShipSprite.getRotation() + (i - 1) * 45);
        }

        return toFire;
    }

    @Override
    public void position(Sprite toSpawnSprite) {
        float startPoint = -toSpawnSprite.getLocalBounds().width / 2;

        float width = spaceShipSprite.getLocalBounds().width;

        float height = spaceShipSprite.getLocalBounds().height;

        float equalDistance = (float) (Math.sqrt(Math.pow(height, 2) + Math.pow(width, 2)) / Math.sqrt(2));

        float difference = (float) (equalDistance * (Math.sqrt(2) - 1) / 2);

        coords[0] = new Vector2f(startPoint, startPoint);

        coords[1] = new Vector2f(startPoint + equalDistance / 2, -difference);

        coords[2] = new Vector2f(startPoint / 2 + equalDistance, startPoint);

        coords[3] = new Vector2f(startPoint + equalDistance + difference, startPoint + equalDistance / 2);

        coords[4] = new Vector2f(equalDistance, equalDistance);

        coords[5] = new Vector2f(-startPoint + equalDistance / 2, equalDistance + difference);

        coords[6] = new Vector2f(-startPoint, width - startPoint);

        coords[7] = new Vector2f(-startPoint - difference, -startPoint + equalDistance / 2);
    }
}
