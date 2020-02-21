package uk.ac.lancaster.scc210.game.bullets.patterns;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class CrossPattern extends Pattern {

    private static final int NUM_BULLETS = 13 ;

    private final int BULLET_Y_PADDING = -40;

    public CrossPattern(Pool pool, Entity spaceShip, String bulletName) {
        super(pool, spaceShip, new Entity[NUM_BULLETS], bulletName);

        bullets[0] = pool.borrowEntity(bulletName);

        Sprite bulletSprite = ((SpriteComponent) bullets[0].findComponent(SpriteComponent.class)).getSprite();

        // Pre-determine the bullet positions
        position(bulletSprite);

        // Let's return the entity back now
        pool.returnEntity(bullets[0]);
    }





    @Override
    public Entity[] create() {
        for (int i = 0; i < NUM_BULLETS; i++) {
            bullets[i] = pool.borrowEntity(bulletName);

            bullets[i].addComponent(new BulletComponent(entity));

            Sprite bulletSprite = ((SpriteComponent) bullets[i].findComponent(SpriteComponent.class)).getSprite();

            Vector2f bulletPos = spaceShipSprite.getTransform().transformPoint(coords[i]);

            bulletSprite.setPosition(bulletPos);

            //  bulletSprite.setRotation(spaceShipSprite.getRotation() + (i - 1) * 45);
        }

        return bullets;
    }

    @Override
    void position(Sprite bulletSprite) {

        float halfWidth = (spaceShipSprite.getLocalBounds().width / 2) - (bulletSprite.getLocalBounds().width / 2);

        float bulletWidth = bulletSprite.getLocalBounds().width;

        float bulletHeight = bulletSprite.getLocalBounds().height;

        //Vector2f middleCentre = new Vector2f(halfWidth, BULLET_Y_PADDING);

        coords[0] = new Vector2f(halfWidth , BULLET_Y_PADDING);

        coords[1] =  new Vector2f(halfWidth + bulletWidth , BULLET_Y_PADDING);

        coords[2] =  new Vector2f(halfWidth + 2*bulletWidth , BULLET_Y_PADDING);

        coords[3] =  new Vector2f(halfWidth  - bulletWidth , BULLET_Y_PADDING);

        coords[4] =  new Vector2f(halfWidth  - 2*bulletWidth , BULLET_Y_PADDING);

        coords[5] =  new Vector2f(halfWidth  , BULLET_Y_PADDING + bulletHeight);

        coords[6] =  new Vector2f(halfWidth  , BULLET_Y_PADDING + 2*bulletHeight);

        coords[7] =  new Vector2f(halfWidth , BULLET_Y_PADDING - bulletHeight);

        coords[8] =  new Vector2f(halfWidth , BULLET_Y_PADDING - 2*bulletHeight);

        coords[9] =  new Vector2f(halfWidth + bulletWidth , BULLET_Y_PADDING + bulletHeight);

        coords[10] =  new Vector2f(halfWidth + bulletWidth , BULLET_Y_PADDING - bulletHeight);

        coords[11] =  new Vector2f(halfWidth  - bulletWidth , BULLET_Y_PADDING + bulletHeight);

        coords[12] =  new Vector2f(halfWidth  - bulletWidth , BULLET_Y_PADDING - bulletHeight);













    }
}

