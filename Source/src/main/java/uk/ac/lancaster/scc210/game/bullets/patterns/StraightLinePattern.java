package uk.ac.lancaster.scc210.game.bullets.patterns;

import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class StraightLinePattern extends Pattern {
    private static final int NUM_BULLETS = 1;

    private final int BULLET_Y_PADDING = -20;

    public StraightLinePattern(World world, Entity spaceShip) {
        super(world, spaceShip, new Entity[NUM_BULLETS]);

        // Find the half-width of the entity sprite and the half-width of the bullet sprite
        float halfWidth = (spaceShipSprite.getSprite().getLocalBounds().width / 2) - (bulletSprite.getSprite().getLocalBounds().width / 2);

        Vector2f middleCentre = new Vector2f(halfWidth, BULLET_Y_PADDING);

        coords[NUM_BULLETS - 1] = spaceShipSprite.getSprite().getTransform().transformPoint(middleCentre);
    }

    @Override
    public void fire() {
        Entity bullet = bullets[NUM_BULLETS - 1];

        SpriteComponent bulletSprite = (SpriteComponent) bullet.findComponent(SpriteComponent.class);

        bulletSprite.getSprite().setPosition(coords[NUM_BULLETS - 1]);

        bulletSprite.getSprite().setRotation(spaceShipSprite.getSprite().getRotation());

        world.addEntity(bullets[NUM_BULLETS - 1]);
    }
}
