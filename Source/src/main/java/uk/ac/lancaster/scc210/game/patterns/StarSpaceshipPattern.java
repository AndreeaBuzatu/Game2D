package uk.ac.lancaster.scc210.game.patterns;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.content.SpaceShipPrototypeManager;
import uk.ac.lancaster.scc210.game.ecs.component.EnemyComponent;
import uk.ac.lancaster.scc210.game.ecs.component.FiredComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

/**
 * The type Star spaceship pattern.
 */
public class StarSpaceshipPattern extends Pattern {
    private static final Time FIRING_GAP = Time.getSeconds(1.3f);

    private static final int NUM_BULLETS = 8;

    private final SpaceShipPrototypeManager spaceShipPrototypeManager;

    private float angle;

    /**
     * Instantiates a new Star spaceship pattern.
     *
     * @param spaceShip                 the space ship
     * @param spaceShipPrototypeManager the space ship prototype manager
     * @param shipName                  the ship name
     */
    public StarSpaceshipPattern(Entity spaceShip, SpaceShipPrototypeManager spaceShipPrototypeManager, String shipName) {
        super(spaceShip, new Entity[NUM_BULLETS], shipName, FIRING_GAP);

        this.spaceShipPrototypeManager = spaceShipPrototypeManager;

        this.angle = 0f;
    }

    @Override
    public Entity[] create() {

        getAngle();

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

            firedSpaceShipSprite.setRotation(spaceShipSprite.getRotation() + (i - 1) * (360f / NUM_BULLETS) - angle);
        }

        return toFire;
    }

    @Override
    public void position(Sprite toSpawnSprite) {
        FloatRect localBounds = toSpawnSprite.getLocalBounds();

        positionStarPatterns(toSpawnSprite);
    }

    @Override
    protected void positionStarPatterns(Sprite bulletSprite) {

        FloatRect localBounds = spaceShipSprite.getLocalBounds();

        float width = localBounds.width;

        float height = localBounds.height;

        for (int i = 0; i < NUM_BULLETS; i++){
            coords[i] = new Vector2f(width / 2, height / 2);
        }
    }

    private void getAngle() {
        angle += 30;

        if (angle>=360) {
            angle -= 360;
        }
    }
}
