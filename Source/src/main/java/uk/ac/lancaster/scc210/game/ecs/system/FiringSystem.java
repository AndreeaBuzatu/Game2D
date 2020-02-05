package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.bullets.patterns.StarPattern;
import uk.ac.lancaster.scc210.game.controller.ControllerButton;
import uk.ac.lancaster.scc210.game.ecs.component.AnimationComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;

/**
 * System which handles Entities firing. This system has a pool of pre-allocated bullets which it draws from.
 * The system places Bullets (Entities) into the front-middle of the entity.
 */
public class FiringSystem extends IterativeSystem {
    private final int BULLET_Y_PADDING = -20;

    private final Pool bulletPool;
    StarPattern starPattern;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world to draw entities from
     */
    public FiringSystem(World world) {
        super(world, SpriteComponent.class, AnimationComponent.class);

        bulletPool = world.getPool(BulletPool.class);

    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            starPattern = new StarPattern(world,entity);
            SpriteComponent entitySprite = (SpriteComponent) entity.findComponent(SpriteComponent.class);
            if (Keyboard.isKeyPressed(Keyboard.Key.SPACE) || ControllerButton.A_BUTTON.isPressed()) {


                starPattern.fire();
              /* Entity[] bullet = new Entity[8];
                SpriteComponent[] bulletSprite = new SpriteComponent[8];
               for(int i = 0; i < 8; i++)
               {
                   bullet[i] = bulletPool.borrowEntity();
                   bulletSprite[i] = (SpriteComponent) bullet[i].findComponent(SpriteComponent.class);
               }


                // Find the half-width of the entity sprite and the half-width of the bullet sprite
                float startPoint =  - bulletSprite[1].getSprite().getLocalBounds().width/2;
                float width = entitySprite.getSprite().getLocalBounds().width;
                float height = entitySprite.getSprite().getLocalBounds().height;
                float equalDistance = (float) ( Math.sqrt(Math.pow(height,2) + Math.pow(width,2))/Math.sqrt(2));
                float difference = (float) (equalDistance*(Math.sqrt(2) - 1)/2);



                Vector2f[] bulletPos = new Vector2f[8];
                Vector2f[] coordinates = new Vector2f[8];

                 coordinates[0] = new Vector2f(startPoint , startPoint);
                 coordinates[1] = new Vector2f( startPoint +equalDistance/2 ,  - difference);
                 coordinates[2] = new Vector2f(startPoint/2 + equalDistance , startPoint);
                 coordinates[3] = new Vector2f(startPoint + equalDistance + difference  , startPoint + equalDistance/2);
                 coordinates[4] = new Vector2f( equalDistance,  equalDistance);
                 coordinates[5] = new Vector2f(-startPoint + equalDistance/2 , equalDistance + difference );
                 coordinates[6] = new Vector2f( -startPoint, width - startPoint);
                 coordinates[7] = new Vector2f(-startPoint - difference , -startPoint + equalDistance/2);



                 for(int i = 0; i < 8; i++)
                {
                    bulletPos[i] = entitySprite.getSprite().getTransform().transformPoint(coordinates[i]);
                    bulletSprite[i].getSprite().setPosition(bulletPos[i]);
                    bulletSprite[i].getSprite().setRotation(entitySprite.getSprite().getRotation() + (i -1)*45);
                    world.addEntity(bullet[i]);


                }*/

            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
